package com.nisum.user.management.usermanagementms.services;

import com.nisum.user.management.usermanagementms.api.Phone;
import com.nisum.user.management.usermanagementms.api.request.NewUserRequest;
import com.nisum.user.management.usermanagementms.exceptions.EmailAlreadyExistsException;
import com.nisum.user.management.usermanagementms.model.User;
import com.nisum.user.management.usermanagementms.model.UserPhone;
import com.nisum.user.management.usermanagementms.repository.UserPhoneRepository;
import com.nisum.user.management.usermanagementms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPhoneRepository userPhoneRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id)
    {
        return userRepository.findById(UUID.fromString(id));
    }

    public User save(NewUserRequest newUser)
    {
        List<User> users = userRepository.findByEmail(newUser.getEmail());
        if (users !=null && users.size()> 0)
        {
                throw new EmailAlreadyExistsException();
        }

        User user = User.builder()
                .name(newUser.getName())
                .email(newUser.getEmail())
                .password(encoder.encode(newUser.getPassword()))
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(Boolean.TRUE)
                .token(UUID.randomUUID())
                .phones(buildUserPhoneList(newUser))
                .build();

        return userRepository.save(user);
    }

    public User save(NewUserRequest newUser, String id)
    {
        return userRepository.findById(UUID.fromString(id))
                .map(item -> {

                    for (UserPhone phone : item.getPhones())
                    {
                        userPhoneRepository.delete(phone);
                    }

                    item.setName(newUser.getName());
                    item.setEmail(newUser.getEmail());
                    item.setPassword(newUser.getPassword());

                    item.setPhones(buildUserPhoneList(newUser));
                    item.setLastLogin(LocalDateTime.now());
                    item.setModified(LocalDateTime.now());
                    item.setIsActive(Boolean.TRUE);
                    return userRepository.save(item);
                })
                .orElseGet(() -> {
                    User user = User.builder().id(UUID.fromString(id))
                    .name(newUser.getName())
                    .email(newUser.getEmail())
                    .password(newUser.getPassword())
                    .phones(buildUserPhoneList(newUser))
                    .created(LocalDateTime.now())
                    .lastLogin(LocalDateTime.now())
                    .isActive(Boolean.TRUE)
                    .build();
                    return userRepository.save(user);
                });
    }


    private List<UserPhone> buildUserPhoneList(NewUserRequest newUser)
    {
        if (newUser.getPhones() == null){
            return null;
        }
        List<UserPhone> userPhoneList = new ArrayList<>();
        for(Phone fromPhone: newUser.getPhones())
        {
            if(fromPhone != null) {
                UserPhone userPhone = UserPhone.builder()
                        .number(fromPhone.getNumber())
                        .cityCode(fromPhone.getCityCode())
                        .countryCode(fromPhone.getCountryCode())
                        .build();
                userPhoneList.add(userPhone);
            }
        }
        return userPhoneList;
    }

    public void deleteById(String id)
    {
        userRepository.deleteById(UUID.fromString(id));
    }

}
