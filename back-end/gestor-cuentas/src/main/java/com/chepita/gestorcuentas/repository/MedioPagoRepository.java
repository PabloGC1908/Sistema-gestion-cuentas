package com.chepita.gestorcuentas.repository;

import com.chepita.gestorcuentas.model.MedioPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedioPagoRepository extends JpaRepository<MedioPago, Long> {
}
