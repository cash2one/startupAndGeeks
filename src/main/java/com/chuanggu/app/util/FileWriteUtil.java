package com.chuanggu.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;


public class FileWriteUtil {
	public static String getBasePath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

//	public static String entryptPassword(String psw) {
//		return MD5Util.MD5(psw);
//	}
//
//	public static void setSessionUser(HttpSession session, DevMember devMember) {
//		session.setAttribute(Constant.KEY_SESSION_USER, devMember);
//	}
//
//	public static DevMember getSessionUser(HttpSession session) {
//		return (DevMember) session.getAttribute(Constant.KEY_SESSION_USER);
//	}
//
//	public static String getEmailBodyForRegister(String vcode, String email) {
//		String emailBody = Config.getString(Constant.EMAIL_FOR_DEV_REGISTER_VCODE);
//		String timeoutHour = Config.getString(Constant.EMAIL_FOR_DEV_REGISTER_VCODE_TIMEOUT);
//		return String.format(emailBody, vcode, timeoutHour);
//	}
//
//	public static String getEmailBodyForForgetPsw(String vcode, String email) {
//		String emailBody = Config.getString(Constant.EMAIL_FOR_DEV_FORGETPSW_VCODE);
//		String timeoutHour = Config.getString(Constant.EMAIL_FOR_DEV_FORGETPSW_VCODE_TIMEOUT);
//		return String.format(emailBody, vcode, timeoutHour);
//	}
//
//	public static void setVcodeImgCode(HttpSession session, String vcode) {
//		session.setAttribute(Constant.KEY_SESSION_IMG_VCODE, vcode);
//	}
//
//	public static String getVcodeImgCode(HttpSession session) {
//		return (String) session.getAttribute(Constant.KEY_SESSION_IMG_VCODE);
//	}
//
//	public static void setCurEmail(HttpSession session, String email) {
//		session.setAttribute(Constant.KEY_SESSION_EMAIL, email);
//	}
//
//	public static String getCurEmail(HttpSession session) {
//		return (String) session.getAttribute(Constant.KEY_SESSION_EMAIL);
//	}

	/**
	 * 复制缓存文件 -1:不存在.需要重新上传. -2:复制失败
	 * 
	 * @param fromPath
	 *            相对路径
	 * @param session
	 * @return
	 */
	public static String copyTmpFile(String fromPath, String toPath, HttpSession session) {
		if (fromPath == null || "".equals(fromPath)) {
			return "-3";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dirName = toPath + df.format(new Date()) + "/";
		String projectPath = FileWriteUtil.getProjectPath(session);
		File s = new File(projectPath + fromPath);
		if (!s.exists()) {
			return "-1";
		}
		String path = dirName;
		File t = new File(projectPath + path);
		if (!t.exists()) {
			t.mkdirs();
		}
		t = new File(projectPath + path + s.getName());
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();
			out = fo.getChannel();
			in.transferTo(0, in.size(), out);
		} catch (IOException e) {
			e.printStackTrace();
			return "-2";
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path + "/" + s.getName();
	}

	/**
	 * 获取项目文件的根路劲
	 * 
	 * @param session
	 * @return
	 */
	public static String getProjectPath(HttpSession session) {
		String path = session.getServletContext().getRealPath("/");
		path = path.substring(0, path.length() - 1);
		path = path.substring(0, path.lastIndexOf(File.separatorChar));
		return path;
	}

	/**
	 * 获取上传文件的临时存放路径
	 * 
	 * @param session
	 * @return
	 */
	public static String getTmpFilePath(HttpSession session) {
		return "/upload/" + session.getId() + "/";
	}

	public static String getFileName(String ext) {
		if (ext == null) {
			ext = "";
		}
		return System.currentTimeMillis() + "_" + new Random().nextInt(1000) + ext;
	}

	/**
	 * 写入缓存文件夹. -1:没有权限. -2:未知错误
	 * 
	 * @param file
	 * @param session
	 * @param ext
	 * @return
	 */
	public static String writetTmpFile(MultipartFile file, HttpSession session, String ext) {
		String fileName = FileWriteUtil.getFileName(ext);
		String projectPath = FileWriteUtil.getProjectPath(session);
		String basePath = FileWriteUtil.getTmpFilePath(session);
		// 创建文件夹
		File fp = new File(projectPath + basePath);
		if (!fp.exists()) {
			if (!fp.mkdirs()) {
				return "-1";
			}
		}
		File ff = new File(projectPath + basePath + fileName);
		if (ff.exists()) {
			if (!ff.delete()) {
				return "-1";
			}
		}
		try {
			file.transferTo(ff);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return "-2";// UNKOWN ERROR
		} catch (IOException e) {
			e.printStackTrace();
			return "-2";// UNKOWN ERROR
		}
		return basePath + fileName;
	}

	public static String writetTmpFile(MultipartFile file, HttpSession session, String toPath, String ext) {
		String fileName = FileWriteUtil.getFileName(ext);
		String projectPath = FileWriteUtil.getProjectPath(session);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dirName = toPath + df.format(new Date()) + "/";
		// 创建文件夹
		File fp = new File(projectPath + dirName);
		if (!fp.exists()) {
			if (!fp.mkdirs()) {
				return "-1";
			}
		}
		File ff = new File(projectPath + dirName + fileName);
		if (ff.exists()) {
			if (!ff.delete()) {
				return "-1";
			}
		}
		try {
			file.transferTo(ff);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return "-2";// UNKOWN ERROR
		} catch (IOException e) {
			e.printStackTrace();
			return "-2";// UNKOWN ERROR
		}
		return dirName + fileName;
	}

	public static String getProjectPath2(HttpSession session) {
		String path = session.getServletContext().getRealPath("/");
		return path;
	}
}
