package com.eventhub.eventhubapi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity representing a registration in the EventHub system.
 * A registration belongs to one user and can contain multiple registration items.
 */
@Entity
@Table(name = "registrations")
public class Registration {

    /**
     * Primary key (auto-generated).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Date and time when the registration was created.
     */
    @Column(nullable = false)
    private LocalDateTime registrationDate;

    /**
     * Many registrations can belong to one user.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * One registration can contain many registration items.
     */
    @OneToMany(mappedBy = "registration")
    private List<RegistrationItem> items = new ArrayList<>();

    /**
     * Default constructor required by JPA.
     */
    public Registration() {
    }

    /**
     * Constructor used to create a registration object.
     */
    public Registration(Long id, LocalDateTime registrationDate, User user) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<RegistrationItem> getItems() {
        return items;
    }

    public void setItems(List<RegistrationItem> items) {
        this.items = items;
    }
}