package com.data.bean;

/**
 * Created by Administrator on 2016/7/16.
 */
public class MyBean {
    private int pagerFlag;  //用来区分是哪个图下的数据
    private String content; //虚拟的评论数据

    public MyBean() {
    }

    public MyBean(int pagerFlag, String content) {
        this.pagerFlag = pagerFlag;
        this.content = content;
    }

    public int getPagerFlag() {
        return pagerFlag;
    }

    public void setPagerFlag(int pagerFlag) {
        this.pagerFlag = pagerFlag;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
