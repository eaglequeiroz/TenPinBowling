package com.tenpin.service;

import com.tenpin.service.interfaces.IGameResultService;
import com.tenpin.service.interfaces.IGameScorePanelService;
import com.tenpin.service.interfaces.IPlayersService;
import com.tenpin.service.interfaces.IScoreService;
import com.tenpin.model.Player;

import java.util.List;

public class GameScorePanelService implements IGameScorePanelService {

    private final IPlayersService playersService = new PlayerService();
    private final IScoreService scoreService = new ScoreService();
    private final IGameResultService resultService = new GameResultService();

    @Override
    public void showPanel(String filePath) {
        try {
            List<Player> players = playersService.getPlayers(filePath);

            players.forEach(scoreService::buildFrames);
            //players.forEach(scoreService::calculateScores);

            resultService.showResult(players);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
