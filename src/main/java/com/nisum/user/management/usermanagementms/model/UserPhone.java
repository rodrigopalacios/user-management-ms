package com.nisum.user.management.usermanagementms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "user_phones")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPhone
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long number;
    @Column(name = "city_code")
    private Integer cityCode;
    @Column(name = "country_code")
    private Integer countryCode;
    /*
    @ManyToOne()
    @JoinColumn(name = "users_id")
    private User user;
    */
}
