package com.bezkoder.spring.datajpa.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
public class  WalletDTO  {
    private BigDecimal change_value;//"- transfer money {transfer account}" "+ recycle {recycle_type} add {total_amount}"
    private String description;
    private String username;
}
