package com.dqj.interstellar;

public class LineBean {

    int startX;

    public LineBean(int startX, int startY, int endX, int endy) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endy = endy;
    }
    public void add(int num)
    {
        startX+=num;
        startY+=num;
        endX+=num;
        endy+=num;
        startX=setLoop(startX,1080);
        startY=setLoop(startY,1920);
        endX=setLoop(endX,1080);
        endy=setLoop(endy,1920);
    }
    public void sub(int num)
    {
        startX-=num;
        startY-=num;
        endX-=num;
        endy-=num;
        startX=setLoop(startX,1080);
        startY=setLoop(startY,1920);
        endX=setLoop(endX,1080);
        endy=setLoop(endy,1920);
    }
    public int setLoop(int num,int max){
        if (num>max)
        {
            num=num-max;
        }
        return num;
    }
    int startY;
    int endX;
    int endy;
}
