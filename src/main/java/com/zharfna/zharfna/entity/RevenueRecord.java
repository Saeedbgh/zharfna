package com.zharfna.zharfna.entity;

import com.zharfna.zharfna.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "revenue_records", indexes = {
        @Index(name = "idx_song_report_date", columnList = "song_id, report_date"),
        @Index(name = "idx_operator_report_date", columnList = "operator_id, report_date"),
        @Index(name = "idx_report_date", columnList = "report_date")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RevenueRecord extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "operator_id", nullable = false)
    private Operator operator;

    @Column(name = "tone_code")
    private Long toneCode;

    @Column(name = "sp_name", length = 200)
    private String spName;

    @Enumerated(EnumType.STRING)
    @Column(name = "revenue_type", nullable = false, length = 50)
    private RevenueType revenueType = RevenueType.DOWNLOAD;

    @Column(name = "raw_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal rawAmount;

    @Column(name = "total_revenue", precision = 19, scale = 2)
    private BigDecimal totalRevenue;

    @Column(name = "sp_revenue", precision = 19, scale = 2)
    private BigDecimal spRevenue;

    @Column(name = "currency", nullable = false, length = 10)
    private String currency = "IRR";

    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate;

    @Column(name = "transaction_count")
    private Integer transactionCount;

    @Column(columnDefinition = "TEXT")
    private String metadata;

    @Column(name = "is_verified", nullable = false)
    private boolean verified = false;

    public enum RevenueType {
        DOWNLOAD,           // دانلود
        STREAMING,          // استریم
        RINGTONE,           // زنگ خور
        SUBSCRIPTION,       // اشتراک
        ADVERTISEMENT,      // تبلیغات
        ROYALTY,           // حق امتیاز
        OTHER              // سایر
    }
}
