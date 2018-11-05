package me.changjie.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * 处理对象的实例化
 * Created by ChangJie on 2018/11/5.
 */
public class BeanUtils {

    public static <T> T instanceByCglib(Class<T> clz, Constructor ctr, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz);
        enhancer.setCallback(NoOp.INSTANCE);
        if(ctr == null){
            return (T) enhancer.create();
        }else {
            return (T) enhancer.create(ctr.getParameterTypes(),args);
        }
    }
}
