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
@Table(name = "Categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_categoria", nullable = false, length = 100)
    private String nombreCategoria;
    @OneToMany(mappedBy = "categoria")
    private Set<Cuenta> cuentas;
}
