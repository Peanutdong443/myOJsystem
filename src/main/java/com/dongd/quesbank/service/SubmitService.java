package com.dongd.quesbank.service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dongd.quesbank.aop.TokenVerify;
import com.dongd.quesbank.dao.SubmitDao;
import com.dongd.quesbank.pojo.DO.SubmitDO;
import com.dongd.quesbank.pojo.DTO.SubmitDTO;
import com.dongd.quesbank.pojo.DTO.SubmitQueryDTO;
import com.dongd.quesbank.utils.Result;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import static com.dongd.quesbank.aop.aspect.tokenVerifyAspect.uidThreadLocal;

@Service
public class SubmitService {

    @Autowired
    SubmitDao submitDao;




    public static String generateUniqueId() {
        // 获取当前时间的毫秒值
        long currentTimeMillis = System.currentTimeMillis();

        // 格式化时间为 yyyyMMddHHmmss
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDate = dateFormat.format(new Date(currentTimeMillis));

        // 将毫秒值转为字符串并取最后四位数作为序号
        String sequence = String.valueOf(currentTimeMillis).substring(String.valueOf(currentTimeMillis).length() - 4);

        // 生成一个随机数后缀，范围为 1000 到 9999
        Random random = new Random();
        int randomSuffix = 1000 + random.nextInt(9000); // 生成 4 位随机数

        // 组合生成唯一序号
        return formattedDate + sequence + randomSuffix;
    }

    @TokenVerify
    public Result exam(SubmitDTO submitDTO) throws IOException, InterruptedException {

        String sbmid=generateUniqueId();

        //int qid=submitDTO.getQid();
        int qid=1;

        int uid=uidThreadLocal.get();

        int languageid=submitDTO.getLanguage();

        String sourcecode=new String(submitDTO.getCodebytes());

        String encodedString = Base64.getEncoder().encodeToString(submitDTO.getCodebytes());

        HttpRequest request=null;
        HttpResponse<String> response=null;

        // 测试样例文件路径
        String filePath = "/Users/zhengziran/Desktop/data.txt";

        ArrayList<String> datas=new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                datas.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder strbuilder=new StringBuilder();

        for(int i=0;i< datas.size();i+=2){
            String in=null,out=null;

            if(datas.get(i).equals("-"))in="";
            else in=Base64.getEncoder().encodeToString(datas.get(i).getBytes());
            if(datas.get(i+1).equals("-"))out="";
            else out=Base64.getEncoder().encodeToString(datas.get(i+1).getBytes());


            String testnode="{\"source_code\":\""+encodedString+"\",\"language_id\":"+submitDTO.getLanguage()+",\"stdin\":\""+in+"\",\"expected_output\":\""+out+"\"}";

            if(i!=0)strbuilder.append(",");

            strbuilder.append(testnode);

        }

        String mainbody="{\"submissions\":["+strbuilder+"]}";

        request = HttpRequest.newBuilder()
                .uri(URI.create("https://judge0-ce.p.rapidapi.com/submissions/batch?base64_encoded=true"))
                .header("x-rapidapi-key", "9aaf517c12msh925029045ad9b4dp1d18f4jsnf21b781e4619")
                .header("x-rapidapi-host", "judge0-ce.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(mainbody))
                .build();
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // 创建 ObjectMapper 实例
        ObjectMapper mapper = new ObjectMapper();

        // 解析 JSON 字符串为 JsonNode
        JsonNode rootNode = mapper.readTree(response.body());

        // 创建存储 token 的列表
        List<String> tokens = new ArrayList<>();

        StringBuilder tokensbuilder=new StringBuilder();

        // 遍历 JSON 数组，提取 token 字段
        for (JsonNode node : rootNode) {
            String token = node.get("token").asText();
            tokensbuilder.append(token);
            tokensbuilder.append(",");
        }
        tokensbuilder.deleteCharAt(tokensbuilder.length()-1);


        request = HttpRequest.newBuilder()
                .uri(URI.create("https://judge0-ce.p.rapidapi.com/submissions/batch?tokens="+tokensbuilder.toString()+"&base64_encoded=true&fields=*"))
                .header("x-rapidapi-key", "9aaf517c12msh925029045ad9b4dp1d18f4jsnf21b781e4619")
                .header("x-rapidapi-host", "judge0-ce.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();


        // 存储 status_id 的列表
        List<Integer> statusIds = new ArrayList<>();

        String[]s=responseBody.split("status_id\":");

        for(int i=1;i<s.length;i++){
            statusIds.add(Integer.parseInt(""+s[i].charAt(0)));
        }

        int status=3;
        int passednum=0;

        for(int i=0;i<statusIds.size();i++){
            if(statusIds.get(i)!=3)status=statusIds.get(i);
            else passednum++;
        }

        submitDao.insert(new SubmitDO(sbmid,qid,uid,sourcecode,languageid,status,passednum));

        Map<String,String> data=new HashMap();
        data.put("status",""+status);
        return Result.ok(data);
    }

    @TokenVerify
    public Result getsubmitlist(int id,int qid){
        if(id==0){
            int uid=uidThreadLocal.get();
            return Result.ok(submitDao.getSubmitList(uid,qid));
        }
        return Result.ok(submitDao.getSubmitList(id,qid));
    }

}
