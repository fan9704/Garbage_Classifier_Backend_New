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
public class UserCauseEffectTest {
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
    void getUserByIdWithCauseEffect() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(u0));
        ResponseEntity responseEntity =userService.getUserById(1);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(u0);
        System.out.println("Cause Effect Pass");
    }
}
