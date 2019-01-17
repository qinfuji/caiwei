package com.saiwill.business.mapper;

import com.saiwill.business.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@Profile("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StudentMapperTest {

    @Autowired
    StudentMapper mapper;

    @Test
    public void testSelectById(){

    }
}
