package com.dongd.quesbank.pojo;
import lombok.Data;

@Data
public class UserInfoDTO {
    private String username;
    private String sign;

    public UserInfoDTO() {
    }

    public UserInfoDTO(String username, String sign) {
        this.username = username;
        this.sign = sign;
    }
}
