package bank.model.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString


public class Transaction {
    private long transactionId;
    private TransactionType transactionType;
    private double amount;
    private LocalDateTime dateTime;
    private Account account;
}
