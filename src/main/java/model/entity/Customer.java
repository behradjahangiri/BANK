package model.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

public class Customer {
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String nationllid;
    private String phone;
    private String email;
    private String address;
    private LocalDate dateOfBirth;
    private LocalDate registrationDate;
    private List<Account> accounts;
    private List<Loan> loans;

    private void createAccount() {
    }
    private void updateAccount() {
    }
    private void deleteAccount() {
    }
    private void getAccount() {
    }
    private void requestLoan(){
    }

}
