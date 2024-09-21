package com.dongd.quesbank.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dongd.quesbank.pojo.VO.StuListVO;
import com.dongd.quesbank.pojo.DO.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao extends BaseMapper<UserDO> {
    public List<StuListVO> getStuListById(@Param("tid")int tid, @Param("sid")int sid);
    public List<StuListVO> getStuListByName(@Param("tid")int tid, @Param("uname")String uname);
    public int deleteStuList(@Param("tid")int tid,@Param("sid")int sid);
}