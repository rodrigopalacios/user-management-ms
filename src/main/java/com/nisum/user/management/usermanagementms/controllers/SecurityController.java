package com.nisum.user.management.usermanagementms.controllers;

import com.nisum.user.management.usermanagementms.api.request.AuthRequest;
import com.nisum.user.management.usermanagementms.api.response.GetTokenResponse;
import com.nisum.user.management.usermanagementms.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class SecurityController
{
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value="/getToken", produces="application/json")
    public GetTokenResponse getToken(@RequestBody AuthRequest authRequest)
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated())
        {
            return GetTokenResponse.builder()
                .token(jwtService.generateToken(authRequest.getUsername()))
                .build();
        }
        else
        {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
