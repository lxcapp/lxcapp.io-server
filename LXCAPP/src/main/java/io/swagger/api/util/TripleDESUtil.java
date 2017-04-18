package io.swagger.api.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * 3DES加解密
 * @author 张建新
 *
 */
public class TripleDESUtil {
	
	private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish

		/**
		 * 加密算法
		 * password为加密密钥，长度为24字节
		 * src为被加密的数据缓冲区（源）
		 */
	    public static byte[] encryptMode(String password, byte[] src) {
	       try {
	            //生成密钥
	            SecretKey deskey = new SecretKeySpec(password.getBytes(), Algorithm);
	            //加密
	            Cipher c1 = Cipher.getInstance(Algorithm);
	            c1.init(Cipher.ENCRYPT_MODE, deskey);
	            return c1.doFinal(src);
	        } catch (java.security.NoSuchAlgorithmException e1) {
	            e1.printStackTrace();
	        } catch (javax.crypto.NoSuchPaddingException e2) {
	            e2.printStackTrace();
	        } catch (Exception e3) {
	            e3.printStackTrace();
	        }
	        return null;
	    }

	    
	    /**
		 * 解密算法
		 * keybyte为加密密钥，长度为24字节
		 * src为被加密的数据缓冲区（源）
		 */
	    public static byte[] decryptMode(String password, byte[] src) {      
	    try {
	            //生成密钥
	            SecretKey deskey = new SecretKeySpec(password.getBytes(), Algorithm);
	            //解密
	            Cipher c1 = Cipher.getInstance(Algorithm+"/ECB/NoPadding");
	            c1.init(Cipher.DECRYPT_MODE, deskey);
	            return c1.doFinal(src);
	        } catch (java.security.NoSuchAlgorithmException e1) {
	            e1.printStackTrace();
	        } catch (javax.crypto.NoSuchPaddingException e2) {
	            e2.printStackTrace();
	        } catch (Exception e3) {
	            e3.printStackTrace();
	        }
	        return null;
	    }

	    //转换成十六进制字符串
	    public static String byte2hex(byte[] b) {
	        StringBuffer hs=new StringBuffer("");
	        String stmp="";

	        for (int n=0;n<b.length;n++) {
	            stmp=(Integer.toHexString(b[n] & 0XFF));
	            if (stmp.length()==1) hs.append("0").append(stmp);
	            else hs.append(stmp);
	            if (n<b.length-1)  hs.append(":");
	        }
	        return hs.toString().toUpperCase();
	    }
	    
    /**
     * BASE64加密
     */
    private static byte[] encode(byte[] res) {
        try {
            /*Base64 base = new Base64();

            return base.encode(res);*/
            return org.apache.commons.codec.binary.Base64.encodeBase64(res);
        } catch (Exception e) {
            return null;
        }
    }

}
