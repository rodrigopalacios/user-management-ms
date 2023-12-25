package com.nisum.user.management.usermanagementms.repository;

import com.nisum.user.management.usermanagementms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>
{
    List<User> findByEmail(String email);
    Optional<User> findByName(String name);

}
