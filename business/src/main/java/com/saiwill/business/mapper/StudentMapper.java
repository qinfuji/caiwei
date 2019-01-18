package com.saiwill.business.mapper;

import com.saiwill.business.entities.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @author qinfuji
 * @date  2019/1/18
 */
public interface StudentMapper {

     /**
      * 通过id查询
      * @param id
      * @return
      */
    Student findById(@Param("id") Long id);
}
