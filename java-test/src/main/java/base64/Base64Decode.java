package base64;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.util.Base64;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/10/15
 **/
public class Base64Decode {
    public static void main(String[] args) {
//        byte[] decode = Base64.getDecoder().decode("+qIAAygAAAEAAAAAODY3MTY3MDUwMDAwMDMyAwDI+w==");
        byte[] decode = Base64.getDecoder().decode("+qIAAygAAAEAAAAAODY3MTY3MDUwMDAwMDMyAwDI+w==");
        String marshal = new HexBinaryAdapter().marshal(decode);
    }
}
