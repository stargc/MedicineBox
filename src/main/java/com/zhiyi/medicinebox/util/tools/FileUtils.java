package com.zhiyi.medicinebox.util.tools;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 文件工具类
 * 
 * @author gaoyue
 * @date 2016年4月24日
 * @time 下午1:17:24
 */
public class FileUtils {
	private static final Logger logger = LogManager.getLogger(FileUtils.class);
	/**
	 * 上传文件
	 * 
	 * @param file
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static File saveFile(MultipartFile file,String Path) throws UnsupportedEncodingException {
		if (file == null){
			return null;
		}
		File targetFile = new File(Path);
		if (!targetFile.exists() || !targetFile.isDirectory()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return targetFile;
	}

	public static boolean saveFile(File file, String path) {
		// 基于myFile创建一个文件输入流
		InputStream is;
		try {
			is = new FileInputStream(file);
			// 设置目标文件
			File toFile = new File(path);
			// 创建一个输出流
			OutputStream os = new FileOutputStream(toFile);
			// 设置缓存
			byte[] buffer = new byte[1024];
			int length = 0;
			// 读取myFile文件输出到toFile文件中
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			// 关闭输入流
			is.close();
			// 关闭输出流
			os.close();
			return true;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return false;
	}

}
