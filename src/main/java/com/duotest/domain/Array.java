package com.duotest.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

public class Array {

    private int arrayId;

    private int len;


    public Array() {
    }

    public Array(int len) {
        this.len = len;
    }



    public int getArrayId() {
        return arrayId;
    }

    public void setArrayId(int arrayId) {
        this.arrayId = arrayId;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

}
