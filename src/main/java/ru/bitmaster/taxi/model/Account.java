package ru.bitmaster.taxi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numberAccount;

    private Long amount;

    @ManyToOne
    @JsonIgnore
    private Driver driver;

    public Account() {
    }

    public Account(Long numberAccount, Long amount) {
        this.numberAccount = numberAccount;
        this.amount = amount;
    }

    public Account(Long numberAccount) {
        this.numberAccount = numberAccount;
    }

    //    public Account(Long numberAccount, Long amount, Driver driver) {
//        this.numberAccount = numberAccount;
//        this.amount = amount;
//        this.driver = driver;
//    }


}
