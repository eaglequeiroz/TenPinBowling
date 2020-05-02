package com.tenpin.service;

import com.tenpin.model.Frame;
import com.tenpin.model.Player;
import com.tenpin.service.interfaces.IGameResultService;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class GameResultService implements IGameResultService {

    @Override
    public void showResult(List<Player> players) {
        System.out.println(buildScoreBoard(players));
    }

    private String buildScoreBoard(List<Player> players) {
        StringBuilder sb = new StringBuilder();
        sb.append(buildFrameHeader());
        sb.append("\n");
        players.forEach(player -> {
            sb.append(player.getName());
            sb.append("\n");
            sb.append("Pinfalls");
            sb.append(buildPinFalls(player.getFrames()));
            sb.append("\n");
            sb.append("Score");
            sb.append(buildScore(player.getFrames()));
            sb.append("\n");
        });
        return sb.toString();

    }

    private String buildFrameHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("Frame\t\t");
        IntStream.range(1, 11).forEach(value -> {
            sb.append(value);
            sb.append("\t\t");
        });
        return sb.toString();
    }

    private String buildPinFalls(List<Frame> frames) {
        StringBuilder sb = new StringBuilder();

        frames.forEach(frame -> {
            Integer firstScore = frame.getFirstBall();
            Integer secondScore = frame.getSecondBall();
            Integer finalScore = frame.getFinalBall();

            sb.append("\t");
            if (frame.isStrike()) {
                sb.append("\tX");
            } else if (frame.isSpare()) {
                sb.append(firstScore);
                sb.append("\t/");
            } else {
                sb.append(firstScore == 10 ? "X" : firstScore);
                sb.append("\t");
                sb.append(Optional.ofNullable(secondScore).orElse(0));
                if (frame.getRound() == 10) {
                    sb.append("\t");
                    sb.append(finalScore);
                }
            }
        });
        return sb.toString();
    }

    private String buildScore(List<Frame> frames) {
        StringBuilder sb = new StringBuilder();
        frames.forEach(frame -> {
            sb.append("\t\t");
            sb.append(frame.getScore());
        });
        return sb.toString();
    }

}
