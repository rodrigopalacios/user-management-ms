package com.nisum.user.management.usermanagementms.api.response;

import com.nisum.user.management.usermanagementms.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FindAllUsersResponse extends BaseResponse
{
    private List<User> users;
}
