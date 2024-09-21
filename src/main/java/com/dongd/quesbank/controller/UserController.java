package com.dongd.quesbank.controller;
import com.dongd.quesbank.pojo.DTO.UserInfoDTO;
import com.dongd.quesbank.pojo.DTO.LoginDTO;
import com.dongd.quesbank.pojo.DTO.PwdDTO;
import com.dongd.quesbank.pojo.DTO.RegisterDTO;
import com.dongd.quesbank.service.Impl.UserServiceImpl;
import com.dongd.quesbank.service.PicUploadService;
import com.dongd.quesbank.utils.JwtHelper;
import com.dongd.quesbank.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
@CrossOrigin({"http://localhost:8080"})
public class UserController {


    @Autowired
    private UserServiceImpl usi;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private PicUploadService pls;

    @PostMapping ("/login")
    public Result login(@RequestBody LoginDTO loginForm){
        Result result = usi.login(loginForm);
        return result;
    }


    @PostMapping("/checkUserId")
    public Result checkUserId(@RequestBody String accountId){
        Result result = usi.checkUserId(accountId);
        return result;
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterDTO registerDTO){
        Result result = usi.register(registerDTO);
        return result;
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(){

        Result result = usi.getUserById();
        return result;
    }

    @PostMapping("/changepwd")
    public Result changepwd(@RequestBody PwdDTO pwdDTO){
        Result result = usi.changepwd(pwdDTO.getOldPassword(), pwdDTO.getNewPassword());
        return result;
    }


    @PostMapping("/changeInfo")
    public Result changeInfo(@RequestBody UserInfoDTO userInfoDTO){
        Result result = usi.changeInfo(userInfoDTO);
        return result;
    }


    //请求的接口
    @PostMapping("/changeAvatar")
    public Result changeAvatar(@RequestParam("avatar") MultipartFile avatar) throws IOException {


        if (avatar.isEmpty()) {
            System.out.println("Avatar upload unsuccessful");
            return null;
        }

        Result result = pls.AvatarUpload(avatar);
        return result;
    }

    @GetMapping("/getStuList")
    public Result getStuList(String sid, int pageNum, int pageSize) {
        Result result = usi.getStuList(sid,pageNum,pageSize);
        return result;
    }

    @DeleteMapping("/deleteStuList")
    public Result deleteStuList(@RequestParam("sid") String sid) {
        Result result = usi.deleteStuList(sid);
        return result;
    }
    @GetMapping("/resetPwd")
    public Result resetPwd(@RequestParam("sid") String sid) {
        Result result = usi.resetPwd(sid);
        return result;
    }

    @GetMapping("/getUsertype")
    public Result getUsertype(){
        Result result = usi.getUsertype();
        return result;
    }


}
