package com.dongd.quesbank.pojo;
import lombok.Data;

@Data
public class PwdForm {
    private String oldPassword;
    private String newPassword;

    public PwdForm() {
    }

    public PwdForm(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
