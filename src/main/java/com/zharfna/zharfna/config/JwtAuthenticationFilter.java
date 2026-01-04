package com.zharfna.zharfna.config;

import com.zharfna.zharfna.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            // 1️⃣ خواندن Authorization Header
            String header = request.getHeader("Authorization");

            if (header == null || !header.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // 2️⃣ جدا کردن JWT از Header
            String token = header.substring(7);

            // 3️⃣ اعتبارسنجی JWT
            if (jwtTokenProvider.validateToken(token)) {

                // 4️⃣ استخراج mobile و roles
                String mobile = jwtTokenProvider.getMobile(token);
                List<String> roles = jwtTokenProvider.getRoles(token);

                // 5️⃣ تبدیل roles به GrantedAuthority
                var authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                // 6️⃣ ساخت Authentication
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                mobile,      // ✅ principal = mobile
                                null,        // credentials
                                authorities
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // 7️⃣ تزریق به SecurityContext
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }

        } catch (Exception ex) {
            // 8️⃣ در صورت خطا، SecurityContext رو پاک کن
            SecurityContextHolder.clearContext();
        }

        // 9️⃣ ادامه زنجیره فیلترها
        filterChain.doFilter(request, response);
    }
}
