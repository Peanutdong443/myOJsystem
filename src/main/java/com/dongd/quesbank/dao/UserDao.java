package com.dongd.quesbank.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dongd.quesbank.pojo.StuListItem;
import com.dongd.quesbank.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
    public List<StuListItem> getStuListById(@Param("tid")int tid,@Param("sid")int sid);
    public List<StuListItem> getStuListByName(@Param("tid")int tid,@Param("uname")String uname);
    public int deleteStuList(@Param("tid")int tid,@Param("sid")int sid);
}