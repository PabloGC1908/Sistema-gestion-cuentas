package com.chepita.gestorcuentas.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_registro")
    private Date fechaRegistro;
    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    @Column(name = "correo_cuenta")
    private String correoCuenta;
    @Column(name = "contrasenia")
    private String contrasenia;
    @ManyToOne
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;
}
