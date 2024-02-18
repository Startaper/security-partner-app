package com.ecommerce.securitypartnerapp.dto;

import com.ecommerce.securitypartnerapp.entities.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {

    private String lastName;
    private String firstName;
    private String middleName;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private int age;
    private boolean isActive;
    private boolean emailIsConfirm;

    public User toEntity() {
        return User.builder()
                .lastName(this.lastName)
                .firstName(this.firstName)
                .middleName(this.middleName)
                .email(this.email)
                .phone(this.phone)
                .birthDate(this.birthDate)
                .build();
    }

}
