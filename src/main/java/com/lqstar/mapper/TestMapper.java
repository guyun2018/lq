package com.lqstar.mapper;

import com.lqstar.model.Boy;
//import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/3/13/013 22:18
 */
@Repository
public interface TestMapper {
//    @Select("SELECT*FROM USER WHERE ID = #{id}")
    Boy select(int id);
}
