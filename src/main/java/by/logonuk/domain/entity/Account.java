package by.logonuk.domain.entity;

import by.logonuk.domain.enums.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "ACCOUNT", schema = "TASK")
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "UUID")
    private UUID uuid;

    @Column(name = "CODE")
    private String code;

    @Column(name = "CURRENCY")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "BALANCE")
    private Double balance;

    @Column(name = "CREATION_DATE")
    private Timestamp creationDate;

    @Column(name = "MODIFICATION_DATE")
    private Timestamp modificationDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "sourceAccount", cascade = CascadeType.ALL)
    private List<Manager> sourceManagers;
}
