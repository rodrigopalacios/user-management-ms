package com.nisum.user.management.usermanagementms.api.request;

import com.nisum.user.management.usermanagementms.api.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewUserRequest
{
    private String name;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    //@Pattern(regexp = "$\\{nisum.password.pattern\\}", message = "Password no cumple el formato definido en \\{nisum.password.pattern\\")
    @Pattern(regexp = "^[0-9]*", message = "Password no cumple el formato definido")
    private String password;
    private List<Phone> phones;
}
