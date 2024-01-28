package com.pgc.app.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_date")
    private Date registrationDate;

    private String provider;

    @ManyToOne
    @JoinColumn(name = "id_account_info")
    private AccountInfo accountInfo;

    @Column(name = "id_customer")
    private Long customer;

    @Column(name = "customer_membership_end_date")
    private Date customerMembershipEndDate;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private AccountStatus status;

    @ManyToOne
    @JoinColumn(name = "id_observation")
    private AccountObservation observation;

    private Boolean isWork;
}
