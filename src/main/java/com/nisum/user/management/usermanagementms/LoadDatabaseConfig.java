package com.nisum.user.management.usermanagementms;

import com.nisum.user.management.usermanagementms.model.User;
import com.nisum.user.management.usermanagementms.model.UserPhone;
import com.nisum.user.management.usermanagementms.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class LoadDatabaseConfig
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadDatabaseConfig.class);

    @Autowired
    private PasswordEncoder encoder;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {

        return args -> {
            buildAndSaveUser(userRepository, 1);
            buildAndSaveUser(userRepository, 2);
            buildAndSaveUser(userRepository, 3);
        };
    }

    private void buildAndSaveUser(UserRepository userRepository, int id)
    {
        User user = User.builder()
                .name("Nombre Apellido" + id)
                .password(encoder.encode("123456"+id))
                .email("nombre.apellido"+id+"@gmail.com")
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(Boolean.TRUE)
                .build();
        user.setPhones(List.of(
                UserPhone.builder().countryCode(12).cityCode(30).number(9779009870L+id).build(),
                UserPhone.builder().countryCode(12).cityCode(30).number(9779002130L+id).build()
        ));
        LOGGER.info("Preloading " + userRepository.save(user));
    }
}
