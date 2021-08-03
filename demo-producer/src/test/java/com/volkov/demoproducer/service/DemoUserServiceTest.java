package com.volkov.demoproducer.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.volkov.demoproducer.entity.DemoUser;
import com.volkov.demoproducer.entity.dto.DemoUserDTO;
import com.volkov.demoproducer.exception.custom.DemoUserDuplicateException;
import com.volkov.demoproducer.exception.custom.DemoUserNotFoundException;
import com.volkov.demoproducer.repository.DemoUserRepository;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class DemoUserServiceTest {

    @Autowired
    DemoUserService demoUserService;

    @MockBean
    DemoUserRepository demoUserRepository;

    @Test
    void when_DemoUserIsMalformed_then_throwConstraintViolationException() {
        DemoUserDTO demoUserDTO = new DemoUserDTO("", null);
        when(demoUserRepository.insert(any(DemoUser.class)))
                .thenThrow(ConstraintViolationException.class);
        assertThrows(ConstraintViolationException.class, () -> demoUserService.saveDemoUser(demoUserDTO), "ConstraintViolationException should be thrown");
    }

    @Test
    void when_DeletingDemoUserThatDoesNotExists_then_throwDemoUserNotFoundException() {
        DemoUserDTO demoUserDTO = new DemoUserDTO("test", "test");
        when(demoUserRepository.deleteByUsername(anyString()))
                .thenThrow(DemoUserNotFoundException.class);
        assertThrows(DemoUserNotFoundException.class, () -> demoUserService.deleteDemoUserByUsername(demoUserDTO.getUsername()), "DemoUserNotFoundException should be thrown");
    }

    @Test
    void when_SavingDemoUserWithDuplicatedParams_then_throwDemoUserDuplicateException() {
        DemoUserDTO demoUserDTO = new DemoUserDTO("test", "test");
        when(demoUserRepository.insert(any(DemoUser.class)))
                .thenThrow(DemoUserDuplicateException.class);
        assertThrows(DemoUserDuplicateException.class, () -> demoUserService.saveDemoUser(demoUserDTO), "DemoUserDuplicateException should be thrown");
    }

}
