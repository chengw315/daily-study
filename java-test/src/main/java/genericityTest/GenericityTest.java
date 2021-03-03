package genericityTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/7/20
 **/
public class GenericityTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<? extends Object> objects = new ArrayList<>();
        objects = Collections.singletonList(list);

        try {
            Method add = ArrayList.class.getMethod("add", Object.class);
            add.invoke(list,"a");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(list.get(0));
        String s = String.valueOf(list.get(0));
    }
}
