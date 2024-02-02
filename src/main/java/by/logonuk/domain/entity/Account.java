package by.logonuk.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "ACCOUNT", schema = "TASK")
public class Account {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "currency", length = 3, nullable = false)
    private String currency = "RUB";

    @Column(name = "balance")
    private Double balance;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
