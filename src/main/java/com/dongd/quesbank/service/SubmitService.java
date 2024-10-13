package com.dongd.quesbank.service;
import com.dongd.quesbank.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

@Service
public class SubmitService {

    public Result exam(byte[] sourcecode) throws IOException, InterruptedException {

        String encodedString = Base64.getEncoder().encodeToString(sourcecode);

        HttpRequest request=null;
        HttpResponse<String> response=null;

        request = HttpRequest.newBuilder()
                .uri(URI.create("https://judge029.p.rapidapi.com/submissions?base64_encoded=true&wait=false&fields=*"))
                .header("x-rapidapi-key", "9aaf517c12msh925029045ad9b4dp1d18f4jsnf21b781e4619")
                .header("x-rapidapi-host", "judge029.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"source_code\":\""+encodedString+"\",\"language_id\":52,\"stdin\":\"\",\"expected_output\":\"\"}"))
                .build();
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        String token=response.body().substring(10,response.body().length()-2 );

        sleep(5000);

        request = HttpRequest.newBuilder()
                .uri(URI.create("https://judge029.p.rapidapi.com/submissions/"+token+"?base64_encoded=true&fields=*"))
                .header("x-rapidapi-key", "9aaf517c12msh925029045ad9b4dp1d18f4jsnf21b781e4619")
                .header("x-rapidapi-host", "judge029.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        System.out.println(response.body().charAt(response.body().indexOf("\"status\":{\"id\":")+15));

        char status=response.body().charAt(response.body().indexOf("\"status\":{\"id\":")+15);

        Map<String,String> data=new HashMap();
        data.put("status",""+status);


        return Result.ok(data);
    }



}
