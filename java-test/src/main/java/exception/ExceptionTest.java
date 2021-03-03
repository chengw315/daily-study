package exception;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/1/27
 **/
public class ExceptionTest {

    public static void main(String[] args) {
        try {
            throw new NullPointerException();
        } catch (Exception e) {
            e.getStackTrace();
            e.printStackTrace();
        }
    }
}
