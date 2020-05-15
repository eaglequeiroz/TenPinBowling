package com.tenpin.service;

import com.tenpin.model.Frame;
import com.tenpin.model.Player;
import com.tenpin.service.interfaces.IScoreService;
import com.tenpin.utils.ScoreHelper;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class ScoreService implements IScoreService {

    private final int STRIKE = 10;
    private Frame currentFrame;
    private int round = 1;

    @Override
    public Player buildFrames(Player player) {

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

    @Override
    public Player calculateScores(Player player) {
        player.getFrames().forEach((frame) -> {
            int total;
            if (frame.isStrike()) {
                total = calcStrikes(frame.getRound(), player.getFrames());
            } else if (frame.isSpare()) {
                total = calcSpares(frame.getRound(), player.getFrames());
            } else {
                total = calcNormalScores(frame.getRound(), player.getFrames());
            }
            frame.setScore(total);
        });

        return player;
    }

    private int calcStrikes(Integer round, List<Frame> frames) {
        int total = 10;
        int index = round - 1;
        int previousScore = index != 0 ? frames.get(index - 1).getScore() : 0;
        if (round < frames.size()) {
            Frame nextFrame = frames.get(index + 1);

            if (nextFrame.isStrike()) {
                Frame nextNextFrame = frames.get(index + 2);
                total += previousScore + nextFrame.getFirstBall() + Optional.ofNullable(nextNextFrame.getFirstBall()).orElse(0);
            } else {
                total += previousScore + nextFrame.getFirstBall() + Optional.ofNullable(nextFrame.getSecondBall()).orElse(0);
            }
        }
        return total;
    }

    private int calcSpares(Integer round, List<Frame> frames) {
        int total = 10;
        if (round < frames.size()) {
            int index = round - 1;
            int previousScore = index != 0 ? frames.get(index - 1).getScore() : 0;
            Frame nextFrame = frames.get(index + 1);
            total += previousScore + nextFrame.getFirstBall();
        }
        return total;
    }

    private int calcNormalScores(Integer round, List<Frame> frames) {
        int index = round - 1;
        int previousScore = index != 0 ? frames.get(index - 1).getScore() : 0;
        Frame frame = frames.get(index);

        return frame.getFirstBall() + Optional.ofNullable(frame.getSecondBall()).orElse(0) + Optional.ofNullable(frame.getFinalBall()).orElse(0) + previousScore;
    }


}
