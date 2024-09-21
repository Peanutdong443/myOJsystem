package com.dongd.quesbank;


import com.dongd.quesbank.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
