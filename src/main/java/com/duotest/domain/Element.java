package com.duotest.domain;

import org.springframework.stereotype.Component;

public class Element {
    private Integer id;

    private Integer arrayId;
    private Integer elemVal;

    private Integer position;



    public Element() {
    }

    public Element(Integer id, Integer arrayId, Integer elemVal, Integer position) {
        this.id = id;
        this.arrayId = arrayId;
        this.elemVal = elemVal;
        this.position = position;
    }

    public Integer getArrayId() {
        return arrayId;
    }

    public void setArrayId(Integer arrayId) {
        this.arrayId = arrayId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getElemVal() {
        return elemVal;
    }

    public void setElemVal(Integer elemVal) {
        this.elemVal = elemVal;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
