package checkSum;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/10/22
 **/
public class CheckSumUtil {

    public static void main(String[] args) {
//        fad10003004400125f90f8d3383639373638303431383037393032fffffcb00000005d00ffffff9209b88fd29400050047000f025f90f8d30004030d2400f70c2400b2fb
        System.out.println(getChecksum("050047000f025f90f8d30004030d2400f70c2400"));
    }

    private static String getChecksum(String command) {
        byte[] content = ConvertHelper.hexStringToBytes(command);
        byte sum = (byte) 0x00;
        int length = content.length;
        for (int i = 1; i < length; i++) {
            sum += content[i];
        }
        return ProtocolUtil.parseByByteNum(ConvertHelper.byteToHexString(sum).toLowerCase(), 1);
    }
}
