package com.dongd.quesbank.controller;

import com.dongd.quesbank.pojo.DTO.LoginDTO;
import com.dongd.quesbank.service.SubmitService;
import com.dongd.quesbank.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin({"http://localhost:8080"})
public class SubmitController {
    @Autowired
    private SubmitService ss;

    @PostMapping("/submitcode")
    public Result submitcode(@RequestBody byte[] sourcecode) throws IOException, InterruptedException {
        Result result = ss.exam(sourcecode);
        return result;
    }
}
