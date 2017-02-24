package com.xie.service;

import com.alibaba.media.MediaDir;
import com.xie.bean.Image;

import java.util.List;

public interface ImageFileService
{
	List<MediaDir> getDirs(String soursDir, int pageNum, int pageSize);

	List<Image> getById(int uid);

	int insert(Image image);

	int update(Image image);

	int delete(Image image);

	int delete(int id);
}
