package bank.model.tools;

import bank.model.entity.Account;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter

public class Session {
    public static Integer customerId;
    public static Double balance;
    public static Account account;
}
