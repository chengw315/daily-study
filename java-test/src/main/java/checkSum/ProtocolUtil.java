package checkSum;

/**
 * 协议编解码可能用到的工具类
 *
 * @author ChengWeiJun
 * @version 1.0.0
 * @date 2019/12/30
 * @modiffiedBy
 */
public class ProtocolUtil {


    /**
     * 将原16进制字符串标准化为指定字节数
     * @param source
     * @param byteNum
     * @return
     */
    public static String parseByByteNum(String source, int byteNum) {
        int length = byteNum * 2;
        if(source.length() == length)
            return source;
        if(source.length() > length)
            return source.substring(source.length() - length, source.length());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length - source.length(); i++) {
            result.append('0');
        }
        result.append(source);
        return result.toString();
    }

    public static String parseIMEI(String IMEI) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < IMEI.length(); i++) {
            result.append(Integer.toHexString(IMEI.charAt(i)));
        }
        return result.toString();
    }
}
