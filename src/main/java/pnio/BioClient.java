package pnio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by SunFlower on 2016/3/14.
 */
public class BioClient implements Runnable{
    private String command ;
    BioClient(String s){
        command = s;
    }
    public void run(){
        String host = "localhost";
        int port = 9999;
        try {
            Socket client = new Socket(host,port);
            DataInputStream input = new DataInputStream(client.getInputStream());
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            output.writeUTF(Thread.currentThread().getName()+ " Start. Command "+ command);
            String ret  = input.readUTF();
            System.out.println(ret);
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+"end");
            input.close();
            output.close();
            client.close();

        }catch (IOException e){

        }
        catch (InterruptedException e){

        }
    }
    public static  void main(String[] args){
        ExecutorService excutor = Executors.newFixedThreadPool(5);
        for(int i = 0;i<10;i++){
            Runnable worker = new BioClient(""+i);
            excutor.execute(worker);
        }
        excutor.shutdown();
        while (!excutor.isTerminated()){

        }
    }
}
