package com.chuanggu.app.constant;

import java.util.HashMap;
import java.util.Map;

public interface Constant {

	String RSLT = "rslt";
	String KCODE = "code";
	String TYPES = "types";
	String PRODUCT_MODEL = "PRODUCT_MODEL";
	String EMAIL_HOST = "EMAIL_HOST";
	String EMAIL_SENDER_NAME = "EMAIL_SENDER_NAME";
	String EMAIL_USERNAME = "EMAIL_USERNAME";
	String EMAIL_PASSWORD = "EMAIL_PASSWORD";
	String EMAIL_PROTOCOL = "EMAIL_PROTOCOL";
	String SECURITY_KEY = "SECURITY_KEY";

	String EMAIL_FOR_DEV_REGISTER_VCODE = "EMAIL_FOR_DEV_REGISTER_VCODE";
	String EMAIL_FOR_DEV_REGISTER_VCODE_TIMEOUT = "EMAIL_FOR_DEV_REGISTER_VCODE_TIMEOUT";
	String EMAIL_FOR_DEV_REGISTER_VCODE_TITLE = "EMAIL_FOR_DEV_REGISTER_VCODE_TITLE";

	// 验证码数字长度
	int VCODE_LENGTH = 4;
	String KEY_SESSION_USER = "KEY_SESSION_USER";
	String EMAIL_FOR_EMAIL_SEND_TIMELIMT = "EMAIL_FOR_EMAIL_SEND_TIMELIMT";

	String SAND_BOX_URL = "SANDBOX_RADAR_URL";
	String MAGIC_CLOUD_URL = "MAGIC_CLOUD_URL";
	String BAIYE_URL = "BAIYE_URL";

	int NUM_VCODE_LENGTH = 4;
	String KEY_SESSION_IMG_VCODE = "KEY_SESSION_IMG_VCODE";
	String EMAIL_FOR_DEV_FORGETPSW_VCODE_TIMEOUT = "EMAIL_FOR_DEV_FORGETPSW_VCODE_TIMEOUT";
	String EMAIL_FOR_DEV_FORGETPSW_VCODE = "EMAIL_FOR_DEV_FORGETPSW_VCODE";
	String EMAIL_FOR_DEV_FORGETPSW_VCODE_TITLE = "EMAIL_FOR_DEV_FORGETPSW_VCODE_TITLE";
	String KEY_SESSION_EMAIL = "KEY_SESSION_EMAIL";
	String KEY_SESSION_FLAG_VCODE = "KEY_SESSION_FLAG_VCODE";
	String KEY_SESSION_FLAG_VCODE_VALUE = "Constant.KEY_SESSION_FLAG_VCODE_VALUE";
	int NOT_MAIN_DEV_MEMBER = 0;

	String UPLOAD_IOS_FILE_SIZE = "UPLOAD_IOS_FILE_SIZE";
	/**
	 * ios 文件类型
	 */
	String IOS_FILE_CONTENT_TYPE = "application/octet-stream";
	String UPLOAD_APK_FILE_SIZE = "UPLOAD_APK_FILE_SIZE";
	/**
	 * apk 文件类型
	 */
	String APK_FILE_CONTENT_TYPE = "application/octet-stream";
	String UPLOAD_SHOTPIC_FILE_SIZE = "UPLOAD_SHOTPIC_FILE_SIZE";
	/**
	 * 图片 文件类型
	 */
	Map<String, String> IMG_FILE_CONTENT_TYPE = new HashMap<String, String>() {
		private static final long serialVersionUID = 1734722052273010341L;
		{
			put("image/bmp", ".bmp");
			put("image/png", ".png");
			put("image/jpeg",".jpg");
			put("image/gif",".gif");
		}
	};
	String UPLOAD_LOGO_FILE_SIZE = "UPLOAD_LOGO_FILE_SIZE";
	String UPLOAD_MEMINFO_IMG_FILE_SIZE = "UPLOAD_MEMINFO_IMG_FILE_SIZE";
	
	//上传存放路劲
	String FILE_APPLY_IMGS_PATH = "/upload/apply/";
	String FILE_APP_APK_PATH = "/upload/apk/";
	String FILE_APP_IPA_PATH = "/upload/ipa/";
	String FILE_APP_DOC_PATH = "/upload/doc/";
	String FILE_APP_IMG_PATH = "/upload/img/";
	String PATH_FIX = "/..";
	Object IOS_FILE_CONTENT_TYPE2 = "application/x-itunes-ipa";
	Object APK_FILE_CONTENT_TYPE2 = "application/x-zip-compressed";

}
