package com.duotest.repos.one;

import com.duotest.domain.Array;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
public interface ArrayRepository {

    @Select("SELECT * FROM public.arr WHERE array_id=#{id}")
    public Array getArrayById(int id);

    @Select("SELECT * FROM public.arr WHERE array_id=#{id}")
    public Array addArrayById(Array array);
}
