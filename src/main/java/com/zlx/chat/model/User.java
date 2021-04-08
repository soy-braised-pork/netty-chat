package com.zlx.chat.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 4321063427158412038L;

    private int id;
    private String name;


    public String toString() {
        return "User [id=" + id + ",name=" + name + "]";
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
