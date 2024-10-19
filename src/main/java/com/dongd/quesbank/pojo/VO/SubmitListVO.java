package com.dongd.quesbank.pojo.VO;

import lombok.Data;

@Data
public class SubmitListVO {
    private String sbmid;
    private int languageid;
    private String sourcecode;
    private int status;
    private int passednum;

    public SubmitListVO() {
    }

    public SubmitListVO(String sbmid, String sourcecode, int status, int languageid, int passednum) {
        this.sbmid = sbmid;
        this.sourcecode = sourcecode;
        this.status = status;
        this.languageid = languageid;
        this.passednum = passednum;
    }
}
