package bcrypt;

import java.util.HashMap;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/1
 **/
public class HexStringUtil {

    static char[] map = new char[16];
    static HashMap<Character, Integer> mapR = new HashMap<>(16);

    static {
        for (int i = 0; i < 10; i++) {
            map[i] = (char) (i + '0');
        }
        for (int i = 0; i < 6; i++) {
            map[i+10] = (char) (i + 'A');
        }
        for (int i = 0; i < map.length; i++) {
            mapR.put(map[i],i);
        }
    }

    /**
     * long的hexString值
     * @param value
     * @return
     */
    public static String long2HexString(Long value) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            long l = (value >>> (i * 8)) & 0xFF;
            long high = l / 16;
            long low = l % 16;
            builder.append(map[(int) low]);
            builder.append(map[(int) high]);
        }
        return builder.reverse().toString();
    }

    /**
     * byte数组转hexString值
     * @param bytes
     * @return
     */
    public static String bytes2HexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            builder.append(map[(bytes[i] >>> 4) & 0x0f]);
            builder.append(map[bytes[i]  & 0x0f]);
        }
        return builder.toString();
    }
    /**
     * HexString转换成byte数组
     * @param string
     * @return
     */
    public static byte[] hexString2Bytes(String string) {
        byte[] result = new byte[string.length() / 2];
        for (int i = 0; i < string.length(); i+=2) {
            result[i/2] = (byte) (mapR.get(string.charAt(i)) * 16 + mapR.get(string.charAt(i+1)));
        }
        return result;
    }

    public static void main(String[] args) {
        String s = bytes2HexString(new byte[]{(byte) 0xff, (byte) 0xaa, 0x15, 0x0f});
    }
}
