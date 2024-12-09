package com.defects_management;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DefectsManagementApplicationTest {

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void contextLoads() {
        MockitoAnnotations.openMocks(this);
        DefectsManagementApplication app = new DefectsManagementApplication();
        assertNotNull(app.modelMapper());
    }
}


