package com.bezkoder.spring.datajpa.service.TransferMoney;

import com.bezkoder.spring.datajpa.dto.Transfer_money_recordDTO;
import com.bezkoder.spring.datajpa.model.Transfer_money_record;
import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.repository.TransferMoneyRecordRepository;
import com.bezkoder.spring.datajpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
class TransferMoneyRecordServiceTest {
    private MockMvc mockMvc;
    @InjectMocks
    private TransferMoneyRecordService transferMoneyRecordService;
    @Mock
    private TransferMoneyRecordRepository transferMoneyRecordRepository;
    @Mock
    private UserRepository userRepository;

    User u1 =new User(1,"user1","user1@gmail.com","user1","user1","user1",false,"000", new HashSet<>(),null);
    Transfer_money_record t1 = new Transfer_money_record(1,u1,new BigDecimal(120),null,"華南銀行");
    @Test
    void TransferMoneyCauseEffectPositiveValidReceiver() {
        Transfer_money_recordDTO transfer_money_recordDTO = new Transfer_money_recordDTO(1,new BigDecimal(100),"華南銀行");
        Mockito.when(transferMoneyRecordRepository.findById(1L)).thenReturn(Optional.of(t1));
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(u1));
        Mockito.when(transferMoneyRecordRepository.save(any(Transfer_money_record.class))).thenReturn(t1);

        ResponseEntity<Transfer_money_record> ResTransferMoneyRecord = transferMoneyRecordService.patchTransfer_money_record(1L,transfer_money_recordDTO);

        assertThat(ResTransferMoneyRecord.getStatusCodeValue()).isEqualTo(200);

        System.out.println("Test Cause-Effect Positive Valid Receiver Pass");
    }

    @Test
    void TransferMoneyCauseEffectNegativeValidReceiver() throws JsonProcessingException {
        Transfer_money_recordDTO transfer_money_recordDTO = new Transfer_money_recordDTO(1,new BigDecimal(-100)
                ,"華南銀行");
        Mockito.when(transferMoneyRecordRepository.findById(1L)).thenReturn(Optional.of(t1));
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(u1));

        ResponseEntity<Transfer_money_record> ResTransferMoneyRecord = transferMoneyRecordService.patchTransfer_money_record(
                1L,transfer_money_recordDTO);

        assertThat(ResTransferMoneyRecord.getStatusCodeValue()).isEqualTo(404);

        System.out.println("Test Cause-Effect Negative Valid Receiver Pass");
    }

    @Test
    void TransferMoneyCauseEffectNegativeInvalidReceiver() {
        Transfer_money_recordDTO transfer_money_recordDTO = new Transfer_money_recordDTO(-1,new BigDecimal(-100),"華南銀行");

        ResponseEntity<Transfer_money_record> ResTransferMoneyRecord = transferMoneyRecordService.patchTransfer_money_record(-1L
                ,transfer_money_recordDTO);

        assertThat(ResTransferMoneyRecord.getStatusCodeValue()).isEqualTo(404);

        System.out.println("Test Cause-Effect Negative Invalid Receiver Pass");
    }
    @Test
    void TransferMoneyCauseEffectPositiveInvalidReceiver() {
        Transfer_money_recordDTO transfer_money_recordDTO = new Transfer_money_recordDTO(-1,new BigDecimal(100)
                ,"華南銀行");

        ResponseEntity<Transfer_money_record> ResTransferMoneyRecord = transferMoneyRecordService.patchTransfer_money_record(
                -1L,transfer_money_recordDTO);

        assertThat(ResTransferMoneyRecord.getStatusCodeValue()).isEqualTo(404);

        System.out.println("Test Cause-Effect Positive Invalid Receiver Pass");
    }

    private String convertToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}