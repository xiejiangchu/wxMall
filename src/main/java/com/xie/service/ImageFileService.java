package com.xie.service;

import com.alibaba.media.MediaDir;

import java.util.List;

public interface ImageFileService
{
	public List<MediaDir> getDirs(String soursDir, int pageNum, int pageSize);
}
