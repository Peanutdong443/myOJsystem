package com.dongd.quesbank.pojo.VO;
import lombok.Data;

@Data
public class StuListVO {
    private int uid;
    private String username;
    private int temp;

    public StuListVO() {
    }

    public StuListVO(int uid, String username, int temp) {
        this.uid = uid;
        this.username = username;
        this.temp = temp;
    }
}
