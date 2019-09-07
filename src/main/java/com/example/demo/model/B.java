package com.example.demo.model;

/**
 * @author: shaozhixing
 * @date: 2019/7/17 13:41
 * @copyright: gofun
 */
public class B {
    private String a;
    private String b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "B{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}
