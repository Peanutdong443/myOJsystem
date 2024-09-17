package com.dongd.quesbank.pojo;
import lombok.Data;

@Data
public class RegisterForm {
    private  String uid;
    private String password;
    private int usertype;

    public RegisterForm() {
    }

    public RegisterForm(String uid, String salt, String password, int usertype) {
        this.uid = uid;
        this.password = password;
        this.usertype = usertype;
    }

}
