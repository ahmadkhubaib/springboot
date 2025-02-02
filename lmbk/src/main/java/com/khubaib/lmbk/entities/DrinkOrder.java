package com.khubaib.lmbk.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
public class DrinkOrder {
    public DrinkOrder(UUID id, Long version, String customerRef, Timestamp createdDate, Timestamp lastModifiedDate, Customer customer, Set<DrinkOrderLine> drinkOrderLines) {
        this.id = id;
        this.version = version;
        this.customerRef = customerRef;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.setCustomer(customer);
        this.drinkOrderLines = drinkOrderLines;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(nullable = false, updatable = false, length = 36, columnDefinition = "varchar(36)")
    private UUID id;

    @Version
    private Long version;

    private String customerRef;

    public boolean isNew(){return this.id == null;}

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    @ManyToOne
    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.getDrinkOrders().add(this);
    }

    @OneToMany(mappedBy = "drinkOrder")
    private Set<DrinkOrderLine> drinkOrderLines;
}