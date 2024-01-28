package com.pgc.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_status")
@Entity
public class AccountStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(length = 30)
    private String status;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<Account> accounts;
}
