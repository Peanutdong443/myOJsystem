package com.dongd.quesbank.pojo.DTO;

import lombok.Data;

@Data
public class SubmitDTO {
    private int qid;
    private int language;
    private byte[] codebytes;

    public SubmitDTO() {
    }

    public SubmitDTO(int qid, int language, byte[] codebytes) {
        this.qid = qid;
        this.language = language;
        this.codebytes = codebytes;
    }
}
