package com.dongd.quesbank.pojo;
import lombok.Data;

@Data
public class LoginForm {
    private String uid;
    private String password;
    private int usertype;

    public LoginForm() {
    }

    public LoginForm(String uid, String password, int usertype) {
        this.uid = uid;
        this.password = password;
        this.usertype = usertype;
    }

}
