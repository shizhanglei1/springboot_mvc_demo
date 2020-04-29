package com.zjg.tomdog.thread;



import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池工厂类
 */
public class ThreadPoolFactory {
    //线程池
    private static ThreadPoolExecutor threadPoolExecutor;
    //线程池配置对象
    private static ThreadPoolConfig threadPoolConfig;
    //自身对象，单例
    private static ThreadPoolFactory threadPoolFactory = null;

    /**
     * 构造方法私有化
     */
    private ThreadPoolFactory(){}

    /**
     * 获取工厂对象
     * @return 线程池工厂对象
     */
    public static ThreadPoolFactory getInstance(){
        if(threadPoolConfig == null){
            threadPoolConfig = ThreadPoolConfig.getInstance();
        }
        if(threadPoolFactory == null){
            threadPoolFactory = new ThreadPoolFactory();
        }
        if(threadPoolExecutor == null){
            if(threadPoolConfig.getWorkQueue() == null){
                threadPoolExecutor = new ThreadPoolExecutor(
                        threadPoolConfig.getCorePoolSize(),threadPoolConfig.getMaximumPoolSize(),
                        threadPoolConfig.getKeepAliveTime(),threadPoolConfig.getUnit(),
                        threadPoolConfig.getWorkQueue());
            }else{
                threadPoolExecutor = new ThreadPoolExecutor(
                        threadPoolConfig.getCorePoolSize(),threadPoolConfig.getMaximumPoolSize(),
                        threadPoolConfig.getKeepAliveTime(),threadPoolConfig.getUnit(),
                        threadPoolConfig.getWorkQueue(),threadPoolConfig.getHandler());
            }
        }
        System.out.println("create successfully pool");
        return threadPoolFactory;
    }

    /**
     * 添加线程任务
     * @param runnable
     */
    public synchronized void addTask(Runnable runnable){
        threadPoolExecutor.execute(runnable);
    }

    /**
     * 添加多个线程任务
     * @param runnables
     */
    public synchronized void addTask(List<Runnable> runnables){
        if(runnables != null){
            //forEach的用法
            runnables.forEach(this::addTask);
        }
    }
}

/**
 * 线程池执行顺序
 * * 1. 如果运行的线程小于corePoolSize,则尝试使用用户定义的Runnalbe对象创建一个新的线程
 *
 * *    调用addWorker函数会原子性的检查runState和workCount，通过返回false来防止在不应
 *
 * *    该添加线程时添加了线程
 *
 * * 2. 当运行的线程数达到corePoolSize，此时如果有新的任务提交需要加到队列中，
 *      如果一个任务能够成功入队列，在添加一个线程时仍需要进行双重检查（因为在前一次检查后
 *
 * *    该线程死亡了），或者当进入到此方法时，线程池已经shutdown了，所以需要再次检查状态，
 *
 * *    若有必要，当停止时还需要回滚入队列操作，或者当线程池没有线程时需要创建一个新线程
 *
 * * 3. 如果无法入队列，那么需要增加一个新线程，如果此操作失败，那么就意味着线程池已经shut
 *
 * *    down或者已经饱和了，所以拒绝任务
 */

/**
 * ThreadPoolExecutor有四个构造函数
 * 构造函数如下：
 *1，ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)
 * 2，ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue,RejectedExecutionHandler handler)
 * 3，ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory)
 * 4，ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory,RejectedExecutionHandler handler)
 *
 *其中的参数分别如下：
 *
 * 1  corePoolSize（线程池的基本大小）:当提交一个任务到线程池时，线程池会创建一个线程来执行任务，即使其他空闲的基本线程能够执行新任务也会创建线程，等到需要执行的任务数大于线程池基本大小时就不再创建。如果调用了prestartAllCoreThreads()方法，线程池会提前创建并启动所有基本线程。
 *
 * 2  maximumPoolSize（线程池最大数量）：线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。值得注意的是，如果使用了无界的任务队列这个参数就没用了。
 *
 * 3 keepAliveTime(线程活动时间):线程池的工作线程空闲后，保持存活的时间。所以如果任务很多，并且每个任务执行的时间比较短，可以调大时间，提高线程利用率。
 *
 * 4 TimeUnit(线程活动时间的单位)：可选的单位有天（Days）、小时（HOURS）、分钟（MINUTES）、毫秒（MILLISECONDS）、微秒（MICROSECONDS，千分之一毫秒）和纳秒（NANOSECONDS，千分之一微秒）。
 *
 * 5  workQueue(任务队列) : 用于保存等待执行的任务的阻塞队列。可以选择以下几个阻塞队列：
 *    ArrayBlockingQueue:是一个基于数组结构的有界阻塞队列，按FIFO原则进行排序
 *    LinkedBlockingQueue:一个基于链表结构的阻塞队列，吞吐量高于ArrayBlockingQueue。静态工厂方法Excutors.newFixedThreadPool()使用了这个队列
 *    SynchronousQueue: 一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量高于LinkedBlockingQueue，静态工厂方法Excutors.newCachedThreadPool()使用了这个队列
 *    PriorityBlockingQueue:一个具有优先级的无限阻塞队列。
 * 6  ThreadFactory（线程工厂）：可以通过线程工厂为每个创建出来的线程设置更有意义的名字，如开源框架guava
 *
 * 7  RejectedExecutionHandler （饱和策略）：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略还处理新提交的任务。它可以有如下四个选项：
 *    AbortPolicy:直接抛出异常，默认情况下采用这种策略
 *    CallerRunsPolicy:只用调用者所在线程来运行任务
 *    DiscardOldestPolicy:丢弃队列里最近的一个任务，并执行当前任务
 *    DiscardPolicy:不处理，丢弃掉
 *    更多的时候，我们应该通过实现RejectedExecutionHandler 接口来自定义策略，比如记录日志或持久化存储等。
 * 提交任务的方式：使用execute或者submit向线程池提交任务
 *
 * 1，execute方法用于提交不需要返回值的任务，利用这种方式提交的任务无法知道任务是否正常执行；
 *
 * 2，submit用于提交一个任务并带有返回值，这个方法将返回一个Future对象，可以通过这个返回对象判断任务是否执行成功，并且可以通过Future.get方法来获取返回值，get()方法会阻塞当前线程知道任务完成。
 *
 *
 * 关闭线程池：threadPoolExecutor.shutdown()。或者 threadPoolExecutor.shutdownNow()。
 */