package com.dongd.quesbank.pojo;
import lombok.Data;

@Data
public class LoginDTO {
    private String uid;
    private String password;
    private int usertype;

    public LoginDTO() {
    }

    public LoginDTO(String uid, String password, int usertype) {
        this.uid = uid;
        this.password = password;
        this.usertype = usertype;
    }

}
