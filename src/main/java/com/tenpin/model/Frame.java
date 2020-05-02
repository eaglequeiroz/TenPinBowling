package com.tenpin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Frame {

    private Integer firstBall;
    private Integer secondBall;
    private Integer finalBall;
    private Integer round;
    private boolean open;
    private Integer score = 0;

    public boolean isStrike(){
        return (firstBall == 10 || Optional.ofNullable(secondBall).orElse(0) == 10) && round < 10;
    }

    public boolean isSpare(){
        return !this.isStrike() && firstBall + Optional.ofNullable(secondBall).orElse(0) == 10 && round < 10;
    }
}
