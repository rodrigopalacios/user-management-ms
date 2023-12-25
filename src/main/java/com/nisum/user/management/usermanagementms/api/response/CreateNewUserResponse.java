package com.nisum.user.management.usermanagementms.api.response;

import com.nisum.user.management.usermanagementms.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateNewUserResponse extends BaseResponse
{
    private User user;
}
