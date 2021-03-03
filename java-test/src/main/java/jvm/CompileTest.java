package jvm;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/7/13
 **/
public class CompileTest {

    public class AClass{
        public int getNum(int i) {
            return 1024 * 32 * i;
        }
    }

    public void test() {
        AClass aClass = new AClass();
        int num1 = aClass.getNum(25);
        int num2 = aClass.getNum(25);
    }

    public static void main(String[] args) {
        CompileTest compileTest = new CompileTest();
        compileTest.test();
    }
}
