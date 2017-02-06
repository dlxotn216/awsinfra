package com.taesu.test.s3;

import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by crscube on 2017-01-26.
 */
@Controller
public class AwsS3Controller {

    @Autowired
    private AwsS3Service awsS3Service;

    @ResponseBody
    @RequestMapping(value="/s3/upload", method= RequestMethod.POST)
    public List<PutObjectResult> s3Upload(@RequestParam("file")MultipartFile[] multipartFiles){
        String name = multipartFiles[0].getOriginalFilename();
        System.out.println("DEBUG CHECK FIEL NAME[0] : "+name);

        return awsS3Service.upload(multipartFiles);
    }
}
