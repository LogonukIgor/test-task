package by.logonuk.domain.entity;

import by.logonuk.domain.enums.Currency;
import by.logonuk.domain.enums.OperationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
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

    @Column(name = "SOURCE_ACCOUNT")
    private UUID source_account;

    @Column(name = "TARGET_ACCOUNT")
    private UUID target_account;

    @Column(name = "CREATION_DATE")
    private Timestamp creationDate;

    @Column(name = "MODIFICATION_DATE")
    private Timestamp modificationDate;
}
