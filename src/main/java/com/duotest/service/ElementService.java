package com.duotest.service;



import com.duotest.domain.Array;
import com.duotest.domain.Element;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ElementService {
    public List<Element> getElementsById(int id);
    public Array getArrayById(int id);
    public void addArray(Array array);
}
