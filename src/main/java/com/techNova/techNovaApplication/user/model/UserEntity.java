package com.techNova.techNovaApplication.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "gcoo_user")
public class UserEntity {
    @Id
    private String email;
//    private String nickname;
    private AccessMode accessMode;
    private Long reward;

    private static final int REWARD_POINT = 10;

    public void setAccessMode(AccessMode accessMode) {
        this.accessMode = accessMode;
    }

    public void setReward() {
        this.reward = reward + REWARD_POINT;
    }

    public UserEntity(String email, AccessMode accessMode, Long reward) {
        this.email = email;
        this.accessMode = accessMode;
        this.reward = reward;
    }
}
