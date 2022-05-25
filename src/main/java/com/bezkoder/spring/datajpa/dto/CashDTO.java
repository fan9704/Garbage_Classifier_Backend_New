package com.bezkoder.spring.datajpa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashDTO {
    private long userId;
    private long cash;
}