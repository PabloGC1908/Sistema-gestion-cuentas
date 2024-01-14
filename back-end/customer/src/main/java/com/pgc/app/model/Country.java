package com.pgc.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "country_code", length = 10)
    private String countryCode;

    @Column(name = "country_name", length = 50)
    private String countryName;

    @Column(name = "iso_code", length = 5)
    private String isoCode;

    @JsonIgnore
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Customer> customers;
}
