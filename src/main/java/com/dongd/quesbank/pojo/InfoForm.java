package com.dongd.quesbank.pojo;
import lombok.Data;

@Data
public class InfoForm {
    private String username;
    private String sign;

    public InfoForm() {
    }

    public InfoForm(String username, String sign) {
        this.username = username;
        this.sign = sign;
    }
}
