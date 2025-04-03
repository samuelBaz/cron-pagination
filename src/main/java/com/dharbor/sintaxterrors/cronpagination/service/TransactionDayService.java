package com.dharbor.sintaxterrors.cronpagination.service;

import com.dharbor.sintaxterrors.cronpagination.model.entity.transaction.TransactionDayEntity;
import com.dharbor.sintaxterrors.cronpagination.repository.TransactionDayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TransactionDayService {
    private final TransactionDayRepository transactionDayRepository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TransactionDayService(TransactionDayRepository transactionDayRepository) {
        this.transactionDayRepository = transactionDayRepository;
    }

    public Page<TransactionDayEntity> getTransactionDay(Pageable pageable, String startDate, String endDate, String type) {
        if (startDate != null && endDate != null && type != null) {
            LocalDateTime start = LocalDateTime.parse(startDate + "T00:00:00", DATE_FORMATTER);
            LocalDateTime end = LocalDateTime.parse(endDate + "T23:59:59", DATE_FORMATTER);
            return transactionDayRepository.findByDateBetweenAndType(start, end, type, pageable);
        } else if (startDate != null && endDate != null) {
            LocalDateTime start = LocalDateTime.parse(startDate + "T00:00:00", DATE_FORMATTER);
            LocalDateTime end = LocalDateTime.parse(endDate + "T23:59:59", DATE_FORMATTER);
            return transactionDayRepository.findByDateBetween(start, end, pageable);
        } else if (type != null) {
            return transactionDayRepository.findByType(type, pageable);
        }

        return transactionDayRepository.findAll(pageable);
    }
} 