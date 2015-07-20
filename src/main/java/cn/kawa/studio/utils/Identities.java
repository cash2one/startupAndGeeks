package cn.kawa.studio.utils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * ��װ��������Ψһ��ID�㷨�Ĺ�����.
 * 
 * @author calvin
 */
public class Identities {

	private static SecureRandom random = new SecureRandom();

	/**
	 * ��װJDK�Դ���UUID, ͨ��Random��������, �м���-�ָ�.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * ��װJDK�Դ���UUID, ͨ��Random��������, �м���-�ָ�.
	 */
	public static String uuid2() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * ʹ��SecureRandom�������Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * ����Base62�����SecureRandom�������bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}
}