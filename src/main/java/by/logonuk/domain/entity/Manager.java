package by.logonuk.domain.entity;

import by.logonuk.domain.enums.Currency;
import by.logonuk.domain.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "MANAGER", schema = "TASK")
public class Manager{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "OPERATION_TYPE")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column()
    private Double quantity;

    @Column(name = "TARGET_ACCOUNT")
    private UUID targetAccount;

    @JsonIgnore
    @Column(name = "CREATION_DATE")
    private Timestamp creationDate;

    @JsonIgnore
    @Column(name = "MODIFICATION_DATE")
    private Timestamp modificationDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "SOURCE_ACCOUNT", referencedColumnName = "UUID")
    private Account sourceAccount;
}
