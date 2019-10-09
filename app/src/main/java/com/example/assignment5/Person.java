package com.example.assignment5;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Person {
    @SerializedName("nimi")
    private String name;
    @SerializedName("pvm")
    private String PVM;

    public Person(String name, String PVM) {
        this.name = name;
        this.PVM = PVM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPVM() {
        return PVM;
    }

    public void setPVM(String PVM) {
        this.PVM = PVM;
    }
}
