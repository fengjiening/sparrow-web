package com.fengjiening.system.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Slf4j
public class MD5Util {
	public final static String SECRET="sinovoice";

	public static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++){
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname)) {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			} else {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
			}
		} catch (Exception exception) {
		}
		return resultString;
	}
//	public static String GetMD5Hash(String input)
//	{
//		System.Security.Cryptography.MD5CryptoServiceProvider md5 = new System.Security.Cryptography.MD5CryptoServiceProvider();
//		byte[] bs = System.Text.Encoding.UTF8.GetBytes(input);
//		bs = md5.ComputeHash(bs);
//		System.Text.StringBuilder s = new System.Text.StringBuilder();
//		foreach (byte b in bs)
//		{
//			s.Append(b.ToString("x2").ToLower());
//		}
//		return s.ToString();
//	}

		public static String getMapToString(Map<String, String> map){

			Set<String> keySet = map.keySet();

			String[] keyArray = keySet.toArray(new String[keySet.size()]);
			Arrays.sort(keyArray);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < keyArray.length; i++) {
				if (!StringUtils.isEmpty(map.get(keyArray[i]))&&map.get(keyArray[i]).length() > 0) {
					sb.append(keyArray[i].toLowerCase()+"="+map.get(keyArray[i]).trim());
				}
				if (i!=keyArray.length-1){
					sb.append("&");
				}
			}
			return sb.toString();
		}
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };



}
