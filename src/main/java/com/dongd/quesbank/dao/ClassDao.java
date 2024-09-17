package com.dongd.quesbank.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dongd.quesbank.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClassDao  extends BaseMapper<User> {

}
