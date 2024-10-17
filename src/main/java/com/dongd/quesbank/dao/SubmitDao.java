package com.dongd.quesbank.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dongd.quesbank.pojo.DO.SubmitDO;
import com.dongd.quesbank.pojo.DO.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SubmitDao extends BaseMapper<SubmitDO> {

}
