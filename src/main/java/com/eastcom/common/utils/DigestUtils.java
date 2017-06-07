package com.eastcom.common.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.lang3.Validate;

import com.eastcom.common.exception.Exceptions;

/**
 * 
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:35:23 <br>
 *
 */
public class DigestUtils extends org.apache.commons.codec.digest.DigestUtils {

	public final static int HASH_INTERATIONS = 1024;
	public final static int SALT_SIZE = 8;

	private static SecureRandom random = new SecureRandom();

	/**
	 * 加密文件算法
	 * 
	 * @param filename
	 *            需要加密的文件名
	 * @param algorithm
	 *            加密算法名
	 */
	public static void digestFile(String filename, String algorithm) {
		byte[] b = new byte[1024 * 4];
		int len = 0;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			fis = new FileInputStream(filename);
			while ((len = fis.read(b)) != -1) {
				md.update(b, 0, len);
			}
			byte[] digest = md.digest();
			StringBuffer fileNameBuffer = new StringBuffer(128).append(filename).append(".").append(algorithm);
			fos = new FileOutputStream(fileNameBuffer.toString());
			OutputStream encodedStream = new Base64OutputStream(fos);
			encodedStream.write(digest);
			encodedStream.flush();
			encodedStream.close();
		} catch (Exception e) {
			System.out.println("Error computing Digest: " + e);
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (Exception ignored) {
			}
			try {
				if (fos != null)
					fos.close();
			} catch (Exception ignored) {
			}
		}
	}

	/**
	 * 加密密码算法
	 * 
	 * @param pass
	 *            需要加密的原始密码
	 * @param algorithm
	 *            加密算法名称
	 * @return 加密后的密码
	 * @throws NoSuchAlgorithmException
	 *             当加密算法不可用时抛出此异常
	 */
	public static String digestString(String password, String alg) throws NoSuchAlgorithmException {
		String newPass;
		if (alg == null || Algorithms.MD5.equals(alg)) {
			newPass = DigestUtils.md5Hex(password);
		} else if (Algorithms.NONE.equals(alg)) {
			newPass = password;
		} else if (Algorithms.SHA_256.equals(alg)) {
			newPass = DigestUtils.sha256Hex(password);
		} else if (Algorithms.SHA_384.equals(alg)) {
			newPass = DigestUtils.sha384Hex(password);
		} else if (Algorithms.SHA_512.equals(alg)) {
			newPass = DigestUtils.sha512Hex(password);
		} else {
			newPass = DigestUtils.sha1Hex(password);
		}
		return newPass;
	}

	/**
	 * 加密密码算法，默认的加密算法是SHA_1
	 * 
	 * @param password
	 *            未加密的密码
	 * @return String 加密后的密码
	 */
	public static String digestPassword(String password) {
		try {
			if (password != null && !"".equals(password)) {
				return digestString(password, Algorithms.SHA_1);
			} else
				return null;
		} catch (NoSuchAlgorithmException nsae) {
			throw new RuntimeException("Security error: " + nsae);
		}
	}

	/**
	 * 判断密码是不是相等，默认的加密算法是MD5
	 * 
	 * @param beforePwd
	 *            要判断的密码
	 * @param afterPwd
	 *            加密后的数据库密码
	 * @return Boolean true 密码相等
	 */
	public static boolean isPasswordEnable(String beforePwd, String afterPwd) {
		if (beforePwd != null && !"".equals(beforePwd)) {
			String password = digestPassword(beforePwd);
			return afterPwd.equals(password);
		} else
			return false;
	}

	/**
	 * 对输入字符串进行sha1散列.
	 */
	public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
		return digest(input, Algorithms.SHA_1, salt, iterations);
	}

	/**
	 * 对字符串进行散列, 支持md5与sha1算法.
	 */
	private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);

			if (salt != null) {
				digest.update(salt);
			}

			byte[] result = digest.digest(input);

			for (int i = 1; i < iterations; i++) {
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (GeneralSecurityException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * 生成随机的Byte[]作为salt.
	 * 
	 * @param numBytes
	 *            byte数组的大小
	 */
	public static byte[] generateSalt(int numBytes) {
		Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		return entryptPassword(plainPassword, Algorithms.SHA_1);
	}

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次
	 * 
	 * @param plainPassword
	 * @param alg
	 * @return
	 */
	public static String entryptPassword(String plainPassword, String alg) {
		byte[] salt = generateSalt(SALT_SIZE);
		byte[] hashPassword = digest(plainPassword.getBytes(), alg, salt, HASH_INTERATIONS);
		return EncodeUtils.encodeHex(salt) + EncodeUtils.encodeHex(hashPassword);
	}

	/**
	 * 验证密码
	 * 
	 * @param plainPassword
	 *            明文密码
	 * @param password
	 *            密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		return validatePassword(plainPassword, password, Algorithms.SHA_1);
	}

	/**
	 * 验证密码
	 * 
	 * @param plainPassword
	 *            明文密码
	 * @param password
	 *            密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password, String alg) {
		byte[] salt = EncodeUtils.decodeHex(password.substring(0, 16));
		byte[] hashPassword = digest(plainPassword.getBytes(), alg, salt, HASH_INTERATIONS);
		return password.equals(EncodeUtils.encodeHex(salt) + EncodeUtils.encodeHex(hashPassword));
	}

	public class Algorithms {

		public final static String NONE = "NONE";

		/**
		 * The MD2 message digest algorithm defined in RFC 1319.
		 */
		public static final String MD2 = "MD2";

		/**
		 * The MD5 message digest algorithm defined in RFC 1321.
		 */
		public static final String MD5 = "MD5";

		/**
		 * The SHA-1 hash algorithm defined in the FIPS PUB 180-2.
		 */
		public static final String SHA_1 = "SHA-1";

		/**
		 * The SHA-256 hash algorithm defined in the FIPS PUB 180-2.
		 */
		public static final String SHA_256 = "SHA-256";

		/**
		 * The SHA-384 hash algorithm defined in the FIPS PUB 180-2.
		 */
		public static final String SHA_384 = "SHA-384";

		/**
		 * The SHA-512 hash algorithm defined in the FIPS PUB 180-2.
		 */
		public static final String SHA_512 = "SHA-512";
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(DigestUtils.digestPassword("123456"));
		System.out.println(DigestUtils.digestString("123456", Algorithms.MD5));
		DigestUtils.digestFile("C:\\Users\\user\\Desktop\\PasswordEncode.java", Algorithms.SHA_512);
		System.out.println(DigestUtils.isPasswordEnable("123456", DigestUtils.digestPassword("123456")));
	}
}
