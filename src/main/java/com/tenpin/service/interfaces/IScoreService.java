package com.tenpin.service.interfaces;

import com.tenpin.model.Player;

public interface IScoreService {

    Player buildFrames(Player player);

    Player calculateScores(Player player);
}
