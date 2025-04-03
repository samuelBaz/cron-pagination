package com.dharbor.sintaxterrors.cronpagination.repository;

import com.dharbor.sintaxterrors.cronpagination.model.entity.transaction.TransactionHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistoryEntity, Long> {
    Page<TransactionHistoryEntity> findByDateBetweenAndType(LocalDateTime startDate, LocalDateTime endDate, String type, Pageable pageable);
    Page<TransactionHistoryEntity> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Page<TransactionHistoryEntity> findByType(String type, Pageable pageable);
}
