package com.dongd.quesbank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongd.quesbank.pojo.DO.UserDO;
import com.dongd.quesbank.pojo.DTO.UserInfoDTO;
import com.dongd.quesbank.pojo.DTO.LoginDTO;
import com.dongd.quesbank.pojo.DTO.RegisterDTO;
import com.dongd.quesbank.utils.Result;


public interface UserService{

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

    public Result getUsertemp();
}
