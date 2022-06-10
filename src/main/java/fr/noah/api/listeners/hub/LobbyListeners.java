package fr.noah.api.listeners.hub;

import fr.noah.api.API;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import static fr.noah.api.commons.Constant.PREFIX_INFO;
import static org.bukkit.Bukkit.getMaxPlayers;


public class LobbyListeners implements Listener {

    private API main;

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

    public LobbyListeners(API main) {
        this.main = main;
    }


    @EventHandler
    public void onDamageWatting(EntityDamageEvent event) {

    }

    @EventHandler
    public void onDamageOverPvp(EntityDamageEvent event) {

    }

    @EventHandler
    public void onBlockPlaceLobby(BlockBreakEvent event) {

    }

    @EventHandler
    public void onBreakBlockLobby(BlockPlaceEvent event) {


    }

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent event) {

    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {


    }

    @EventHandler
    public void foodLevelChangeModeration(FoodLevelChangeEvent e) {
        Player p = (Player) e.getEntity();
            e.setCancelled(true);
            p.setFoodLevel(25);
            p.setSaturation(20);

    }

}
