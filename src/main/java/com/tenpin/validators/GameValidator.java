package com.tenpin.validators;

import com.tenpin.validators.interfaces.IGameValidator;
import com.tenpin.model.Frame;

import java.util.ArrayList;
import java.util.List;

public class GameValidator implements IGameValidator {

    private final String LESS_THAN_10_PLAYS = "Bad input! A Player can't throw less than 10 plays.";
    private final String MORE_THAN_10_PLAYS = "Bad input! A Player can't throw more than 10 plays.";
    private final String NEGATIVE_PIN_INPUT = "Bad input! It's not possible negative values for pins.";
    private final String EXCEED_PIN_INPUT = "Bad input! You exceed the pin limit.";

    List<String> errors = new ArrayList<>();


    @Override
    public List<String> validate(List<Frame> frames) {
        validatePins(frames);
        validateFrames(frames);
        return errors;
    }

    private void validateFrames(List<Frame> frames) {
        if (frames.size() < 10) errors.add(LESS_THAN_10_PLAYS);
        if (frames.size() > 10) errors.add(MORE_THAN_10_PLAYS);
    }

    private void validatePins(List<Frame> frames) {
        frames.forEach(frame -> {
            if(frame.getFirstBall() < 0 || frame.getSecondBall() < 0 || frame.getFinalBall() < 0) errors.add(NEGATIVE_PIN_INPUT);
            if(frame.getFirstBall() + frame.getSecondBall() > 10 ||
                    frame.getFirstBall() + frame.getSecondBall() + frame.getFinalBall() > 30) errors.add(EXCEED_PIN_INPUT);
        });
    }
}
