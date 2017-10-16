package com.rocky.boot.entry;

import java.io.Serializable;

/**
 * Created by Rocky on 2017-10-16.
 */
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 2561019539707988492L;

    private Long id;
    private String userName;
    private String passWord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
