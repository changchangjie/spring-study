package me.changjie.utils;

import java.lang.reflect.Field;

/**
 * 通过反射来完成对象的依赖注入
 * Created by ChangJie on 2018/11/5.
 */
public class ReflectionUtils {

    public static void injectField(Field field, Object obj, Object value) throws IllegalAccessException {
        if(field != null) {
            field.setAccessible(true);//访问私有属性
            field.set(obj, value);//设置 obj 的 field 为 value
        }
    }

}
