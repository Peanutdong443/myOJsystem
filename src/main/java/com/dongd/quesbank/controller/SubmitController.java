package com.dongd.quesbank.controller;

import com.dongd.quesbank.pojo.DTO.LoginDTO;
import com.dongd.quesbank.pojo.DTO.SubmitDTO;
import com.dongd.quesbank.pojo.DTO.SubmitQueryDTO;
import com.dongd.quesbank.service.SubmitService;
import com.dongd.quesbank.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin({"http://localhost:8080"})
public class SubmitController {
    @Autowired
    private SubmitService ss;

    @PostMapping("/submitcode")
    public Result submitcode(@RequestBody SubmitDTO submitDTO) throws IOException, InterruptedException {
        Result result = ss.exam(submitDTO);
        return result;
    }

    @GetMapping("/getsubmitlist")
    public Result getsubmitlist(@RequestParam int uid, @RequestParam int qid){
        Result result = ss.getsubmitlist(uid,qid);
        return result;
    }
}
