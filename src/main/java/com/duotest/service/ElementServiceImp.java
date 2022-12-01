package com.duotest.service;

import com.duotest.domain.Array;
import com.duotest.domain.Element;
import com.duotest.repos.one.ArrayRepository;
import com.duotest.repos.two.ArraySecMapper;
import com.duotest.repos.one.ElementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan("com.duotest.repos")
public class ElementServiceImp implements ElementService{
    @Autowired
    private final ElementMapper elementMapper;
    private final ArraySecMapper arraySecMapper;

    @Autowired
    private final ArrayRepository arrayRepository;

    public ElementServiceImp(ElementMapper elementMapper, ArraySecMapper arraySecMapper, ArrayRepository arrayRepository) {
        this.elementMapper = elementMapper;
        this.arraySecMapper = arraySecMapper;
        this.arrayRepository = arrayRepository;
    }


    @Override
    public List<Element> getElementsById(int id) {
        Array array = arrayRepository.getArrayById(id);
        List<Element> elements =  elementMapper.getElementsById(id);
        return elements;
    }

    @Override
    public Array getArrayById(int id) {
        Array array = arraySecMapper.getArrayById(id);
        return array;
    }

    @Override
    public void addArray(Array array) {
        arrayRepository.addArrayById(array);
    }
}
