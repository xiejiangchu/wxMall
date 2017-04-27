package com.xie.service.impl;

import com.alibaba.media.MediaDir;
import com.alibaba.media.MediaFile;
import com.alibaba.media.Result;
import com.alibaba.media.client.MediaClient;
import com.alibaba.media.common.PagedList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Image;
import com.xie.dao.ImageDao;
import com.xie.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ImageFileServiceImpl implements ImageFileService {
    @Autowired
    private MediaClient client;

    @Autowired
    private ImageDao imageDao;


    @Override
    public List<MediaDir> getDirsServer(String soursDir, int pageNum, int pageSize) {
        Result<PagedList<MediaDir>> mediaDirsResult = client.listDirs(soursDir,
                pageNum, pageSize);
        if (mediaDirsResult.isSuccess()) {
            return mediaDirsResult.getData();
        }
        return null;
    }

    @Override
    public List<MediaFile> getFileServer(String soursDir, int pageNum, int pageSize) {
        Result<PagedList<MediaFile>> mediaDirsResult = client.listFiles(soursDir,
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

    @Override
    public Image getById(int id) {
        return imageDao.getById(id);
    }

    @Override
    public List<Image> getByName(String name) {
        return imageDao.getByName(name);
    }

    @Override
    public List<Image> getAll() {
        return imageDao.getAll();
    }

    @Override
    public PageInfo<Image> getAll(int pageNum, int pageSize) {
        PageInfo<Image> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> imageDao.getAll());
        return page;
    }

    @Override
    public int insert(Image image) {
        return imageDao.insert(image);
    }

    @Override
    public int update(Image image) {
        return imageDao.update(image);
    }

    @Override
    public int delete(Image image) {
        return imageDao.delete(image);
    }

    @Override
    public int delete(int id) {
        Image image = imageDao.getById(id);
        client.deleteFile(image.getDir(), image.getName());
        return imageDao.delete(id);
    }
}
