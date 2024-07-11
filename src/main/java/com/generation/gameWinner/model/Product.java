package com.generation.gameWinner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation.gameWinner.utils.ValidationPrice;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "O campo nome é obrigatório")
    private String name;
    @NotBlank(message = "O campo descrição é obrigatório")
    private String description;
    @ValidationPrice
    private double price;

    private int stock;
    private String characteristic;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"products"})
    private Category category;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Characteristics getCharacteristic() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(characteristic, Characteristics.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter caracteristicas do JSON", e);
        }
    }

    public void setCharacteristic(Characteristics characteristic) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String characteristics = mapper.writeValueAsString(characteristic);
            this.characteristic = characteristics;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter caracteristicas para JSON", e);
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
