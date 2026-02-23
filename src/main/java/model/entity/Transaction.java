package model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder


public class Transaction {
    private long transactionid;
    private TransactionType transactionType;
    private double amount;
    private LocalDate date;
    private Account sourceAccount;
    private Account targetAccount;

}
