package me.changjie.utils;

/**
 * 处理 Java 类的加载
 * Created by ChangJie on 2018/11/5.
 */
public class ClassUtils {

    public static ClassLoader getDefultClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class loadClass(String className) throws ClassNotFoundException {
        return getDefultClassLoader().loadClass(className);
    }
}
