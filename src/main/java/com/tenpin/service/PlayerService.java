package com.tenpin.service;

import com.tenpin.service.interfaces.IPlayersService;
import com.tenpin.model.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class PlayerService implements IPlayersService {

    @Override
    public List<Player> getPlayers(String filePath) {
        Path path = Paths.get(filePath);
        Stream<String> lines = null;
        try {
            lines = Files.lines(path);
        } catch (IOException e) {
            System.out.println(" *** FILE NOT FOUND! *** ");
        }

        Map<String, List<String>> playerMap = lines.collect(
                groupingBy(
                        player -> player.substring(0, player.indexOf("\t")),
                        mapping(line -> line.substring(line.indexOf("\t") + 1), toList())
                ));

        List<Player> players = new LinkedList<>();
        playerMap.forEach((player, scores) -> {
            Player p = new Player();
            p.setName(player);
            p.setScores(scores);
            players.add(p);
        });
        return players;
    }
}
