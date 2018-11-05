package me.changjie.core;

/**
 * Created by ChangJie on 2018/11/5.
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

}
