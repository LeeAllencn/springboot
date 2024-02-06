package com.rocky.boot.java.io;

import java.io.*;

/**
 * @author : rocky
 * @date : created in 2024/2/6
 */
public class PersonTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String file = "springboot-java/obj.dat";
        // 对象的序列化
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Person wPerson = new Person("rocky", "male", 23);
        oos.writeObject(wPerson);
        oos.flush();
        oos.close();

        // 对象的反序列化
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person rPerson = (Person)ois.readObject();
        System.out.println(rPerson);
        ois.close();
    }
}
