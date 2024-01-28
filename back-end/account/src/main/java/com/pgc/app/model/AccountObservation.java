package com.pgc.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_observation")
public class AccountObservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(length = 30)
    private String observation;

    @OneToMany(mappedBy = "observation", fetch = FetchType.LAZY)
    private List<Account> accounts;
}
