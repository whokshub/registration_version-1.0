package com.api.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegistrationDto {

    @NotEmpty
    @Size(min = 2, message = "Name Should contain minimum two character")
    private String name;

    @Email
    private String email;

    @Size(min = 10,max = 10,message = "mobile number should contain 10 numbers in it")
    private String mobile;

}