package com.dongd.quesbank.pojo.DTO;
import lombok.Data;

@Data
public class PwdDTO {
    private String oldPassword;
    private String newPassword;

    public PwdDTO() {
    }

    public PwdDTO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
