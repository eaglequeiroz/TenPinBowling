package com.tenpin.model;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Player {

    private String name;
    private List<String> scores;
    private List<Frame> frames = new LinkedList<>();
}
