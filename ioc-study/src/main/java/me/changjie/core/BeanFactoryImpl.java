package me.changjie.core;

import me.changjie.bean.BeanDefinition;
import me.changjie.utils.BeanUtils;
import me.changjie.utils.ClassUtils;
import me.changjie.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ChangJie on 2018/11/5.
 */
public class BeanFactoryImpl implements BeanFactory {

    private static final ConcurrentHashMap<String,Object> beanMap = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, BeanDefinition> beanDefineMap= new ConcurrentHashMap<>();

    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<>());


    @Override
    public Object getBean(String name) throws Exception {

        //查找对象是否已经实例化过
        Object bean = beanMap.get(name);
        if(bean != null){
            return bean;
        }

        //如果没有实例化，那就需要调用createBean来创建对象
        bean = createBean(beanDefineMap.get(name));

        if(bean != null) {

            //对象创建成功以后，注入对象需要的参数
            populatebean(bean);

            //再把对象存入Map中方便下次使用。
            beanMap.put(name,bean);
        }

        //结束返回
        return bean;
    }

    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        String beanName = beanDefinition.getClassName();
        Class clz = ClassUtils.loadClass(beanName);
        if(clz == null) {
            throw new Exception("can not find bean by beanName");
        }

        return BeanUtils.instanceByCglib(clz,null,null);
    }

    private void populatebean(Object bean) throws Exception {
        Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                String beanName = field.getName();
                beanName = StringUtils.uncapitalize(beanName);
                if (beanNameSet.contains(field.getName())) {
                    Object fieldBean = getBean(beanName);
                    if (fieldBean != null) {
                        ReflectionUtils.injectField(field,bean,fieldBean);
                    }
                }
            }
        }
    }



    protected void registerBean(String name, BeanDefinition bd){
        beanDefineMap.put(name,bd);
        beanNameSet.add(name);
    }

}
