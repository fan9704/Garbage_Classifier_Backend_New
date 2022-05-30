package com.bezkoder.spring.datajpa.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Note {
    private String subject;
    private String content;
    private Map<String, String> data = new HashMap<>();
    private String image;
}