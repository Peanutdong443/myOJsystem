package com.dongd.quesbank.service.Impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dongd.quesbank.aop.TokenVerify;
import com.dongd.quesbank.dao.UserDao;
import com.dongd.quesbank.pojo.DO.UserDO;
import com.dongd.quesbank.pojo.DTO.LoginDTO;
import com.dongd.quesbank.pojo.DTO.RegisterDTO;
import com.dongd.quesbank.pojo.DTO.UserInfoDTO;
import com.dongd.quesbank.pojo.VO.StuListVO;
import com.dongd.quesbank.service.UserService;
import com.dongd.quesbank.utils.JwtHelper;
import com.dongd.quesbank.utils.MD5Util;
import com.dongd.quesbank.utils.Result;
import com.dongd.quesbank.utils.ResultCodeEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dongd.quesbank.aop.aspect.tokenVerifyAspect.uidThreadLocal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    JwtHelper jwtHelper;

    public static boolean isNumeric(String strNum) {
        try {
            Integer.parseInt(strNum);
            return true;
        } catch (NumberFormatException | NullPointerException e1) {
           return false;
        }
    }

    @Override
    public Result login(LoginDTO loginForm) {

        UserDO us=userDao.selectById(Long.valueOf(loginForm.getUid()));
        System.out.println("User: "+us);
        System.out.println(MD5Util.encrypt(loginForm.getPassword()));
        if(us==null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        if(!StringUtils.isEmpty(loginForm.getPassword())&& MD5Util.encrypt(loginForm.getPassword()).equals(us.getPassword())){
            if(us.getUsertype()!=loginForm.getUsertype())return Result.build(null,ResultCodeEnum.USERTYPE_FAULT);


            String token=jwtHelper.createToken((long)us.getUid());
            Map<String,String> data=new HashMap();
            data.put("token",token);

            QueryWrapper<UserDO> queryWrapper=new QueryWrapper<UserDO>().select("avatarurl").eq("uid",us.getUid());

            String avatarUrl=userDao.selectOne(queryWrapper).getAvatarurl();

            String url="http://"+avatarUrl;

            data.put("avatarUrl",url);

            return Result.ok(data);
        }
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result checkUserId(String uid) {

        if(uid.equals("null"))return Result.ok(null);

        QueryWrapper<UserDO> Wrapper=new QueryWrapper<UserDO>().select("uid").eq("uid",Integer.parseInt(uid));
        int count=userDao.selectCount(Wrapper).intValue();

        if(count==0) return Result.ok(null);
        return Result.build(null,ResultCodeEnum.USERID_EXIST);
    }

    @Override
    public Result register(RegisterDTO registerDTO) {

        QueryWrapper<UserDO> Wrapper=new QueryWrapper<UserDO>().select("uid").eq("uid",Integer.parseInt(registerDTO.getUid()));
        int count=userDao.selectCount(Wrapper).intValue();

        if(count>0) return Result.build(null,ResultCodeEnum.USERID_EXIST);

        registerDTO.setPassword(MD5Util.encrypt(registerDTO.getPassword()));

        userDao.insert(new UserDO(registerDTO));

        return Result.ok(null);
    }

    @TokenVerify
    @Override
    public Result getUserById() {

        int uid=uidThreadLocal.get();

        UserDO userDO = userDao.selectById(uid);

        userDO.setPassword("");

        Map data=new HashMap();
        data.put("User", userDO);

        return Result.ok(data);
    }

    @TokenVerify
    @Override
    public Result changepwd(String oldpassword,String newpassword) {

        int uid=uidThreadLocal.get();

        UserDO userDO = userDao.selectById(uid);

        if(userDO ==null){
            System.out.println("未找到该用户");
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        else if(MD5Util.encrypt(oldpassword).equals(userDO.getPassword())){

            UpdateWrapper<UserDO> wrapper = new UpdateWrapper<UserDO>().set("password",MD5Util.encrypt(newpassword)).eq("uid",uid);

            userDao.update(wrapper);

            System.out.println("密码修改成功");

            return Result.ok(null);
        }else{
            System.out.println("原密码错误");
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
    }

    @Override
    @TokenVerify
    public Result changeInfo(UserInfoDTO userInfoDTO){
        int uid=uidThreadLocal.get();

        UserDO userDO = userDao.selectById(uid);

        UpdateWrapper<UserDO> wrapper = new UpdateWrapper<UserDO>().set("username", userInfoDTO.getUsername()).set("sign", userInfoDTO.getSign()).eq("uid",uid);

        userDao.update(wrapper);

        System.out.println("个人信息修改成功");

        return Result.ok(null);
    }


    @TokenVerify
    @Override
    public Result getStuList(String id, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        int uid = uidThreadLocal.get();

        int sid=0;
        String uname=null;
        List<StuListVO> stuList=null;

        if(id!=null) {
           if(isNumeric(id)) {
               sid=Integer.parseInt(id);
               stuList=userDao.getStuListById(uid,sid);
           }else{
               uname=id;
               stuList=userDao.getStuListByName(uid,uname);
           }
        }else{
            stuList=userDao.getStuListById(uid,sid);
        }

        PageInfo<StuListVO> pageInfo = new PageInfo(stuList);
        HashMap<String, Object> res = new HashMap();
        res.put("total", pageInfo.getTotal());
        res.put("list", pageInfo.getList());

        return Result.ok(res);
    }

    @TokenVerify
    @Override
    public Result deleteStuList(String id) {
        int tid = uidThreadLocal.get();
        int sid=Integer.parseInt(id);
        userDao.deleteStuList(tid,sid);

        return Result.ok(null);
    }

    @TokenVerify
    @Override
    public Result resetPwd(String id) {
        int sid=Integer.parseInt(id);
        UpdateWrapper<UserDO> wrapper = new UpdateWrapper<UserDO>().set("password", "2dc030a6179f5bd35fa80dfeb43254a4").eq("uid", sid);
        userDao.update(wrapper);
        return Result.ok(null);
    }

    @TokenVerify
    @Override
    public Result getUsertype() {

        int uid=uidThreadLocal.get();
        UserDO us=userDao.selectById(uid);

        Map<String,Integer> data=new HashMap<>();

        data.put("usertype",us.getUsertype());

        return Result.ok(data);
    }

}
