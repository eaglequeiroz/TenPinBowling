package com.tenpin.validators.interfaces;

import com.tenpin.model.Frame;

import java.util.List;

public interface IGameValidator {

    List<String> validate(List<Frame> frames);
}
