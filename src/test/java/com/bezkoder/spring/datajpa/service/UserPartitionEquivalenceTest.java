package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Transfer_money_record;
import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.repository.TransferMoneyRecordRepository;
import com.bezkoder.spring.datajpa.repository.UserRepository;
import com.bezkoder.spring.datajpa.service.TransferMoney.TransferMoneyRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
public class UserPartitionEquivalenceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    User u0 =new User(0,"user0","user0@gmail.com","user0","user0","user0",false,"000", new HashSet<>(),null);
    User u1 =new User(1,"user1","user1@gmail.com","user1","user1","user1",false,"000", new HashSet<>(),null);
    User u2 =new User(2,"user2","user2@mail.com","user2","user2","user2",false,"001", new HashSet<>(),null);
    User u3 =new User(3,"user3","user3@mail.com","user3","user3","user3",false,"002", new HashSet<>(),null);
    User u4 =new User(4,"user4","user4@mail.com","user4","user4","user4",false,"003", new HashSet<>(),null);
    User umax =new User(Long.MAX_VALUE,"user9223372036854775807","user9223372036854775807@mail.com","user9223372036854775807","user9223372036854775807","user9223372036854775807",false,"009223372036854775807", new HashSet<>(),null);
    User umaxMinus1 =new User(Long.MAX_VALUE-1,"user9223372036854775807","user9223372036854775807@mail.com","user9223372036854775807","user9223372036854775807","user9223372036854775807",false,"009223372036854775807", new HashSet<>(),null);
    @Test
    void getUserByIdWithNegative() {
        Mockito.when(userRepository.findById(-1L)).thenReturn(null);

        ResponseEntity responseEntity =userService.getUserById(-1);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
        assertThat(responseEntity.getBody()).isEqualTo(null);
        System.out.println("Equivalence Partitioning Negative Pass");
    }
    @Test
    void getUserByIdWithPositive() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(u1));
        ResponseEntity responseEntity =userService.getUserById(1);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(u1);
        System.out.println("Equivalence Partitioning Positive Pass");
    }
    @Test
    void getUserByIdWithOverFlow() {
        Mockito.when(userRepository.findById(Long.MAX_VALUE+1)).thenReturn(Optional.of(umax));
        ResponseEntity responseEntity =userService.getUserById(Long.MAX_VALUE+1);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
        assertThat(responseEntity.getBody()).isEqualTo(null);
        System.out.println("Equivalence Partitioning OverFlow Pass");
    }
}
