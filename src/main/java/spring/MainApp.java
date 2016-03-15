package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by SunFlower on 2016/3/13.
 */
public class MainApp {
    public static void main(String[] args){
        ApplicationContext context  = new ClassPathXmlApplicationContext("Beams.xml");
        HelloWorld obj  = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();
    }
}
