package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/7/23
 **/
public class PhoneProxy implements InvocationHandler {

    private PhoneImpl phone = new PhoneImpl();

    public static void main(String[] args) {
        PhoneImpl phone = new PhoneProxy().create();
        phone.call();
    }

    public PhoneImpl create() {
        //第二个参数必须传递接口
        return (PhoneImpl) Proxy.newProxyInstance(PhoneImpl.class.getClassLoader(),
                new Class[]{PhoneImpl.class},
                new PhoneProxy());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
//        if(method 被代理) {
//            //前置操作
//        }
//        //调用原方法
//        method.invoke(target,args);
//        if(method 被代理) {
//            //后置操作
//        }
        return null;
    }
}
