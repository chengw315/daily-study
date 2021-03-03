package bcrypt;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/1
 **/
public class AesTest {
    private final static String AES_ECB_NoPadding = "AES/ECB/NoPadding";
    private final static String AES_ECB_PKCS5Padding = "AES/ECB/PKCS5Padding";
    private final static String AES_CBC_NoPadding = "AES/CBC/NoPadding";
    private final static String AES_CBC_PKCS5Padding = "AES/CBC/PKCS5Padding";

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        String secretKey = "674DA31D4BC506E92B431FAED48D712B";
        String secretKey = "A0 1A 24 79 67 25 73 DF 84 53 7D F4 C7 3D 3F CC".replace(" ","");
//        String data = "75D0B36B1ABA02BC313033313635397B";
        String data = "11 15 43 4B 11 1E 1B C8 31 31 31 30 30 30 30 31 7B".replace(" ","");
        ecb(secretKey, data);
    }

    private static void ecb(String secretKey, String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Key key = new SecretKeySpec(HexStringUtil.hexString2Bytes(secretKey), "AES");
        Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5Padding);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(HexStringUtil.hexString2Bytes(data));
        String s = HexStringUtil.bytes2HexString(bytes);
    }
}
