package com.market.connect.models.dtos;

import com.market.connect.utils.Subscription;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerDTO {

    private Long id;
    @NotBlank(message = "Invalid first name.")
    private String firstName;
    @NotBlank(message = "Invalid last name.")
    private String lastName;
    @NotBlank(message = "Invalid email.")
    @Email(message = "Invalid email.")
    private String email;

    private boolean isActive;

    private String city;

    private Subscription subscription;
}
