package com.volkov.demoproducer.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.volkov.demoproducer.repository.DemoUserRepository;
import java.util.Arrays;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

@SpringBootTest
public class DemoUserTest {

    @Autowired
    DemoUserRepository demoUserRepository;

    @BeforeEach
    @AfterEach
    void postCleanUp() {
        demoUserRepository.deleteAll();
    }

    @Test
    public void when_DemoUserIsGoodFormed_then_SaveSuccessfully() {
        DemoUser demoUser = new DemoUser("test", "test");
        demoUserRepository.save(demoUser);
        assertTrue(demoUserRepository.findAll().size() == 1, "good formed DemoUser should be successfully saved");
    }

    @Test
    public void when_DemoUserTestUsernameIsBlank_then_throwConstraintViolationException() {
        final String blankString = "";
        final String nullString = null;

        DemoUser demoUserBlank = new DemoUser(blankString, "testtest");
        DemoUser demoUserNull = new DemoUser(nullString, "testtesttest");
        assertAll(() -> {
            assertThrows(ConstraintViolationException.class, () -> demoUserRepository.save(demoUserBlank)
                    , "exception should be thrown when setting blank username");
            assertThrows(ConstraintViolationException.class, () -> demoUserRepository.save(demoUserNull),
                    "exception should be thrown when setting null username");
        });
    }

    @Test
    public void when_DemoUserTestFirstNameIsBlankOrNull_then_throwConstraintViolationException() {
        final String blankString = "";
        final String nullString = null;

        DemoUser demoUserBlank = new DemoUser("testtest", blankString);
        DemoUser demoUserNull = new DemoUser("testtesttest", nullString);
        assertAll(() -> {
            assertThrows(ConstraintViolationException.class, () -> demoUserRepository.save(demoUserBlank)
                    , "exception should be thrown when setting blank username");
            assertThrows(ConstraintViolationException.class, () -> demoUserRepository.save(demoUserNull),
                    "exception should be thrown when setting null username");
        });

    }

    @Test
    public void when_DemoUserUsernameIsMoreThanUSERNAME_MAX_LENGTH_then_throwConstraintViolationException() {
        String stringLongerThanUSERNAME_MAX_LENGTH =
                getStringWithLengthAndFilledWithCharacter(DemoUser.USERNAME_MAX_LENGTH + 1, '*');
        DemoUser demoUser = new DemoUser(stringLongerThanUSERNAME_MAX_LENGTH, "test");
        assertThrows(ConstraintViolationException.class, () -> demoUserRepository.save(demoUser)
                , "exception should be thrown when username is longer than constraint USERNAME_MAX_LENGTH");
    }

    @Test
    public void when_DemoUserFirstnameIsMoreThanFIRSTNAME_MAX_LENGTH_then_throwConstraintViolationException() {
        String stringLongerThanFIRSTNAME_MAX_LENGTH =
                getStringWithLengthAndFilledWithCharacter(DemoUser.FIRSTNAME_MAX_LENGTH + 1, '*');
        DemoUser demoUser = new DemoUser("testtest", stringLongerThanFIRSTNAME_MAX_LENGTH);
        assertThrows(ConstraintViolationException.class, () -> demoUserRepository.save(demoUser)
                , "exception should be thrown when username is longer than constraint FIRSTNAME_MAX_LENGTH");
    }

    @Test
    public void when_DemoUserWithSameParametersAlreadyExists_then_throw_DuplicateKeyException() {
        DemoUser demoUser = new DemoUser("ddd", "ddd");
        DemoUser duplicatedDemoUser = new DemoUser("ddd", "ddd");

        demoUserRepository.save(demoUser);
        assertThrows(DuplicateKeyException.class, () -> demoUserRepository.save(duplicatedDemoUser),
                "exception should be thrown when saving duplicated entity");
    }

    protected String getStringWithLengthAndFilledWithCharacter(int length, char charToFill) {
        if (length > 0) {
            char[] array = new char[length];
            Arrays.fill(array, charToFill);
            return new String(array).intern();
        }
        return "";
    }
}
