package com.dongd.quesbank.pojo.DTO;
import lombok.Data;

@Data
public class SubmitQueryDTO {
    private int uid;
    private int qid;

    public SubmitQueryDTO() {
    }

    public SubmitQueryDTO(int uid, int qid) {
        this.qid = qid;
        this.uid = uid;
    }
}
