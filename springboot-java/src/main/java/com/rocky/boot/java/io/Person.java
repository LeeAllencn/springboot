package com.rocky.boot.java.io;

import lombok.Data;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author : rocky
 * @date : created in 2024/2/6
 */
@Data
public class Person implements Serializable {

    private String name;
    private String sex;
    /**
     * 该变量不会进行jvm默认的序列化，但也可以自己完成序列化和反序列化
     */
    private transient int age;

    public Person() {
    }

    public Person(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", sex=" + sex + ", age=" + age + "]";
    }

    private void writeObject(ObjectOutputStream s)
            throws java.io.IOException{
        // 把jvm能默认序列化的元素进行序列化操作
        s.defaultWriteObject();
        // 自己完成age的序列化操作
        s.writeInt(age);
    }

    private void readObject(ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException{
        // 把jvm能默认反序列化的元素进行反序列化操作
        s.defaultReadObject();
        // 自己完成age的反序列化操作
        this.age = s.readInt();
    }
}
