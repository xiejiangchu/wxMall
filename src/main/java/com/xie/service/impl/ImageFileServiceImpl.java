package com.xie.service.impl;

import com.alibaba.media.MediaDir;
import com.alibaba.media.MediaFile;
import com.alibaba.media.Result;
import com.alibaba.media.client.MediaClient;
import com.alibaba.media.common.PagedList;
import com.alibaba.media.upload.UploadPolicy;
import com.xie.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ImageFileServiceImpl implements ImageFileService {
    @Autowired
    private MediaClient client;

    /**
     * 分页获取指定文件夹下的目录
     */
    @Override
    public List<MediaDir> getDirs(String soursDir, int pageNum, int pageSize) {
        Result<PagedList<MediaDir>> mediaDirsResult = client.listDirs(soursDir,
                pageNum, pageSize);
        if (mediaDirsResult.isSuccess()) {
            return mediaDirsResult.getData();
        }
        return null;
    }

    /**
     * 获取指定路径的文件
     *
     * @param dir
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<MediaFile> getFile(String dir, int pageNum, int pageSize) {
        Result<PagedList<MediaFile>> mediaDirsResult = client.listFiles(dir, pageNum, pageSize);
        if (mediaDirsResult.isSuccess()) {
            return mediaDirsResult.getData();
        }
        return null;
    }

    public String fieUpload(File file, String dir, String name) {

        // 0.4 自定义上传策略
        UploadPolicy uploadPolicy = new UploadPolicy();
        uploadPolicy.setInsertOnly(UploadPolicy.INSERT_ONLY_NONE);
        uploadPolicy.setExpiration(System.currentTimeMillis() + 3600 * 1000);


        // 1. 简单上传接口，直接上传文件
        Result<MediaFile> result = client.upload(dir, name, file);

//        // 2. 直接上传接口，满足个性化上传需求
//        UploadRequest uploadRequest = new UploadRequest(uploadPolicy);
//        uploadRequest.setFile(file);
//        uploadRequest.setDir("保存路径");
//        uploadRequest.setName("保存文件名称");
//
//        Result<UploadResponse> result = client.upload(uploadRequest);
        if (result.isSuccess()) {
            return result.getData().getUrl();
        } else
            return null;
    }
}
