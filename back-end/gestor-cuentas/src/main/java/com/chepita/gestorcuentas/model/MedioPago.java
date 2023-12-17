package com.chepita.gestorcuentas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Medio_pago")
public class MedioPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "medio_pago")
    private String medioPago;
    @OneToMany(mappedBy = "medioPago")
    @JsonIgnore
    private Set<Cliente> clientes;
}
