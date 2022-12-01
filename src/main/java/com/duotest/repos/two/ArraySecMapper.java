package com.duotest.repos.two;

import com.duotest.domain.Array;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ArraySecMapper {

    @Select("SELECT * FROM public.arr WHERE array_id=#{id}")
    public Array getArrayById(int id);

    @Select("SELECT * FROM public.arr WHERE array_id=#{id}")
    public Array addArrayById(Array array);
}
