package com.elijahhelmandollar.expensiph.entity;

import java.util.List;
import java.time.LocalDate;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash")
    private String password;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Expense> expenses;

    // Initializing constructor.
    public User() {}

    // Accessors and mutators.
    public Long getId() {

        return this.id;

    }

    public void setId(long id) {

        this.id = id;

    }

    public String getUsername() {

        return this.username;

    }

    public void setUsername(String username) {

        this.username = username;

    }

    public String getPassword() {

        return this.password;

    }

    public void setPassword(String password) {

        this.password = password;

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

    public List<Expense> getExpenses() {

        return this.expenses;

    }

    public void setExpenses(List<Expense> expenses) {

        this.expenses = expenses;

    }

}