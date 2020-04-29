package com.zjg.tomdog.annotations;

import com.zjg.tomdog.MainApp;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ServletMapping {
    /**
     * 映射注解，将被WebService注解的类放入map中
     */
    public void getAnnotation(){
        Map<Class<?>,String> allClass = getClasses("com.zjg.tomdog");
        // Map.entrySet() 这个方法返回的是一个Set<Map.Entry<K,V>>，Map.Entry 是Map中的一个接口，他的
        // 用途是表示一个映射项（里面有Key和Value），而Set<Map.Entry<K,V>>表示一个映射项的Set。Map.Entry
        // 里有相应的getKey和getValue方法，即JavaBean，让我们能够从一个项中取出Key和Value。
        Set<Map.Entry<Class<?>,String>> setClass = allClass.entrySet();
        for(Map.Entry<Class<?>,String> clas : setClass ){
            boolean useAnnota = clas.getKey().isAnnotationPresent(WebService.class);
            if(useAnnota){
                MainApp.servlets.put(clas.getKey(),clas.getValue());
            }
        }

    }

    /**
     * 从包package中获取所有的class
     * @return
     */
    public static Map<Class<?>, String> getClasses(String packageName) {
        Map<Class<?>,String> allClass = new HashMap<Class<?>, String>();
        //是否循环迭代
        boolean recursive = true;
        //获取包的名字进行替换
        String packageDirName = packageName.replace(".","/");
        //定义一个枚举类集合，并用循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            //以相对路径的方式在内部获取资源，path不以’/'开头时，是从ClassPath根下获取；
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            //循环迭代下去
            while(dirs.hasMoreElements()){
                //获取下一个元素
                URL url = dirs.nextElement();
                //得到协议的名称
                String protocol = url.getProtocol();
                //判断是否是以文件的形式保存在服务器上
                if("file".equals(protocol)){
                    //获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(),"UTF-8");
                    //以文件的方式扫描整个包下的文件，并添加到集合中
                    findAndAddClassesInPackageByFile(packageName,filePath,recursive,allClass);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allClass;
    }

    /**
     * 以文件的形式获取包下所有的class
     * @param packageName
     * @param filePath
     * @param recursive
     * @param allClass
     */
    public static void findAndAddClassesInPackageByFile(String packageName, String filePath, final boolean recursive, Map<Class<?>, String> allClass) {
        //获取此包的目录 建立一个file
        File dir = new File(filePath);
        //如果不存在或者也不是目录就直接返回
        if(!dir.exists() || !dir.isDirectory()){
            return;
        }
        //如果存在就获取包下的所有文件，包括目录
        File[] dirfiles;
        dirfiles = dir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
                return (recursive&&pathname.isDirectory() || pathname.getName().endsWith(".class"));
            }
        });
        //循环所有文件
        for (File file:dirfiles){
            //如果是目录，则继续扫描
            if(file.isDirectory()){
                findAndAddClassesInPackageByFile(packageName+"."+file.getName(),
                        file.getAbsolutePath(), recursive, allClass);
            }else {
                //如果是java类文件，去掉后面的.class 只留下类名
                String className = file.getName().substring(0,file.getName().length()-6);
                try {
                    allClass.put(Class.forName(packageName+"."+className),packageName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
/**
 * 遍历map的四种方法
 * Map<String, String> map = new HashMap<String, String>();
 *   map.put("1", "value1");
 *   map.put("2", "value2");
 *   map.put("3", "value3");
 *
 * 第一种：普遍使用，二次取值
 * System.out.println("通过Map.keySet遍历key和value：");
 * for(String key:map.keySet()){
 *     System.out.println("key"+key+";value="+map.get(key))
 * }
 *
 * 第二种：
 *System.out.println("通过Map.entrySet使用iterator遍历key和value：");
 * Iterator<Map.Entry<String,String>> it = map.entrySet.iterator());
 * while(it.hasNext()){
 *    Map.Entry<String,String> entry = it.next();
 *    System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
 * }
 *
 *第三种：推荐，尤其是容量大时
 *   System.out.println("通过Map.entrySet遍历key和value");
 *   for (Map.Entry<String, String> entry : map.entrySet()) {
 *    System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
 *   }
 *
 * 第四种
 *   System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
 *   for (String v : map.values()) {
 *    System.out.println("value= " + v);
 *   }
 *
 *
 */
