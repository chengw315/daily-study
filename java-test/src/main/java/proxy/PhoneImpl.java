package proxy;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/7/23
 **/
public class PhoneImpl implements Phone {
    @Override
    public void call() {
        System.out.println("calling");
    }
}
