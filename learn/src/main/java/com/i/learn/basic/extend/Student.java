package com.i.learn.basic.extend;

/**
 * 抽象类
 */
public abstract class Student extends Person{

    private String school;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    protected abstract void work();

}
