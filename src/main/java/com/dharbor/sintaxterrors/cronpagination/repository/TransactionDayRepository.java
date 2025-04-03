package com.dharbor.sintaxterrors.cronpagination.repository;

import com.dharbor.sintaxterrors.cronpagination.model.entity.transaction.TransactionDayEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionDayRepository extends JpaRepository<TransactionDayEntity, Long> {
    Page<TransactionDayEntity> findByDateBetweenAndType(LocalDateTime startDate, LocalDateTime endDate, String type, Pageable pageable);
    Page<TransactionDayEntity> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Page<TransactionDayEntity> findByType(String type, Pageable pageable);
    List<TransactionDayEntity> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
