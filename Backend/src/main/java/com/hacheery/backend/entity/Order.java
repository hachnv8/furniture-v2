package com.hacheery.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by HachNV on 17/04/2023
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    // Getters and setters
}

