package com.hacheery.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue
    private Long id;

    private Long orderId;

    private Long productId;

    private int quantity;
}
