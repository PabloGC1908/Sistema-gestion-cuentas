package com.chepita.gestorcuentas.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
    @OneToMany(mappedBy = "proveedor")
    private Set<Cuenta> cuentas;
}
