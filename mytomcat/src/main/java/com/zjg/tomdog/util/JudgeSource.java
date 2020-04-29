package com.zjg.tomdog.util;

public class JudgeSource {

    public boolean judgeSourceType(Request requestMes){
        String[] staticType = new String[]{".html",".css",".js",".ico"};
        boolean isStatic = false;
        if(requestMes.getData() != null){
            for(String type : staticType){
                if(requestMes.getData().endsWith(type)){
                    isStatic = true;
                }
            }
        }

        return isStatic;
    }
}
