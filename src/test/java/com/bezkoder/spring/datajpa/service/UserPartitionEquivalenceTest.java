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
     User u1 =new User(1,"user1","user1@gmail.com","user1","user1","user1",false,"000", new HashSet<>(),null);
    @Test
    void getUserByIdWithNegative() {
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
        ResponseEntity responseEntity =userService.getUserById(Long.MAX_VALUE+1);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
        assertThat(responseEntity.getBody()).isEqualTo(null);
        System.out.println("Equivalence Partitioning OverFlow Pass");
    }
}
