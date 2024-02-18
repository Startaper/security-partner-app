package com.ecommerce.securitypartnerapp.models;

import com.ecommerce.securitypartnerapp.dto.UserDTO;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RequestRegistration {

    private String lastName;
    private String firstName;
    private String middleName;
    private String email;
    private String phone;
    private String password;
    private String passwordRepeat;
    private LocalDate birthDate;
    private boolean emailIsValid;
    private boolean phoneIsValid;

    public UserDTO toDTO() {
        LocalDate now = LocalDate.now();
        return UserDTO.builder()
                .lastName(this.lastName)
                .firstName(this.firstName)
                .middleName(this.middleName)
                .email(this.email)
                .phone(this.phone)
                .birthDate(this.birthDate)
                .age(Period.between(birthDate, now).getYears())
                .isActive(true)
                .build();
    }

}
