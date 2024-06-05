package com.euphoria_ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String region;
    private String state;
    private String city;
    private String street;
    private String companyName;
    private String homeType;
    private String postalCode;
    private String phone;

    @JsonBackReference
    @OneToOne(mappedBy = "address")
    private User user;
}
