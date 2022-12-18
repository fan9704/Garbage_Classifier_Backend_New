package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
public class UserBoundaryAnalysisTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    User u0 =new User(0,"user0","user0@gmail.com","user0","user0","user0",false,"000", new HashSet<>(),null);
    User u1 =new User(1,"user1","user1@gmail.com","user1","user1","user1",false,"000", new HashSet<>(),null);

    User umax =new User(Long.MAX_VALUE,"user9223372036854775807","user9223372036854775807@mail.com","user9223372036854775807","user9223372036854775807","user9223372036854775807",false,"009223372036854775807", new HashSet<>(),null);
    User umaxMinus1 =new User(Long.MAX_VALUE-1,"user9223372036854775807","user9223372036854775807@mail.com","user9223372036854775807","user9223372036854775807","user9223372036854775807",false,"009223372036854775807", new HashSet<>(),null);


    @Test
    void getUserByIdWithLeftBoundary() {
        Mockito.when(userRepository.findById(0L)).thenReturn(Optional.of(u0));
        ResponseEntity responseEntity =userService.getUserById(0);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(u0);
        System.out.println("Left Boundary Analysis Pass");
    }
    @Test
    void getUserByIdWithLeftBoundaryPlusOne() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(u1));
        ResponseEntity responseEntity =userService.getUserById(1);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(u1);
        System.out.println("Left Boundary Analysis Plus One Pass");
    }
    @Test
    void getUserByIdWithRightBoundaryMinusOne() {
        Mockito.when(userRepository.findById(Long.MAX_VALUE-1)).thenReturn(Optional.of(umaxMinus1));
        ResponseEntity responseEntity =userService.getUserById(Long.MAX_VALUE-1);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(umaxMinus1);
        System.out.println("Right Boundary Analysis Minus One Pass");
    }
    @Test
    void getUserByIdWithRightBoundary() {
        Mockito.when(userRepository.findById(Long.MAX_VALUE)).thenReturn(Optional.of(umax));
        ResponseEntity responseEntity =userService.getUserById(Long.MAX_VALUE);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(umax);
        System.out.println("Right Boundary Analysis One Pass");
    }
    @Test
    void getUserByIdWithLeftBoundaryMinusOne() {
        ResponseEntity responseEntity =userService.getUserById(-1);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
        assertThat(responseEntity.getBody()).isEqualTo(null);
        System.out.println("Left Boundary Minus One Analysis Pass");
    }


    @Test
    void getUserByIdWithRightBoundaryPlusOne() {
        ResponseEntity responseEntity =userService.getUserById(Long.MAX_VALUE+1);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
        assertThat(responseEntity.getBody()).isEqualTo(null);
        System.out.println("Right Boundary Analysis Plus One Pass");
    }
}
