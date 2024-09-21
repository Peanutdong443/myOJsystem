package com.dongd.quesbank.service;

import com.dongd.quesbank.pojo.UserInfoDTO;
import com.dongd.quesbank.pojo.LoginDTO;
import com.dongd.quesbank.pojo.RegisterDTO;
import com.dongd.quesbank.utils.Result;


public interface UserService {

    public Result login(LoginDTO loginForm);

    public Result checkUserId(String uid);

    public Result register(RegisterDTO registerDTO);

    public Result getUserById();

    public Result changepwd(String oldpassword,String newpassword);

    public Result changeInfo(UserInfoDTO userInfoDTO);

    public Result getStuList(String sid, int pageNum, int pageSize);

    public Result deleteStuList(String sid);

    public Result resetPwd(String sid);

    public Result getUsertype();
}
