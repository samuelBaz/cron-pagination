package com.dharbor.sintaxterrors.cronpagination.service;

import com.dharbor.sintaxterrors.cronpagination.model.entity.transaction.TransactionDayEntity;
import com.dharbor.sintaxterrors.cronpagination.model.entity.transaction.TransactionHistoryEntity;
import com.dharbor.sintaxterrors.cronpagination.repository.TransactionDayRepository;
import com.dharbor.sintaxterrors.cronpagination.repository.TransactionHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

//@Slf4j
@Service
public class TransactionMigrationService {
    private final TransactionDayRepository transactionDayRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    @Value("${transaction.migration.cron}")
    private String migrationCron;

    public TransactionMigrationService(
            TransactionDayRepository transactionDayRepository,
            TransactionHistoryRepository transactionHistoryRepository) {
        this.transactionDayRepository = transactionDayRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    @Scheduled(cron = "${transaction.migration.cron}")
    @Transactional
    public void migrateDailyTransactionsToHistory() {
//        log.info("Starting daily transactions migration to history");
        
        // Get all transactions from current day
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        
        List<TransactionDayEntity> dailyTransactions = transactionDayRepository.findByDateBetween(startOfDay, endOfDay);
        
        // Convert and save to history
        List<TransactionHistoryEntity> historyTransactions = dailyTransactions.stream()
                .map(this::convertToHistoryEntity)
                .toList();
                
        transactionHistoryRepository.saveAll(historyTransactions);
        
        // Delete daily transactions
        transactionDayRepository.deleteAll(dailyTransactions);
        
//        log.info("Migration completed successfully. {} transactions moved to history", dailyTransactions.size());
    }

    private TransactionHistoryEntity convertToHistoryEntity(TransactionDayEntity dailyTransaction) {
        TransactionHistoryEntity historyEntity = new TransactionHistoryEntity();
        historyEntity.setName(dailyTransaction.getName());
        historyEntity.setNumberTransaction(dailyTransaction.getNumberTransaction());
        historyEntity.setAmount(dailyTransaction.getAmount());
        historyEntity.setType(dailyTransaction.getType());
        historyEntity.setDate(dailyTransaction.getDate());
        return historyEntity;
    }
} 