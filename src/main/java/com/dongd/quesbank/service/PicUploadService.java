package com.dongd.quesbank.service;

import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dongd.quesbank.aop.TokenVerify;
import com.dongd.quesbank.config.AliyunConfig;
import com.dongd.quesbank.dao.UserDao;
import com.dongd.quesbank.pojo.UserDO;
import com.dongd.quesbank.utils.Result;
import com.dongd.quesbank.utils.ResultCodeEnum;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.dongd.quesbank.aop.aspect.tokenVerifyAspect.uidThreadLocal;

@Service
public class PicUploadService {

    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg",
            ".jpeg", ".gif", ".png"};

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private AliyunConfig aliyunConfig;

    @Autowired
    UserDao userDao;


    @TokenVerify
    public Result AvatarUpload(MultipartFile avatar) throws IOException {


        int uid=uidThreadLocal.get();
        // 读取上传的原始图片
        BufferedImage originalImage = ImageIO.read(avatar.getInputStream());
        System.out.println(originalImage.toString());

        // 创建缩略图
        int thumbnailSize = 200;

        // 计算缩略图的宽度和高度，保持原宽高比例
        int newWidth, newHeight;
        if (originalImage.getWidth() > originalImage.getHeight()) {
            newWidth = thumbnailSize;
            newHeight = thumbnailSize * originalImage.getHeight() / originalImage.getWidth();
        } else {
            newWidth = thumbnailSize * originalImage.getWidth() / originalImage.getHeight();
            newHeight = thumbnailSize;
        }

        // 创建缩略图
        BufferedImage thumbnail = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = thumbnail.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        graphics2D.dispose();

        // 裁剪成以图片中心为中心的正方形，因为前端是以正方形显示
        int x = 0;
        int y = 0;
        int cropSize = Math.min(newWidth, newHeight);
        if (newWidth > newHeight) {
            x = (newWidth - cropSize) / 2;
        } else {
            y = (newHeight - cropSize) / 2;
        }
        thumbnail = thumbnail.getSubimage(x, y, cropSize, cropSize);



        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageIO.write(thumbnail, "jpg", bs);
        byte[] thumbnailBytes = bs.toByteArray();


        // 文件新路径
        String fileName = avatar.getOriginalFilename();
        String filePath = getFilePath(fileName);

        // 上传到阿里云
        try {
            // 目录结构：images/2018/12/29/xxxx.jpg
            ossClient.putObject(aliyunConfig.getBucketName(), filePath, new
                    ByteArrayInputStream(thumbnailBytes));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(null, ResultCodeEnum.RESOURCE_FIND_ERROR);
        }

        // 上传成功
        String url=aliyunConfig.getEndpoint().split("//")+"//"+aliyunConfig.getBucketName()+"."+aliyunConfig.getEndpoint().split("//")[1]+"/"+filePath;

        String[] ptr=url.split("//");

        UpdateWrapper<UserDO> wrapper=new UpdateWrapper<UserDO>().set("avatarurl",ptr[1]).eq("uid",uid);

        userDao.update(wrapper);

        String Avatarurl="http://"+ptr[1];

        Map<String,String> data=new HashMap();

        data.put("avatarUrl",Avatarurl);

        System.out.println(Avatarurl);

        return Result.ok(data);
    }

    private String getFilePath(String sourceFileName) {
        DateTime dateTime = new DateTime();
        return "images/" + dateTime.toString("yyyy")
                + "/" + dateTime.toString("MM") + "/"
                + dateTime.toString("dd") + "/" + System.currentTimeMillis() +
                RandomUtils.nextInt(100, 9999) + "." +
                StringUtils.substringAfterLast(sourceFileName, ".");
    }

}

