package com.bms.devbank.model;

import com.bms.devbank.helper.MessageHelper;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "acounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    @NotEmpty(message= MessageHelper.ACCOUNT_TYPE_NOT_EMPTY)
    private AccountType accountType;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    @NotEmpty(message= MessageHelper.ACCOUNT_STATUS_NOT_EMPTY)
    private AccountStatus accountStatus;

    @Column(name = "account_limit")
    private Double accountLimit;

    @Column(name = "account_limit_type")
    @Enumerated(EnumType.STRING)
    @NotEmpty(message= MessageHelper.ACCOUNT_LIMIT_TYPE_NOT_EMPTY)
    private AccountLimitType accountLimitType;

    @Column(name = "account_limit_status")
    @Enumerated(EnumType.STRING)
    @NotEmpty(message= MessageHelper.ACCOUNT_LIMIT_STATUS_NOT_EMPTY)
    private AccountLimitStatus accountLimitStatus;

    @Column(name = "account_limit_amount")
    private Double accountLimitAmount;

    @Column(name = "account_limit_start_date")
    private LocalDate accountLimitStartDate;

    @Column(name = "account_limit_end_date")
    private LocalDate accountLimitEndDate;

    @Column(name = "account_limit_created_at")
    private LocalDateTime accountLimitCreatedAt;

    @Column(name = "account_limit_updated_at")
    private LocalDateTime accountLimitUpdatedAt;

    @Column(name = "account_limit_deleted_at")
    private LocalDateTime accountLimitDeletedAt;

    @Column(name = "account_limit_is_active")
    private Boolean accountLimitIsActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return id != null && Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
