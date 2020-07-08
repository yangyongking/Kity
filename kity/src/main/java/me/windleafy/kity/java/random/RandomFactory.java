package me.windleafy.kity.java.random;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import me.windleafy.kity.java.utils.ReflectKit;

/**
 * 随机类工厂
 */
public class RandomFactory {

    private RandomFactory() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取对象
     * [usage]
     * Person person = RandomFactory.createObject(Person.class);
     *
     * @param tClass 对象类型
     * @return
     * Person{id='611', name='MTaEWTGU', age='LhvMmSII'}
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static <T> T createObject(Class<T> tClass) {
        T instance = null;
        try {
            instance = newInstance(tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }


    /**
     * 获取对象List
     * [usage]
     * List list = RandomFactory.createList(Game.class, 3);
     *
     * @param tClass 对象类型
     * @param length list大小
     * @param <T>
     * @return
     * Game{id=826, name='wIhYTfzO', isVip=false, age=16, money=125724, level=0.613, smart=0.62},
     * Game{id=336, name='IyGZyDAz', isVip=true, age=699, money=364914, level=0.5257, smart=0.74},
     * Game{id=426, name='DyTMUWVW', isVip=false, age=830, money=370730, level=0.0017, smart=0.72}
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static <T> List<T> createList(Class<T> tClass, int length) {
        List<T> list = new LinkedList<>();
        try {
            for (int i = 0; i < length; i++) {
                T instance = newInstance(tClass);
                list.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private static <T> T newInstance(Class<T> tClass) {
        try {
            return ReflectKit.instanceRandom(tClass);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
