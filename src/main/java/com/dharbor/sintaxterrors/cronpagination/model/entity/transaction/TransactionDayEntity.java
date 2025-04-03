package com.dharbor.sintaxterrors.cronpagination.model.entity.transaction;

import com.dharbor.sintaxterrors.cronpagination.model.constant.TransactionDayEntityConstants.TransactionDayTable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = TransactionDayTable.NAME)
public class TransactionDayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TransactionDayTable.Id.NAME, nullable = false)
    private Long id;

    @Column(name = TransactionDayTable.Name.NAME)
    private String name;

    @Column(name = TransactionDayTable.NumberTransaction.NAME)
    private String numberTransaction;

    @Column(name = TransactionDayTable.Amount.NAME)
    private BigDecimal amount;

    @Column(name = TransactionDayTable.Type.NAME)
    private String type;

    @Column(name = TransactionDayTable.Date.NAME)
    private LocalDateTime date;
} 