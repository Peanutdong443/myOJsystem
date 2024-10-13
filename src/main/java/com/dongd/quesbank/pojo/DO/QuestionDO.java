package com.dongd.quesbank.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Question")
public class QuestionDO {
    @TableId(value = "qid")
    private int qid;
    private String title;
    private String content;
    private String testcase;
    private int timelimit;
    private int memlimit;

    public QuestionDO() {
    }

    public QuestionDO(int qid, String title, String content, String testcase, int timelimit, int memlimit) {
        this.qid = qid;
        this.title = title;
        this.content = content;
        this.testcase = testcase;
        this.timelimit = timelimit;
        this.memlimit = memlimit;
    }
}
