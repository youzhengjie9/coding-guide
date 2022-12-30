package com.coding.guide.mobile.service.impl;


import com.coding.guide.common.config.QiniuOssProperties;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.mobile.service.OssUploadService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 七牛云oss文件上传实现类
 *
 * @author youzhengjie
 * @date 2022/12/30 00:34:15
 */
@Service("qiniuOssUploadServiceImpl")
@Slf4j
public class QiniuOssUploadServiceImpl implements OssUploadService {

    @Autowired
    private QiniuOssProperties qiniuOssProperties;

    /**
     * 检查文件是否是图片类型
     * @param originalFilename
     * @return true代表是图片，false则不是图片
     */
    private boolean isImage(String originalFilename){
        //将文件名全部变小写
        String lowerOriginalFilename = originalFilename.toLowerCase();

        return lowerOriginalFilename.endsWith(".jpg") ||
                lowerOriginalFilename.endsWith(".png") ||
                lowerOriginalFilename.endsWith(".jpeg");
    }

    @Override
    public ResponseResult imageUpload(MultipartFile imageFile) {

        //获取上传前的文件原名
        String oldFileName = imageFile.getOriginalFilename();

        //封装响应结果
        ResponseResult<Object> result = new ResponseResult<>();

        //如果不是图片则直接返回
        if(!isImage(oldFileName)){
            result.setCode(ResponseType.FILE_FORMAT_UNSUPPORT.getCode());
            result.setMsg(ResponseType.FILE_FORMAT_UNSUPPORT.getMessage());
            return result;
        }

        //构造一个带指定自动的Region对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本

        UploadManager uploadManager = new UploadManager(cfg);

        //以日期作为目录，每一天的图片都会放到不同的目录下，方便管理
        String fileDir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));

        //UUID文件名
        String uuidFileName = UUID.randomUUID().toString().replaceAll("-", "");

        //获取文件后缀名 .jpg
        String fileSuffix= oldFileName.substring(oldFileName.lastIndexOf("."));

        //上传到oss中的新的图片文件名
        String newFileName = new StringBuilder()
                .append(fileDir)
                .append(uuidFileName)
                .append(fileSuffix).toString();

        try {
            //获取前端传来的文件流
            InputStream inputStream = imageFile.getInputStream();
            Auth auth = Auth.create(qiniuOssProperties.getAccessKey(), qiniuOssProperties.getSecretKey());
            String upToken = auth.uploadToken(qiniuOssProperties.getBucket());
            //七牛云oss上传文件的核心方法
            Response response = uploadManager.put(inputStream,newFileName,upToken,null, null);
            result.setCode(ResponseType.IMAGE_UPLOAD_SUCCESS.getCode());
            result.setMsg(ResponseType.IMAGE_UPLOAD_SUCCESS.getMessage());
            //返回一个外面可以访问的图片地址。拼接域名+新的图片全路径，这样我们通过这个路径就可以直接在外面访问图片了
            result.setData(qiniuOssProperties.getOssUrl()+newFileName);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(ResponseType.IMAGE_UPLOAD_ERROR.getCode());
            result.setMsg(ResponseType.IMAGE_UPLOAD_ERROR.getMessage());
            return result;
        }
    }

    /**
     * 七牛云oss文件删除
     * @param fileFullName 文件全名，也就是下面这个代码生成的名字（记住不要加上域名）：格式例如：2022/10/28/4f74aa358a4548d4860c110ebec3831f.jpg
     *         String newFileName = new StringBuilder()
     *                 .append(fileDir)
     *                 .append(uuidFileName)
     *                 .append(fileSuffix).toString();
     * @return 删除结果
     */
    @Override
    public ResponseResult fileDelete(String fileFullName) {
        //封装响应结果
        ResponseResult<Object> result = new ResponseResult<>();
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());

        Auth auth = Auth.create(qiniuOssProperties.getAccessKey(), qiniuOssProperties.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            //七牛云oss文件删除核心方法
            Response response = bucketManager.delete(qiniuOssProperties.getBucket(), fileFullName);
            result.setCode(ResponseType.FILE_DELETE_SUCCESS.getCode());
            result.setMsg(ResponseType.FILE_DELETE_SUCCESS.getMessage());
            return result;
        } catch (QiniuException ex) {
            ex.printStackTrace();
            result.setCode(ResponseType.FILE_DELETE_ERROR.getCode());
            result.setMsg(ResponseType.FILE_DELETE_ERROR.getMessage());
            return result;
        }
    }
}
