package com.nisum.user.management.usermanagementms.api.response;

import com.nisum.user.management.usermanagementms.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetUserByIdResponse extends BaseResponse
{
    private Optional<User> user;
}
