package com.techNova.techNovaApplication.user.dto;

import com.techNova.techNovaApplication.user.model.AccessMode;
import com.techNova.techNovaApplication.user.model.UserEntity;
import lombok.Getter;

@Getter
public class UserDto {

    private String email;
//    private String nickname;
    private AccessMode accessMode;

    public UserEntity toEntity() {
        return new UserEntity(email, accessMode, 0L);
    }
}
