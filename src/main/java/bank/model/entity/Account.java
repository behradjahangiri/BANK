package bank.model.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString


//کلاس میتونه ابسترک بشه

public class Account {
    private Integer accountId;
    private Double balance;
    private LocalDate openDate;
    private String status;
    private Customer customer;

    private List<Transaction> transactions;
}