package com.hm.mes_final_260106.repository;

import com.hm.mes_final_260106.entity.ProductionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionLogRepository extends JpaRepository<ProductionLog, Long> {
}
