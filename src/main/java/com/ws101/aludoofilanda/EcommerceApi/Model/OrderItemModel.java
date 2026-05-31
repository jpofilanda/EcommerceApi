package com.ws101.aludoofilanda.EcommerceApi.Model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a single line item inside an Order.
 * Links an Order to a Product.
 * Many OrderItems belong to one Order, and many OrderItems reference one Product.
 */
@Entity
@Table(name = "order_items")
@Getter
@Setter
@ToString(exclude = {"order", "product"})
@EqualsAndHashCode(exclude = {"order", "product"})
@NoArgsConstructor
public class OrderItemModel {

    /**
     * Auto-generated primary key for the order item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Quantity of the product ordered.
     */
    private Integer quantity;

    /**
     * Price of the product at the time of purchase.
     * Stored separately in case product price changes later.
     */
    private Double priceAtPurchase;

    /**
     * The order this item belongs to.
     * Many OrderItems belong to one Order (Many-to-One relationship).
     * FetchType.LAZY means order is only loaded when accessed.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderModel order;

    /**
     * The product this order item references.
     * Many OrderItems can reference one Product (Many-to-One relationship).
     * FetchType.LAZY means product is only loaded when accessed.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel product;
}