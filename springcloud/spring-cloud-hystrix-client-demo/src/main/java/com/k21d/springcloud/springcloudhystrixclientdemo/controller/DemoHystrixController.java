package com.k21d.springcloud.springcloudhystrixclientdemo.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class DemoHystrixController {
    private final static Random random = new Random();
    /**
     * 当{@link #helloWorld2()}方法调用超时或者失败时，fallback方法{@link #errorContent()}
     * 作为替代返回
     * @return
     */
    @GetMapping("/hello-world2")
    public String helloWorld2(){
        return new HelloWorldCommand().execute();
    }

    /**
     * 编程方法
     */
    private class HelloWorldCommand extends com.netflix.hystrix.HystrixCommand<String> {

        protected HelloWorldCommand() {
            super(HystrixCommandGroupKey.Factory.asKey("HelloWorld"),100 );
        }
        @Override
        protected String run() throws Exception {
            int value = random.nextInt(200);
            System.out.println("hello world() costs"+value+"ms");
            Thread.sleep (value);
            return "Hello World";
        }

        /**
         * 容错执行
         * @return
         */
        protected String getFallback(){
            return DemoHystrixController.this.errorContent();
        }
    }
    /**
     * 当{@link #helloWorld()}方法调用超时或者失败时，fallback方法{@link #errorContent()}
     * 作为替代返回
     * @return
     */
    @GetMapping("/hello-world")
    @HystrixCommand(fallbackMethod = "errorContent",
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="100")
            })
    public String helloWorld() throws InterruptedException {
        int value = random.nextInt(200);
        System.out.println("hello world() costs"+value+"ms");
        Thread.sleep (value);
        return "hello world";
    }

    public String errorContent() {
        return "Fault";
    }

}
