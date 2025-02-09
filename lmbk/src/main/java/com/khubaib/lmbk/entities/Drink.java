package com.khubaib.lmbk.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Drink {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, nullable = false, updatable = false, columnDefinition = "varchar(36)")
    private UUID id;
    
    @Version
    private Integer version;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Column(length = 50)
    private String drinkName;

    @NotNull
    private BigDecimal price;

    @NotNull
    private DrinkStyle drinkStyle;
    private Integer quantityOnHand;

    @NotBlank
    @NotNull
    private String upc;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "drink_category",
            joinColumns = @JoinColumn(name = "drink_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "drink")
    private Set<DrinkOrderLine> drinkOrderLines;

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getDrinks().add(this);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
        category.getDrinks().remove(this);
    }
}
