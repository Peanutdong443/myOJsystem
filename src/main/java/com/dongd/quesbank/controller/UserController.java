package com.dongd.quesbank.controller;
import com.dongd.quesbank.pojo.InfoForm;
import com.dongd.quesbank.pojo.LoginForm;
import com.dongd.quesbank.pojo.PwdForm;
import com.dongd.quesbank.pojo.RegisterForm;
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
    public Result login(@RequestBody LoginForm loginForm){
        Result result = usi.login(loginForm);
        return result;
    }


    @PostMapping("/checkUserId")
    public Result checkUserId(@RequestBody String accountId){
        Result result = usi.checkUserId(accountId);
        return result;
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterForm registerForm){
        Result result = usi.register(registerForm);
        return result;
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(){

        Result result = usi.getUserById();
        return result;
    }

    @PostMapping("/changepwd")
    public Result changepwd(@RequestBody PwdForm pwdForm){
        Result result = usi.changepwd(pwdForm.getOldPassword(),pwdForm.getNewPassword());
        return result;
    }


    @PostMapping("/changeInfo")
    public Result changeInfo(@RequestBody InfoForm infoForm){
        Result result = usi.changeInfo(infoForm);
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


}
