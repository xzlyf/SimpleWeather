package com.xz.simpleweather.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Pchilds implements Serializable {
    public String code;
    public String name;
    public ArrayList<Cchilds> cchilds;

    public ArrayList<Cchilds> getCchilds() {
        return cchilds;
    }

    public void setCchilds(ArrayList<Cchilds> cchilds) {
        this.cchilds = cchilds;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
