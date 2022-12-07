package com.bms.devbank.model;

import com.bms.devbank.helper.MessageHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    @NotEmpty(message = MessageHelper.FIRST_NAME_CANNOT_BE_EMPTY)
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = MessageHelper.LAST_NAME_CANNOT_BE_EMPTY)
    private String lastName;

    @Column(name = "national_id", nullable = false, length = 11)
    @NotEmpty(message = MessageHelper.NATIONAL_ID_CANNOT_BE_EMPTY)
    private String nationalId;

    @Column(name = "email", nullable = false)
    @Email(message = MessageHelper.EMAIL_NOT_VALID,
            regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @NotEmpty(message = MessageHelper.EMAIL_CANNOT_BE_EMPTY)
    private String email;

    @Column(name = "password")
    @NotEmpty(message = MessageHelper.PASSWORD_CANNOT_BE_EMPTY)
    private String password;

    @Column(name = "phone_number")
    @Pattern(regexp = "^(\\+90|0)?[0-9]{10}$",
            message = MessageHelper.PHONE_NUMBER_NOT_VALID)
    @NotEmpty(message = MessageHelper.PHONE_NUMBER_CANNOT_BE_EMPTY)
    private String phone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Account> accounts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
