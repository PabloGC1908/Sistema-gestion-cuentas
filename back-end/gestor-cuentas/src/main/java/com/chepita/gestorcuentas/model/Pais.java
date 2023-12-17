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
@Table(name = "Pais")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_pais")
    private String nombrePais;
    @Column(name = "abreviatura")
    private String abreviatura;
    @OneToMany(mappedBy = "pais")
    @JsonIgnore
    private Set<Cliente> clientes;
}
