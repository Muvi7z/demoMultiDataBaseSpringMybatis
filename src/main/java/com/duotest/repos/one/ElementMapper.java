package com.duotest.repos.one;

import com.duotest.domain.Array;
import com.duotest.domain.Element;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ElementMapper {
    @Select("SELECT * FROM public.element WHERE array_id=#{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "arrayId", column = "array_id"),
            @Result(property = "elemVal", column = "elem_val"),
            @Result(property = "position", column = "position"),
    })
    List<Element> getElementsById(int id);

    @Select("SELECT * FROM public.element WHERE array_id=#{id}")
    Element addArrayById(Array array);
}
