package com.xll.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xll.constant.Constants;
/**
 * 获取所有自动发送的图片路径
 * @author xialonglei
 * @date 2016/10/16
 */
public class PicBootstrap implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(PicBootstrap.class);

	public static List<String> picsPath = new ArrayList<String>();

	@Override
	public void run() {
		LOGGER.info("开始初始化图片库......");
		File f = new File(Constants.PIC_ROOT_PATH);
		addPicPath(f);
		LOGGER.info("图片库初始化已完成......");
	}

	private void addPicPath(File f) {
		if(f.isFile()){
			String path = f.getAbsolutePath();
			picsPath.add(path);
		}else{
			File[] fChilds = f.listFiles();
			for(File fChild : fChilds){
				addPicPath(fChild);
			}
		}
	}

}
