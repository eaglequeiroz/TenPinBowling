package com.tenpin;

import com.tenpin.service.interfaces.IGameScorePanelService;
import com.tenpin.service.GameScorePanelService;

public class Main {

    public static void main(String[] args) {

        IGameScorePanelService service = new GameScorePanelService();

        if (args.length == 0){
            args = new String[]{"/home/igor/Documentos/game.txt"};
        }

        service.showPanel(args[0]);

    }
}
