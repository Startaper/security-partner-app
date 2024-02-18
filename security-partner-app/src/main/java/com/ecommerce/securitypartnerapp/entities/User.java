package com.ecommerce.securitypartnerapp.entities;

import com.ecommerce.securitypartnerapp.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "t_users", schema = "db_fa_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    @Column(name = "email_is_confirm", nullable = false)
    private boolean emailIsConfirm;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", schema = "db_fa_users",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    public UserDTO toDTO() {
        return UserDTO.builder()
                .lastName(this.lastName)
                .firstName(this.firstName)
                .middleName(this.middleName)
                .email(this.email)
                .phone(this.phone)
                .birthDate(this.birthDate)
                .age(this.age)
                .isActive(this.isActive)
                .emailIsConfirm(this.emailIsConfirm)
                .build();
    }

}
