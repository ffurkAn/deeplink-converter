package com.atanriverdi.deeplinkconterter.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "transaction_log")
public class TransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String requestBody;

    private String responseBody;

    private LocalDateTime creationDate;

    @PrePersist
    void setCreationDate() {
        setCreationDate(LocalDateTime.now());
    }

}
