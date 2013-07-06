package geeky.week3.asynchronous.config;

public class HelloWorldHandler {
    public void handleMessage(String text) throws InterruptedException {
        System.out.println("Received: " + text);
        Thread.sleep(1000);
    }
}
