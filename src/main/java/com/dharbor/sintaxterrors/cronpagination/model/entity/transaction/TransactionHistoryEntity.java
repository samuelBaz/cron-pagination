package com.dharbor.sintaxterrors.cronpagination.model.entity.transaction;

import com.dharbor.sintaxterrors.cronpagination.model.constant.TransactionHistoryEntityConstants.TransactionHistoryTable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = TransactionHistoryTable.NAME)
public class TransactionHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TransactionHistoryTable.Id.NAME, nullable = false)
    private Long id;

    @Column(name = TransactionHistoryTable.Name.NAME)
    private String name;

    @Column(name = TransactionHistoryTable.NumberTransaction.NAME)
    private String numberTransaction;

    @Column(name = TransactionHistoryTable.Amount.NAME)
    private BigDecimal amount;

    @Column(name = TransactionHistoryTable.Type.NAME)
    private String type;

    @Column(name = TransactionHistoryTable.Date.NAME)
    private LocalDateTime date;
} 