package com.dongd.quesbank.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Submit")
public class SubmitDO {
    @TableId(value = "sbmid")
    private String sbmid;
    private int qid;
    private int uid;
    private int languageid;
    private String sourcecode;
    private int status;
    private int passednum;

    public SubmitDO() {
    }

    public SubmitDO(int qid, int uid,int languageid, String sourcecode) {
        this.uid=uid;
        this.qid = qid;
        this.languageid = languageid;
        this.sourcecode = sourcecode;
    }

    public SubmitDO(String sbmid, int qid, int uid, String sourcecode, int languageid, int passednum, int status) {
        this.sbmid = sbmid;
        this.qid = qid;
        this.uid = uid;
        this.sourcecode = sourcecode;
        this.languageid = languageid;
        this.passednum = passednum;
        this.status = status;
    }
}
