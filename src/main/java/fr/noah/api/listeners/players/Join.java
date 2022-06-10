package fr.noah.api.listeners.players;

import fr.noah.api.API;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import static fr.noah.api.commons.Constant.PREFIX_INFO;
import static org.bukkit.Bukkit.getMaxPlayers;

public class Join implements Listener {

    private API main;

    public Join(API main) {
        this.main = main;
    }


    @EventHandler
        public void onJoin(PlayerJoinEvent event) {
            Player player = event.getPlayer();
            Location lobby = new Location(Bukkit.getWorld("world"), 0.50, 106, 0.50, 90.4f, 1.6f);
            player.teleport(lobby);
            //Player LOBBY event
            player.setCustomName("Player");
            player.setLevel(0);

            if (!main.getPlayers().contains(player)) main.getPlayers().add(player);
            player.setGameMode(GameMode.ADVENTURE);
            event.setJoinMessage(PREFIX_INFO + player.getName() + "§f§l +1 [" + main.getPlayers().size() + "/" + getMaxPlayers() + "]");


        }
}
