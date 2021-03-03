/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/17
 **/
public class StringTest {
    public static void main(String[] args) {
        String companyId = "companyId";
        String productId = "productId";

        String outFileName = companyId +
                "-" +
                productId +
                "-" +
                System.currentTimeMillis() + ".xls";

        for (int i = 0; i < 10; i++) {
            outFileName += companyId +
                    "-" +
                    productId +
                    "-";
        }
    }
}
