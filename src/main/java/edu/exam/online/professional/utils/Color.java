package edu.exam.online.professional.utils;

/**
 * Created by licy13 on 2016/5/24.
 */
public enum Color {
    RED(1,"红色"), GREEN(2,"绿色"), BLANK(3,"白色"), YELLO(4,"黄色");
    // 成员变量
    private int index;
    private String name;
    // 构造方法
    private Color( int index,String name) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (Color c : Color.values()) {
            if (c.index == index) {
                return c.name;
            }
        }
        return null;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public static void main(String[] args) {
        Color.GREEN.getName();
        System.out.println(Color.getName(3));
    }
}