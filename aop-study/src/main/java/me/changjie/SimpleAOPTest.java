package me.changjie;

import me.changjie.advice.Advice;
import me.changjie.advice.BeforeAdvice;
import me.changjie.invocation.MethodInvocation;
import me.changjie.service.HelloService;
import me.changjie.service.HelloServiceImpl;

import java.lang.reflect.Proxy;

/**
 * Created by ChangJie on 2018/11/5.
 */
public class SimpleAOPTest {

    public static void main(String[] args) {

        MethodInvocation methodInvocation = new MethodInvocation() {
            @Override
            public void invoke() {
                System.out.println("before something");
            }
        };

        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();

        Advice advice = new BeforeAdvice(helloServiceImpl, methodInvocation);

        HelloService helloServiceImplProxy = (HelloService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), helloServiceImpl.getClass().getInterfaces(), advice);

        helloServiceImplProxy.sayHelloWorld();

    }
}
