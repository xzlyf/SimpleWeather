package com.xz.simpleweather.entity;

import java.util.ArrayList;
import java.util.List;

public class National {
    public String code;
    public String name;
    public ArrayList<Pchilds> pchilds;

    public ArrayList<Pchilds> getPchilds() {
        return pchilds;
    }

    public void setPchilds(ArrayList<Pchilds> pchilds) {
        this.pchilds = pchilds;
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
