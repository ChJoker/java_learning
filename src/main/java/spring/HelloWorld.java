package spring;

/**
 * Created by SunFlower on 2016/3/13.
 */
public class HelloWorld {
    private String message;
    public void setMessage(String message){
        this.message = message;
    }
    public void getMessage(){
        System.out.println("You message : "+message);
    }
}
