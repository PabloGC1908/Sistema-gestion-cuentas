package com.pgc.app.repository;

import com.pgc.app.model.AccountObservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountObservationRepository extends JpaRepository<AccountObservation, Byte> {
}
