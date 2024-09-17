package com.dongd.quesbank.pojo;
import lombok.Data;

@Data
public class StuListItem {
    private int uid;
    private String username;
    private int temp;

    public StuListItem() {
    }

    public StuListItem(int uid, String username, int temp) {
        this.uid = uid;
        this.username = username;
        this.temp = temp;
    }
}
