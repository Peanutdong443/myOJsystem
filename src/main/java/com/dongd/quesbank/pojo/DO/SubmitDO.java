package com.dongd.quesbank.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Submit")
public class SubmitDO {
    @TableId(value = "sbmid")
    private int sbmid;
    private int qid;
    private int uid;
    private String language;
    private String sourcecode;
    private int status;
    private int runtime;
    private int runmem;

    public SubmitDO() {
    }

    public SubmitDO(int qid, int uid,String language, String sourcecode) {
        this.uid=uid;
        this.qid = qid;
        this.language = language;
        this.sourcecode = sourcecode;
    }

    public SubmitDO(int sbmid, int qid, int uid,int runmem, int runtime, int status, String sourcecode, String language) {
        this.sbmid = sbmid;
        this.qid = qid;
        this.uid=uid;
        this.runmem = runmem;
        this.runtime = runtime;
        this.status = status;
        this.sourcecode = sourcecode;
        this.language = language;
    }
}
