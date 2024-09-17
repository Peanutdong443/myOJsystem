package com.dongd.quesbank;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dongd.quesbank.dao.UserDao;
import com.dongd.quesbank.pojo.User;
import org.apache.ibatis.annotations.Update;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.List;

@SpringBootTest
public class MPtest {

    @Autowired
    private UserDao userDao;

    @Test
    public void test(){

//        UpdateWrapper<User> wrapper = new UpdateWrapper<User>().set().eq("uid",uid);
//
//        userDao.update()

    }




}
