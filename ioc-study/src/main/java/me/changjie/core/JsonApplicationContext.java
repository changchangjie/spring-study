package me.changjie.core;

import com.alibaba.fastjson.JSONObject;
import me.changjie.bean.BeanDefinition;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ChangJie on 2018/11/5.
 */
public class JsonApplicationContext extends BeanFactoryImpl {

    private String fileName;
    public JsonApplicationContext(String fileName) {
        this.fileName = fileName;
    }
    public void init() throws IOException {
        loadFile();
    }
    private void loadFile() throws IOException {

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        String text = IOUtils.toString(inputStream,"utf8");
        List<BeanDefinition> beanDefinitions = JSONObject.parseArray(text,BeanDefinition.class);

        if(CollectionUtils.isNotEmpty(beanDefinitions)) {
            for (BeanDefinition beanDefinition : beanDefinitions) {
                registerBean(beanDefinition.getName(), beanDefinition);
            }
        }
    }
}
