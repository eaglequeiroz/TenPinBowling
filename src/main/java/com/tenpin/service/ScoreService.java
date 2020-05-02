package com.tenpin.service;

import com.tenpin.model.Frame;
import com.tenpin.service.interfaces.IScoreService;
import com.tenpin.model.Player;
import com.tenpin.utils.ScoreHelper;

import java.util.stream.IntStream;

public class ScoreService implements IScoreService {

    private final int STRIKE = 10;
    private Frame currentFrame;
    private int round = 1;

    @Override
    public Player calculatePlayerScores(Player player) {

        IntStream.range(0, player.getScores().size()).forEach(index -> {
            String score = player.getScores().get(index);
            Integer intScore = ScoreHelper.getIntScore(score);

            if (index == 0) currentFrame = new Frame();
            if (!currentFrame.isOpen() || index == 0) {
                currentFrame.setFirstBall(intScore);
                if (intScore == STRIKE && index < player.getScores().size() - 3) {
                    currentFrame.setRound(round);
                    round++;
                    currentFrame = new Frame();
                } else {
                    currentFrame.setOpen(true);
                }
            } else if (index == player.getScores().size() - 1) {
                currentFrame.setFinalBall(intScore);
                currentFrame.setRound(round);
                round++;
            } else {
                currentFrame.setSecondBall(intScore);
                if (index < player.getScores().size() - 2) {
                    currentFrame.setRound(round);
                    round++;
                    currentFrame = new Frame();
                }
            }
            player.getFrames().add(currentFrame);

        });

        return player;
    }
}
