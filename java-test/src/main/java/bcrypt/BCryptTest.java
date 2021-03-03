package bcrypt;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/5/8
 **/
public class BCryptTest {
    public static void main(String[] args) {
        String password = "123456123456123456123456123456123456";
        //加盐
        System.out.println(BCrypt.gensalt());
        String salt = BCrypt.gensalt();
        System.out.println(salt);
        String passwordSalt = BCrypt.hashpw(password, salt);
        System.out.println(passwordSalt);
        //加盐密码做盐对原密码加盐
        String passwordPasswordSalt = BCrypt.hashpw(password,passwordSalt+"asdafdgasd");
        System.out.println(passwordPasswordSalt);
        System.out.println(BCrypt.hashpw(password,passwordSalt+"asdafdgasd"));
        System.out.println(BCrypt.hashpw(password,passwordSalt+"asdafdgasd"));
        System.out.println(BCrypt.hashpw(password,passwordSalt+"asdafdgasd"));
        System.out.println(BCrypt.hashpw(password,passwordSalt+"asdafdgasd"));
        System.out.println(BCrypt.hashpw(password,passwordSalt+"asdafdgasd"));
        System.out.println(BCrypt.hashpw(password,passwordSalt+"asdafdgasd"));

    }
}
