package me.windleafy.kity.java.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 反射类
 */
public class ReflectKit {

    private ReflectKit() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //========================================打印信息=======================================

    //获取所有的公共的方法
    public static <T> void methodsInfo(Class<T> tClass)
            throws IllegalAccessException, InstantiationException {
        Method[] methods = tClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    //获取所有的接口
    public static <T> void interfacesInfo(Class<T> tClass)
            throws IllegalAccessException, InstantiationException {
        Class<?>[] interS = tClass.getInterfaces();
        for (Class<?> class2 : interS) {
            System.out.println(class2);
        }
    }

    //获取父类
    public static <T> void superclassInfo(Class<T> tClass)
            throws IllegalAccessException, InstantiationException {
        Class<?> superclass = tClass.getSuperclass();
        System.out.println(superclass);
    }

    //获取所有的构造函数
    public static <T> void constructorsInfo(Class<T> tClass)
            throws IllegalAccessException, InstantiationException {
        Constructor<?>[] constructors = tClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
    }

    //创建实例化：相当于 new 了一个对象
    public static <T> void instanceInfo(Class<T> tClass)
            throws IllegalAccessException, InstantiationException {
        T instance = tClass.newInstance();
        System.out.println(instance.toString());
    }

    //取得本类的全部属性
    public static <T> void declaredFieldsInfo(Class<T> tClass)
            throws IllegalAccessException, InstantiationException {
        Field[] declaredFields = tClass.getDeclaredFields();
        for (Field declaredFiled : declaredFields) {
            System.out.println(declaredFiled);
        }
    }

    public static <T> void fieldsInfo(Class<T> tClass)
            throws IllegalAccessException, InstantiationException {
        Field[] mFields = tClass.getFields();
        for (Field field : mFields) {
            System.out.println("" + field);
        }
    }

    public static <T> void info(Class<T> tClass)
            throws IllegalAccessException, InstantiationException {

        //获取所有的公共的方法
        System.out.println("====getMethods====");
        Method[] methods = tClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("");

        //获取所有的接口
        System.out.println("====getInterfaces====");
        Class<?>[] interS = tClass.getInterfaces();
        for (Class<?> class2 : interS) {
            System.out.println(class2);
        }
        System.out.println("");

        //获取父类
        System.out.println("====getSuperclass====");
        Class<?> superclass = tClass.getSuperclass();
        System.out.println(superclass);
        System.out.println("");

        //获取所有的构造函数
        System.out.println("====getConstructors====");
        Constructor<?>[] constructors = tClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println("");

        //创建实例化：相当于 new 了一个对象
        System.out.println("====newInstance====");
        T instance = tClass.newInstance();
        System.out.println(instance.toString());
        System.out.println("");

        //取得本类的全部属性
        System.out.println("====getDeclaredFields====");
        Field[] declaredFields = tClass.getDeclaredFields();
        for (Field declaredFiled : declaredFields) {
            System.out.println(declaredFiled);
        }
        System.out.println("");

        System.out.println("====getDeclaredFields====");
        Field[] mFields = tClass.getFields();
        for (Field field : mFields) {
            System.out.println("" + field);
        }
        System.out.println("");
    }


    //==========================================方法===================================================

    /**
     * 构造函数，使用参数最多的
     *
     * @param tClass
     * @param <T>
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static <T> T constructor(Class<T> tClass)
            throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructorParamsMax = getConstructorParamsMax(tClass);
        if (constructorParamsMax == null)
            return null;
        Class[] params = constructorParamsMax.getParameterTypes();
        return constructor(constructorParamsMax, params);
    }

    /**
     * 获取class最多参数的构造函数
     *
     * @param tClass
     * @param <T>
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static <T> Constructor getConstructorParamsMax(Class<T> tClass) {
        Constructor<?>[] constructors = tClass.getConstructors();
        Constructor constructorParamsMax = null;
        for (Constructor<?> constructor : constructors) {
            if (constructorParamsMax == null) {
                constructorParamsMax = constructor;
                continue;
            }
            if (constructor.getParameterCount() > constructorParamsMax.getParameterCount()) {
                constructorParamsMax = constructor;
            }
        }
        return constructorParamsMax;
    }

    //指定构造函数
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static <T> T constructor(Class<T> tClass, Class... paramsClz)
            throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<T> constructor = tClass.getDeclaredConstructor(paramsClz);
        return constructor(constructor, paramsClz);

    }


    /**
     * @param constructor
     * @param paramsClz
     * @param <T>
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static <T> T constructor(Constructor<?> constructor, Class... paramsClz)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return (T) constructor.newInstance(createBasicObject(paramsClz));
    }


    /**
     * 创建对象
     *
     * @param tClass 对象类型
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static <T> T instance(Class<T> tClass)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return constructor(tClass);
    }




    /**
     * 构造函数，使用参数最多的
     *
     * @param tClass
     * @param <T>
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static <T> T constructorRandom(Class<T> tClass)
            throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructorParamsMax = getConstructorParamsMax(tClass);
        if (constructorParamsMax == null)
            return null;
        Class[] params = constructorParamsMax.getParameterTypes();
        return constructorRandom(constructorParamsMax, params);
    }


    //指定构造函数
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static <T> T constructorRandom(Class<T> tClass, Class... paramsClz)
            throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<T> constructor = tClass.getDeclaredConstructor(paramsClz);
        return constructorRandom(constructor, paramsClz);

    }


    /**
     * @param constructor
     * @param paramsClz
     * @param <T>
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static <T> T constructorRandom(Constructor<?> constructor, Class... paramsClz)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return (T) constructor.newInstance(createBasicObjectRandom(paramsClz));
    }


    /**
     * 创建对象
     *
     * @param tClass 对象类型
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static <T> T instanceRandom(Class<T> tClass)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return constructorRandom(tClass);
    }



    /**
     * 创建对象
     *
     * @param tClass    对象类型
     * @param paramsClz 必须满足一下条件集合
     *                  boolean.class
     *                  byte.class
     *                  char.class
     *                  short.class
     *                  int.class
     *                  long.class
     *                  float.class
     *                  double.class
     *                  java.lang.Boolean.class
     *                  java.lang.Byte.class
     *                  java.lang.Character.class
     *                  java.lang.Short.class
     *                  java.lang.Integer.class
     *                  java.lang.Long.class
     *                  java.lang.Float.class
     *                  java.lang.Double.class
     * @param <T>
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static <T> T instance(Class<T> tClass, Class... paramsClz)
            throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        return constructor(tClass, paramsClz);
    }


    /**
     * 创建对象列表
     *
     * @param classes 必须满足一下条件集合
     *                boolean.class
     *                byte.class
     *                char.class
     *                short.class
     *                int.class
     *                long.class
     *                float.class
     *                double.class
     *                java.lang.Boolean.class
     *                java.lang.Byte.class
     *                java.lang.Character.class
     *                java.lang.Short.class
     *                java.lang.Integer.class
     *                java.lang.Long.class
     *                java.lang.Float.class
     *                java.lang.Double.class
     * @return
     */
    public static Object[] createBasicObject(Class... classes) {
        List<Object> list = new LinkedList<>();
        for (Class clz : classes) {
            list.add(createBasicObject(clz));
        }
        return list.toArray();
    }

    /**
     * 创建对象列表
     *
     * @param classes
     * @return
     */
    public static Object[] createBasicObjectRandom(Class... classes) {
        List<Object> list = new LinkedList<>();
        for (Class clz : classes) {
            list.add(createBasicObjectRandom(clz));
        }
        return list.toArray();
    }

    /**
     * 默认创建基本数据类
     */
    public static Object createBasicObject(Class clz) {
        return createBasicObject(clz,
                "", false,
                0, 0, 0, 0,
                (byte) 0, '0', (short) 0);
    }

    /**
     * 随机创建基本数据类
     */
    public static Object createBasicObjectRandom(Class clz) {
        Random random = new Random();
        return createBasicObject(clz,
                RandomKit.getRandom(RandomKit.LETTERS, 8),
                random.nextBoolean(),
                random.nextInt(1000),
                random.nextInt(1000000),
                MathKit.floatCut(random.nextFloat(),2),
                MathKit.doubleCut(random.nextDouble(),4),
                (byte) RandomKit.getRandom(0, 127),
                '0',
                (short) RandomKit.getRandom(0, 100));
    }


    /**
     * 创建基本数据类
     *
     * @param clz 必须满足一下条件之一
     *            boolean.class
     *            byte.class
     *            char.class
     *            short.class
     *            int.class
     *            long.class
     *            float.class
     *            double.class
     *            java.lang.Boolean.class
     *            java.lang.Byte.class
     *            java.lang.Character.class
     *            java.lang.Short.class
     *            java.lang.Integer.class
     *            java.lang.Long.class
     *            java.lang.Float.class
     *            java.lang.Double.class
     */
    public static Object createBasicObject(Class clz,
                                           String defaultString,
                                           boolean defaultBoolean,
                                           int defaultInteger,
                                           long defaultLong,
                                           float defaultFloat,
                                           double defaultDouble,
                                           byte defaultByte,
                                           char defaultChar,
                                           short defaultShort) {
//        System.out.println("clzName:" + clz.getName());
        switch (clz.getName()) {
            //常用
            case "java.lang.String":
                return new String(defaultString);

            case "boolean":
                return defaultBoolean;
            case "java.lang.Boolean":
                return new Boolean(defaultBoolean);
            case "int":
                return defaultInteger;
            case "java.lang.Integer":
                return new Integer(defaultInteger);
            case "long":
                return defaultLong;
            case "java.lang.Long":
                return new Long(defaultLong);
            case "float":
                return defaultFloat;
            case "java.lang.Float":
                return new Float(defaultFloat);
            case "double":
                return defaultDouble;
            case "java.lang.Double":
                return new Double(defaultDouble);

            case "byte":
                return defaultByte;
            case "java.lang.Byte":
                return new Byte(defaultByte);
            case "char":
                return defaultChar;
            case "java.lang.Character":
                return new Character(defaultChar);
            case "short":
                return defaultShort;
            case "java.lang.Short":
                return new Short(defaultShort);

            default:
                return null;
        }
    }

    public static <E> boolean contain(E element, E... elements) {
        for (E e : elements) {
            if (element == e)
                return true;
        }
        return false;
    }


    public static boolean isWrapClass(Class clz) {
        try {
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean isString(Class clz) {
        return clz == String.class;
    }

    public static boolean isCharSequence(Class clz) {
        return clz == CharSequence.class;
    }

    public static boolean isInteger(Class clz) {
        return clz == Integer.class;
    }

    public static boolean isInt(Class clz) {
        return clz == int.class;
    }

    public static boolean isFloat(Class clz) {
        return clz == Float.class;
    }

    public static boolean isfloat(Class clz) {
        return clz == float.class;
    }

    public static boolean isDouble(Class clz) {
        return clz == Double.class;
    }

    public static boolean isdouble(Class clz) {
        return clz == double.class;
    }

}
