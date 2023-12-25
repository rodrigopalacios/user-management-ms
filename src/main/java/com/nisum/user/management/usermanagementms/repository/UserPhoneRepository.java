package com.nisum.user.management.usermanagementms.repository;

import com.nisum.user.management.usermanagementms.model.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPhoneRepository extends JpaRepository<UserPhone, UUID>
{
}
