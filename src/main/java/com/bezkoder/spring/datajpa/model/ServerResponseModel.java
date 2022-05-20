package com.bezkoder.spring.datajpa.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerResponseModel {
    private String responseMessage;

    public ServerResponseModel(String s) {
        this.responseMessage=s;
    }
}
