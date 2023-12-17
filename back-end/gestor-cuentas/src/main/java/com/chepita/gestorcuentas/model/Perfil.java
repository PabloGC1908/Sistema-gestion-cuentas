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
@Table(name = "Perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "perfil")
    private String perfil;
    @OneToMany(mappedBy = "perfil")
    @JsonIgnore
    private Set<Cuenta> cuentas;
}
