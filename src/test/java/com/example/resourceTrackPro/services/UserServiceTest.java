package com.example.resourceTrackPro.services;

import com.example.resourceTrackPro.entities.User;
import com.example.resourceTrackPro.repositories.UserRepositoryImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    static UserRepositoryImpl userRepository;
    static  UserService userService;
    //    HttpServletRequest request;
    @BeforeAll
    public static void init() throws Exception{
        userService = new UserService();
        userRepository = mock(UserRepositoryImpl.class);

    }
    @Test
    public void test_valid_username_and_password() {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);


        User user = new User();
        user.setUsername("abdelaziz");
        user.setPassword("abdelaziz12");

        when(userRepository.findByUsername("abdelaziz")).thenReturn(Optional.of(user));
        when(request.getSession()).thenReturn(session);

        boolean result = userService.login("abdelaziz", "abdelaziz12", request);

        assertTrue(result);
//        verify(session).setAttribute("user", user);
    }

    @Test
    public void test_save_user_and_return_saved_user() {


        User user = new User("username", "email", "password");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.register(user);

        assertEquals(user, result);
    }
    @Test
    public void test_password_no_digit() {
        User user = new User("testUser", "test@example.com", "Password@");
        boolean result = userService.validLoginDetails(user);
        assertFalse(result);
    }
    @Test
    public void test_password_no_special_character() {
        User user = new User("testUser", "test@example.com", "Password123");
        boolean result = userService.validLoginDetails(user);
        assertFalse(result);
    }

    @Test
    public void test_password_less_than_8_characters() {
        User user = new User("testUser", "test@example.com", "Pass12@");
        boolean result = userService.validLoginDetails(user);
        assertFalse(result);
    }

}