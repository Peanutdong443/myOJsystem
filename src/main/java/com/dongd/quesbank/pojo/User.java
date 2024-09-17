package com.dongd.quesbank.pojo;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("User")
public class User {
    @TableId(value = "uid")
    private int uid;
    private String username;
    private String password;
    private int usertype;
    private String sign;
    private String avatarurl;
    private int temp;

    public User() {
    }

    public User(int uid, String password, int usertype) {
        this.uid = uid;
        this.password = password;
        this.usertype = usertype;
    }
    public User(RegisterForm rf){
        this.username = rf.getUid();
        this.password = rf.getPassword();
        this.usertype = rf.getUsertype();
    }

    public User(int uid, String username, String password, int usertype, String sign, String avatarurl, int temp) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.sign = sign;
        this.avatarurl = avatarurl;
        this.temp = temp;
    }
}
