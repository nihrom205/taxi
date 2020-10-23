package ru.bitmaster.taxi.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Account debitingAccount;

    @OneToOne
    private Account creditingAccount;

    @OneToOne
    private StatusOperation statusOperation;

    private LocalDate dateCreate = LocalDate.now();

    public Operation() {
    }

    public Operation(Account debitingAccount, Account creditingAccount, StatusOperation statusOperation) {
        this.debitingAccount = debitingAccount;
        this.creditingAccount = creditingAccount;
        this.statusOperation = statusOperation;
    }

    public Operation(Account debitingAccount, StatusOperation statusOperation) {
        this.debitingAccount = debitingAccount;
        this.statusOperation = statusOperation;
    }
}
