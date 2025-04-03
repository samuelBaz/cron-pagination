package com.dharbor.sintaxterrors.cronpagination.service;

import com.dharbor.sintaxterrors.cronpagination.model.entity.transaction.TransactionHistoryEntity;
import com.dharbor.sintaxterrors.cronpagination.repository.TransactionHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TransactionHistoryService {
    private final TransactionHistoryRepository transactionHistoryRepository;

    public TransactionHistoryService(TransactionHistoryRepository transactionHistoryRepository){
        this.transactionHistoryRepository = transactionHistoryRepository;
    }
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Page<TransactionHistoryEntity> getTransactionHistory(Pageable pageable, String startDate, String endDate, String type) {
        if (startDate != null && endDate != null && type != null) {
            LocalDateTime start = LocalDateTime.parse(startDate + "T00:00:00", DATE_FORMATTER);
            LocalDateTime end = LocalDateTime.parse(endDate + "T23:59:59", DATE_FORMATTER);
            return transactionHistoryRepository.findByDateBetweenAndType(start, end, type, pageable);
        } else if (startDate != null && endDate != null) {
            LocalDateTime start = LocalDateTime.parse(startDate + "T00:00:00", DATE_FORMATTER);
            LocalDateTime end = LocalDateTime.parse(endDate + "T23:59:59", DATE_FORMATTER);
            return transactionHistoryRepository.findByDateBetween(start, end, pageable);
        } else if (type != null) {
            return transactionHistoryRepository.findByType(type, pageable);
        }

        return transactionHistoryRepository.findAll(pageable);
    }
} 