package com.example.customer.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    private Long id;

    @NotNull(message = "firstName may be not null")
    @NotBlank(message = "firstName may be not blank")
    private String firstName;

    @NotNull(message = "lastName may be not null")
    @NotBlank(message = "lastName may be not blank")
    private String lastName;
    private  String email;

}
