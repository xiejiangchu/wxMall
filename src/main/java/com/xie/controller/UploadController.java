package com.xie.controller;

import com.alibaba.media.upload.UploadPolicy;
import com.alibaba.media.upload.UploadTokenClient;
import com.xie.bean.Image;
import com.xie.response.BaseResponse;
import com.xie.service.ImageFileService;
import com.xie.utils.DateEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author xie
 * @Date 17/2/23 下午4:33.
 */
@Controller
public class UploadController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @Autowired
    private ImageFileService imageFileService;

    @Autowired
    private UploadTokenClient uploadTokenClient;

    @Autowired
    private UploadPolicy uploadPolicy;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String index(Model model) {
        String token = uploadTokenClient.getUploadToken(uploadPolicy);
        model.addAttribute("token", token);
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse upload(@ModelAttribute Image image) {
        return BaseResponse.ok(imageFileService.insert(image));
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request, @RequestParam("upload_image") MultipartFile[] files) {
//        //可以从页面传参数过来
//        System.out.println("name=====" + request.getParameter("name"));
//        //这里可以支持多文件上传
//        if (files != null && files.length >= 1) {
//            BufferedOutputStream bw = null;
//            try {
//                String fileName = files[0].getOriginalFilename();
//                //判断是否有文件且是否为图片文件
//                if (fileName != null && !"".equalsIgnoreCase(fileName.trim()) && isImageFile(fileName)) {
//                    //创建输出文件对象
//                    File outFile = new File(uploadPath + "/" + UUID.randomUUID().toString() + getFileType(fileName));
//                    //拷贝文件到输出文件对象
//                    FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (bw != null) {
//                        bw.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        return "redirect:/index";
    }

    /**
     * 判断文件是否为图片文件
     *
     * @param fileName
     * @return
     */
    private Boolean isImageFile(String fileName) {
        String[] img_type = new String[]{".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        if (fileName == null) {
            return false;
        }
        fileName = fileName.toLowerCase();
        for (String type : img_type) {
            if (fileName.endsWith(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件后缀名
     *
     * @param fileName
     * @return
     */
    private String getFileType(String fileName) {
        if (fileName != null && fileName.indexOf(".") >= 0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }
}
