package com.chepita.gestorcuentas.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "correo_cliente")
    private String correoCliente;

    @ManyToOne
    @JoinColumn(name = "id_pais")
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "id_medio_pago")
    private MedioPago medioPago;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "cliente")
    private Set<Cuenta> cuentas;
}
