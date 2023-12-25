package com.nisum.user.management.usermanagementms.exceptions;

public class UserNotFoundException extends RuntimeException
{
    private String id;

    public UserNotFoundException(String id) {
        super("Could not find user of id " + id);
    }
}
