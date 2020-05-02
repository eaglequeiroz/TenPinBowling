package com.tenpin.service.interfaces;

import com.tenpin.model.Player;

import java.util.List;

public interface IPlayersService {

    List<Player> getPlayers(String filePath);
}
