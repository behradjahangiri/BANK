package model.entity;

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
    private String accountName;
    private double balance;
    private LocalDate openDate;
    private String status;
    private Customer customer;

//    class list check bashe

    private List<Transaction> transactions;


    private Account deposit(){
        return null;
    }

    private Account withdraw(){
        return null;
    }

    private void addTransaction(){
    }

    private double chech(){
        return this.balance;
    }

}
