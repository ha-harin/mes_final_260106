package com.hm.mes_final_260106.dto;
// 작업 지시에 대한 응답 : 서버가 처리한 결과물을 '외부에 보여주기 위한 형태'로 가공한 데이터 객체

import com.hm.mes_final_260106.entity.WorkOrder;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data @Builder
public class WorkOrderResDto {
    private Long id; // 작업 지시의 고유 식별번호
    private String productCode; // 생산 대상 제품 코드
    private int targetQty; // 목표 생산 수량
    private int currentQty; // 현재 생산 수량
    private String status; // 작업 진행 상태
    private LocalDateTime orderDate; // 작업 지시 생성 지점

    // Entity -> DTO 변환 메서드
    public static WorkOrderResDto fromEntity(WorkOrder workOrder) {
        return WorkOrderResDto.builder()
                .id(workOrder.getId())
                .productCode(workOrder.getProductCode())
                .targetQty(workOrder.getTargetQty())
                .currentQty(workOrder.getCurrentQty())
                .status(workOrder.getStatus())
                .orderDate(workOrder.getCreatedAt())
                .build();
    }
}
