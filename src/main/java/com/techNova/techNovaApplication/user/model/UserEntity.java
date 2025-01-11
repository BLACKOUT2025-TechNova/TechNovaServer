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
    private Long phoneNumber;  // 전화번호 전체
    private String nickname; // 뒷자리 네글자
    private AccessMode accessMode;
    private Long reward;

    private static final int REWARD_POINT = 10;

    public void setAccessMode(AccessMode accessMode) {
        this.accessMode = accessMode;
    }

    public void setReward() {
        this.reward = reward + REWARD_POINT;
    }

    public UserEntity(Long phoneNumber, AccessMode accessMode, Long reward) {
        this.phoneNumber = phoneNumber;
        this.nickname = phoneNumber.toString().substring(phoneNumber.toString().length()-4);
        this.accessMode = accessMode;
        this.reward = reward;
    }
}
