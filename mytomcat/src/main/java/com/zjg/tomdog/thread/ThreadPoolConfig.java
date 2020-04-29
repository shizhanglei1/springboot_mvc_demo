package com.zjg.tomdog.thread;

import java.util.concurrent.*;

public class ThreadPoolConfig {
    private int corePoolSize = 10;
    private int maximumPoolSize = 15;
    private long keepAliveTime = 60;
    //存活时间单位
    private TimeUnit unit = TimeUnit.SECONDS;
    //阻塞队列类型
    private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(3);

    public ThreadPoolFactory getThreadPoolFactory() {
        return threadPoolFactory;
    }

    public void setThreadPoolFactory(ThreadPoolFactory threadPoolFactory) {
        this.threadPoolFactory = threadPoolFactory;
    }

    //线程池
    private ThreadPoolFactory threadPoolFactory = null;

    public RejectedExecutionHandler getHandler() {
        return handler;
    }

    public void setHandler(RejectedExecutionHandler handler) {
        this.handler = handler;
    }

    //RejectedExecutionHandler （饱和策略）：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略还处理新提交的任务。它可以有如下四个选项：
    //更多的时候，我们应该通过实现RejectedExecutionHandler 接口来自定义策略
    private RejectedExecutionHandler handler = new ThreadExceptionHandle();

    //类本身对象，单例模式
    private static ThreadPoolConfig threadPoolConfig;

    public ThreadPoolConfig(){
    }

    /**
     * 对线程池配置对象进行初始化
     * @return
     */
    public static ThreadPoolConfig getInstance(){
        if(threadPoolConfig == null){
            return new ThreadPoolConfig();
        }
        return threadPoolConfig;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    public BlockingQueue<Runnable> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
    }

}
