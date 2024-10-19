package com.dongd.quesbank.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dongd.quesbank.pojo.DO.SubmitDO;
import com.dongd.quesbank.pojo.VO.SubmitListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SubmitDao extends BaseMapper<SubmitDO> {
    public List<SubmitListVO> getSubmitList(@Param("uid")int uid, @Param("qid")int qid);
}
