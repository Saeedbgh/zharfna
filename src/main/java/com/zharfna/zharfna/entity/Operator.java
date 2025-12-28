package com.zharfna.zharfna.entity;

import com.zharfna.zharfna.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "operators")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Operator extends BaseEntity<Long> {

    @Column(nullable = false)
    private String operatorName;

    @Column(nullable = true)
    private Long operatorCode;

}
