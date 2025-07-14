package com.elijahhelmandollar.expensiph.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "total")
    private double total;

    @Column(name = "description")
    private String description;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Initializing constructor.
    public Expense() {}

    // Accessors and mutators.
    public long getId() {

        return this.id;

    }

    public void setId(long id) {

        this.id = id;

    }

    public String getTitle() {

        return this.title;

    }

    public void setTitle(String title) {

        this.title = title;

    }

    public double getTotal() {

        return this.total;

    }

    public void setTotal(double total) {

        this.total = total;

    }

    public String getDescription() {

        return this.description;

    }

    public void setDescription(String description) {

        this.description = description;

    }

    public LocalDate getPurchaseDate() {

        return this.purchaseDate;

    }

    public void setPurchaseDate(LocalDate purchaseDate) {

        this.purchaseDate = purchaseDate;

    }

    public LocalDate getCreationDate() {

        return this.creationDate;

    }

    public void setCreationDate(LocalDate creationDate) {

        this.creationDate = creationDate;

    }

    public LocalDate getUpdatedDate() {

        return this.updatedDate;

    }

    public void setUpdatedDate(LocalDate updatedDate) {

        this.updatedDate = updatedDate;

    }

    public User getUser() {

        return this.user;

    }

    public void setUser(User user) {

        this.user = user;

    }

}