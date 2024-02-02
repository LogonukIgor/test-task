package by.logonuk.domain.entity;

import by.logonuk.domain.enums.Currency;
import by.logonuk.domain.enums.OperationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Table(name = "MANAGER", schema = "TASK")
public class Manager extends BaseEntity{

    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "quantity")
    private Double quantity;

//    @Type(type = "uuid-char")
    @Column(name = "source_account")
    private UUID source_account;

//    @Type(type = "uuid-char")
    @Column(name = "target_account")
    private UUID target_account;
}
