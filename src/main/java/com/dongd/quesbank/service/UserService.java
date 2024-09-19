package com.dongd.quesbank.service;

import com.dongd.quesbank.pojo.InfoForm;
import com.dongd.quesbank.pojo.LoginForm;
import com.dongd.quesbank.pojo.RegisterForm;
import com.dongd.quesbank.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


public interface UserService {

    public Result login(LoginForm loginForm);

    public Result checkUserId(String uid);

    public Result register(RegisterForm registerForm);

    public Result getUserById();

    public Result changepwd(String oldpassword,String newpassword);

    public Result changeInfo(InfoForm infoForm);

    public Result getStuList(String sid, int pageNum, int pageSize);

    public Result deleteStuList(String sid);
}
