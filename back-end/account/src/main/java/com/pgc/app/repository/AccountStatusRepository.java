package com.pgc.app.repository;

import com.pgc.app.model.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountStatusRepository extends JpaRepository<AccountStatus, Byte> {
}
