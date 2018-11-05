package me.changjie;

import me.changjie.core.JsonApplicationContext;
import me.changjie.entity.Robot;


/**
 * Created by ChangJie on 2018/11/5.
 */
public class App {

    public static void main(String[] args) throws Exception {
        JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");
        applicationContext.init();
        Robot aiRobot = (Robot) applicationContext.getBean("robot");
        aiRobot.show();
    }
}
