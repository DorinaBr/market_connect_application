package com.market.connect.models.entities;

import com.market.connect.utils.Subscription;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "city")
    private String city;
    @Column(name = "subscription")
    private Subscription subscription;
    @OneToMany (mappedBy = "customer")
    private List<Order> orders;
}
