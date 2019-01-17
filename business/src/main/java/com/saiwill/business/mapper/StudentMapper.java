package com.saiwill.business.mapper;

import com.saiwill.business.entities.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface StudentMapper {

     Student findById(@Param("id") Long id);
}
