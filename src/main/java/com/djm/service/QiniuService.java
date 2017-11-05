package com.djm.service;

import com.alibaba.fastjson.JSONObject;
import com.djm.util.ToutiaoUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * @author dingjiamin
 * @workcode wb-djm332505
 * @date 2017/11/4
 */
@Service
public class QiniuService {
    private static final Logger logger = LoggerFactory.getLogger(QiniuService.class);

    String accessKey = "OFoune4RXuac0MxeDkTBbXVhZgLIXobfEAQ7B2ur";
    String secretKey = "gj9bkC5Sye5DRcm4hZiXMYhQs-xUD6N9h6PVuYIs";
    String bucketname = "toutiao";

    Auth auth = Auth.create(accessKey, secretKey);

    UploadManager uploadManager = new UploadManager();

    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public String saveImage(MultipartFile file) throws IOException {
        int dotPos = file.getOriginalFilename().lastIndexOf(".");
        if (dotPos < 0) {
            return null;
        }
        String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();

        if (!ToutiaoUtil.isFileAllowed(fileExt)) {
            return null;
        }

        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;

        try {
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            if (res.isOK() && res.isJson()) {
                String key = JSONObject.parseObject(res.bodyString()).getString("key");
                return ToutiaoUtil.QINIU_DOMAIN_PREFIX + key;
            } else {
                logger.error("七牛异常:", res.bodyString());
                return null;
            }
        }catch (QiniuException e) {
            logger.error("七牛异常" + e.getMessage());
            return null;
        }
    }
}
