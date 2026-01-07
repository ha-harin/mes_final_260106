package com.hm.mes_final_260106.controller;
import com.hm.mes_final_260106.dto.MaterialInboundDto;
import com.hm.mes_final_260106.dto.ProductionReportDto;
import com.hm.mes_final_260106.dto.WorkOrderDto;
import com.hm.mes_final_260106.dto.WorkOrderResDto;
import com.hm.mes_final_260106.entity.Material;
import com.hm.mes_final_260106.entity.WorkOrder;
import com.hm.mes_final_260106.service.ProductionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 웹 대시보드 및 설비를 연결
@RestController
@RequestMapping("/api/mes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class MesController {
    private final ProductionService productionService;

    // Dashboard : 자재 입고 API / 자재가 입고됨을 알려줌, 생산 이전 상태
    @PostMapping("/material/inbound")
    public ResponseEntity<Material> inboundMaterial(@RequestBody MaterialInboundDto dto) {
        log.info("자재 입고 : {}", dto);
        return ResponseEntity
                .ok(productionService.inboundMaterial(dto.getCode(), dto.getName(), dto.getAmount()));
    }
    @GetMapping("/materials")
    public ResponseEntity<List<Material>> getAllMaterials() {
        return ResponseEntity.ok(productionService.getMaterialStock());
    }
    @PostMapping("/order")
    public ResponseEntity<WorkOrderResDto> createOrder(@RequestBody WorkOrderDto dto) {
        WorkOrder order = productionService.createWorkOrder(dto.getProductCode(), dto.getTargetQty());
        return ResponseEntity.ok(WorkOrderResDto.fromEntity(order));
    }
    @GetMapping("/orders")
    public ResponseEntity<List<WorkOrderResDto>> getAllOrders() {
        List<WorkOrderResDto> dtos = productionService.getAllWorkOrders()
                .stream()
                .map(WorkOrderResDto::fromEntity)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // --- [Machine / PLC API] ---
    @GetMapping("/machine/poll")
    public ResponseEntity<WorkOrderResDto> pollWork(@RequestParam String machineId) {
        WorkOrder work = productionService.assignWorkToMachine(machineId);
        return (work != null) ? ResponseEntity.ok(WorkOrderResDto.fromEntity(work))
                : ResponseEntity.noContent().build();
    }
    @PostMapping("/machine/report")
    public ResponseEntity<String> reportProduction(@RequestBody ProductionReportDto dto) {
        productionService.reportProduction(dto.getOrderId(), dto.getMachineId(),
                dto.getResult(), dto.getDefectCode());
        return ResponseEntity.ok("ACK");

}

}
