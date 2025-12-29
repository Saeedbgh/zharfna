package com.zharfna.zharfna.entity;

import com.zharfna.zharfna.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "operators", indexes = {
        @Index(name = "idx_operator_code", columnList = "operator_code")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Operator extends BaseEntity<Long> {

    @Column(name = "operator_name", nullable = false, unique = true, length = 100)
    private String operatorName;

    @Column(name = "operator_code", unique = true)
    private Long operatorCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "operator_type", nullable = false, length = 50)
    private OperatorType operatorType = OperatorType.MOBILE_OPERATOR;

    @Column(nullable = false)
    private boolean active = true;

    @Column(columnDefinition = "TEXT")
    private String description;

    public enum OperatorType {
        MOBILE_OPERATOR,      // اپراتورهای موبایل
        STREAMING_PLATFORM,   // پلتفرم‌های استریم
        DOWNLOAD_PLATFORM,    // پلتفرم‌های دانلود
        MANUAL_REPORT,        // گزارش دستی
        API_INTEGRATION       // یکپارچه‌سازی API
    }
}
