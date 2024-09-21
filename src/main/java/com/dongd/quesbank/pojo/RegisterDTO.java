package com.dongd.quesbank.pojo;
import lombok.Data;

@Data
public class RegisterDTO {
    private  String uid;
    private String password;
    private int usertype;

    public RegisterDTO() {
    }

    public RegisterDTO(String uid, String salt, String password, int usertype) {
        this.uid = uid;
        this.password = password;
        this.usertype = usertype;
    }

}
