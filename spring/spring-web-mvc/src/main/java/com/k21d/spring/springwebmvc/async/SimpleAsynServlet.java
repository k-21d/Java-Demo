package com.k21d.spring.springwebmvc.async;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 异步Servlet
 * 1.不需要自己写线程池
 *
 */
@WebServlet(value = "/simple/async", asyncSupported = true)
public class SimpleAsynServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        //启动异步上下文
        AsyncContext asyncContext = req.startAsync();
        writer.println(Thread.currentThread().getName()+"线程执行了");
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {
                writer.println(Thread.currentThread().getName()+"线程执行了");
                writer.println("请求完成了");
            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                writer.println(Thread.currentThread().getName()+"线程执行了");
                writer.println("请求超时了");
            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {
                writer.println(Thread.currentThread().getName()+"线程执行了");
                writer.println("请求发生错误了");
            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                writer.println(Thread.currentThread().getName()+"线程执行了");
                writer.println("开始异步");
            }

        });
        //同步调用
        //asyncContext.complete();
        asyncContext.start(()->{
            writer.println(Thread.currentThread().getName()+"执行中");
            asyncContext.complete();
        });
    }

}
