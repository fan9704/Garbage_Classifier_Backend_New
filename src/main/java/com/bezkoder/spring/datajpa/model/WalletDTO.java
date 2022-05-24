package com.bezkoder.spring.datajpa.model;

import lombok.*;
import java.math.BigDecimal;

@Data
public class  WalletDTO  {
    private BigDecimal change_value;//"- transfer money {transfer account}" "+ recycle {recycle_type} add {total_amount}"
    private String description;
    private String username;
}
