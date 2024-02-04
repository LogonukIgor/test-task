package by.logonuk.repository;

import by.logonuk.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByUuid(UUID accountNumber);

    Optional<Account> findByUuidAndCode(UUID accountNumber, String code);
}
