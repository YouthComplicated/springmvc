package com.lanmo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 上传文件
 */
//@RestController
@Controller
public class UploadController {

    /**
     * //todo 这种方法不知道原始文件名称，能否传递多余的参数???
     * @param picture
     * @return
     */
    @PostMapping(value = "/upload")
    public Map<String, Object> uploadReceiveByBytes(@RequestPart("picture") byte[] picture){
        System.out.println(picture);
        return null;
    }


    /**
     * @param picture
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload/part")
    public Map<String, Object> uploadReceive(@RequestPart("aa") Part picture) throws IOException {
        Map<String, Object> map = new HashMap<>();
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString()+".png";
        picture.write(fileName);
        map.put("success", "yes");
        return map;
    }

    /**
     * 若指定的名称错误:
     * org.springframework.web.multipart.support.MissingServletRequestPartException:
     * Required request part 'picture' is not present
     *
     * @param picture
     * @return
     * @throws IOException
     */
    @PostMapping(value="/upload/multipart/file")
    public Map<String, Object> uploadReceiveMultipartFile(@RequestPart("picture") MultipartFile picture) throws IOException {
        Map<String, Object> map = new HashMap<>();
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString()+".png";
        picture.transferTo(new File(fileName));
        map.put("success", "yes");
        return map;
    }

    /**
     * 上传多个文件
     * postman 测试(form-data)时必须指定key并且都是files
     * MultipartFile 可以不用指定@RequestParam
     * @param files
     * @return
     * @throws IOException
     */
    @PostMapping(value="/upload/multipart/files")
    public Map<String, Object> uploadReceiveMultipartFiles(MultipartFile[] files) throws IOException {
        Map<String, Object> map = new HashMap<>();
        for(MultipartFile file : files){
            System.out.println(file.getOriginalFilename());
        }
        return map;
    }

    /**
     * 必须使用@RequestParam
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value="/upload/commons/files")
    public Map<String, Object> uploadCommonsMultipartFile(@RequestParam(value = "file", required = false) CommonsMultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        System.out.println(file.getOriginalFilename());
        return map;
    }


}
