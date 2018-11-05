package me.changjie.advice;

import me.changjie.invocation.MethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by ChangJie on 2018/11/5.
 */
public class BeforeAdvice implements Advice {

    private Object bean;

    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //调用通知
        methodInvocation.invoke();

        return method.invoke(bean, args);//执行目标方法
    }
}
