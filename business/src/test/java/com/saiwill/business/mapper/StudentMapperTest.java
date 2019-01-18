package com.saiwill.business.mapper;

import com.saiwill.business.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class StudentMapperTest extends BaseTest {

    @Autowired
    StudentMapper mapper;

    @Test
    public void testSelectById(){
        mapper.findById(1L);
    }
}
