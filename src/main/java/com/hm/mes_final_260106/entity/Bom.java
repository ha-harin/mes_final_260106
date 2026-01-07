package com.hm.mes_final_260106.entity;
// BOM (Bill of Materials)
// 제품 1개를 만들기 위해 필요한 자재 목록과 소요량을 정의하는 테이블
// 생산의 레시피 역할을 함
// MES에서 생산과 자재를 연결하는 핵심 기준 정보
// MES에서 생산이 진행될 때 어떤 자재가 얼마만큼 사용되었는가를 자동으로 계산
// 생산 시 BOM 기준으로 자동 재고 차감(Backflushing)
// Material과 ManyToOne 관계 : 하나의 BOM은 여러 자재(Material)를 가짐. 하나의 자재는 여러 제품의 BOM에 사용될 수 있음
// Product와 BOM은 1:N 관계, 자재(Material)와 BOM은 1:N 관계

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Bom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productCode; // 제품 코드

    @ManyToOne
    private Material material; // 필요한 자재
    private int requiredQty; // 소요량
}
