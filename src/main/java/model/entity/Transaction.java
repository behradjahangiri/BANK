package model.entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString


public class Transaction {
    private long transactionid;
    private TransactionType transactionType;
    private double amount;
    private LocalDate date;
    private Account sourceAccount;
    private Account targetAccount;

}
