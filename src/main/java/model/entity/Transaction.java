package model.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString


public class Transaction {
    private long transactionid;
    private TransactionType transactionType;
    private double amount;
    private LocalDateTime date;
    private Account account;
//    private Account sourceaccount;
//    private Account targetAccount;
}
