package com.zharfna.zharfna.entity;

import com.zharfna.zharfna.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "revenue_records")
@Getter
@Setter
public class RevenueRecord extends BaseEntity<Long> {
    private Long toneCode;      // کد آهنگ در اکسل
    private String spName;      // نام تامین کننده
    private Double totalRevenue; // درآمد کل
    private Double spRevenue;    // درآمد خالص (سهم شرکت + خواننده)
    private LocalDate reportDate; // تاریخ گزارش (مثلاً اول ماه)

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song; // پیوند به آهنگ در سیستم ما
}