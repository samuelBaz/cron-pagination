package com.dharbor.sintaxterrors.cronpagination.controller;

import com.dharbor.sintaxterrors.cronpagination.model.entity.transaction.TransactionDayEntity;
import com.dharbor.sintaxterrors.cronpagination.service.TransactionDayService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions/daily")
public class TransactionDayController {
    private final TransactionDayService transactionDayService;

    public TransactionDayController(TransactionDayService transactionDayService) {
        this.transactionDayService = transactionDayService;
    }

    @GetMapping
    public ResponseEntity<Page<TransactionDayEntity>> getTransactionDay(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String type) {
        
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        return ResponseEntity.ok(transactionDayService.getTransactionDay(pageable, startDate, endDate, type));
    }
} 