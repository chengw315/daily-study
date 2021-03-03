package bcrypt;

import java.util.zip.CRC32;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/1
 **/
public class CRC32Test {

    public static void main(String[] args) {
        String data = "FF AA 04 02 BD 38 06 E2 6C 7D 16 CB 8E 0A 31 32 33 34 35 36 37 38 39 31 DB A9 AD 6E";
        String s = crc32(HexStringUtil.hexString2Bytes(data.replace(" ","")));
    }

    /**
     * 获取crc32的计算结果
     * @param data
     * @return
     */
    private static String crc32(byte[] data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data);
        Long value = crc32.getValue();
        return HexStringUtil.long2HexString(value);
    }


}
