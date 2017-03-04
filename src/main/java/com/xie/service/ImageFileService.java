package com.xie.service;

import com.alibaba.media.MediaDir;
import com.alibaba.media.MediaFile;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Image;

import java.util.List;

public interface ImageFileService
{
	List<MediaDir> getDirsServer(String soursDir, int pageNum, int pageSize);

	List<MediaFile> getFileServer(String soursDir, int pageNum, int pageSize);

	Image getById(int id);

	List<Image> getByName(String name);

	List<Image> getAll();

	PageInfo<Image> getAll(int pageNum, int pageSize);

	int insert(Image image);

	int update(Image image);

	int delete(Image image);

	int delete(int id);
}
