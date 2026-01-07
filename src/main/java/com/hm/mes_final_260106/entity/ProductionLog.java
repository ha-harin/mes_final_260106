package com.hm.mes_final_260106.entity;
// 생산이력 관리 : 제품 1개가 실제로 생산된 사실을 기록하는 테이블
// 계획이 아닌 실행 결과, 수정 대상이 아니고 쌓인 결과
// 작업지시번호, 설비, 작업자, 투입된 자재(LOT)
// 5M1E의 집약체 (Man, Machine, Material, Method, Measurement, Environment)

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

// 생산 이력 (가장 중요한 테이블: 5M1E 정보의 집약체)
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductionLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workOrderNo; // 작업지시번호
    private String productCode; // 제품코드, 제품 테이블로 생성된 객체를 ManyToOne으로 연결하는게 더 좋은 방식
    private String machineId; // 설비 ID

    @Column(unique = true)
    private String serialNo; // ★ 추적성(Traceability) 핵심: 제품 고유 ID

    private String result; // OK or NG
    private String defectCode; // 불량인 경우 사유 표시 (예: ERR-001)

    private LocalDateTime producedAt; // 생산 시간
}

