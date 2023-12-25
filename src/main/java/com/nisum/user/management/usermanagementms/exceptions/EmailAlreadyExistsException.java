package com.nisum.user.management.usermanagementms.exceptions;

public class EmailAlreadyExistsException extends RuntimeException
{
    public EmailAlreadyExistsException() {
        super("El correo ya se encuentra registrado");
    }
}
