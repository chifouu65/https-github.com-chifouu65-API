package fr.noah.api.listeners.hub;

import fr.noah.api.API;
import fr.noah.api.scoreBoard.Scoreboard;
import fr.noah.api.utils.reflexion.Reflexion;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static fr.noah.api.commons.Constant.PREFIX_INFO;
import static org.bukkit.Bukkit.getMaxPlayers;


public class LobbyListeners implements Listener {

    private API main;


    public LobbyListeners(API main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location lobby = new Location(Bukkit.getWorld("world"), 0.50, 106, 0.50, 90.4f, 1.6f);
        player.teleport(lobby);
        //Player LOBBY event
        player.setCustomName("Player");
        player.setLevel(1000);
        if (!main.getPlayers().contains(player)) main.getPlayers().add(player);
        player.setGameMode(GameMode.ADVENTURE);
        event.setJoinMessage(PREFIX_INFO + player.getName() + "§a have join §f§l +1 [" + main.getPlayers().size() + "/" + getMaxPlayers() + "]");

        Scoreboard.scoreBoard();

    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDamageOverPvp(EntityDamageEvent e) {
        e.setCancelled(true);

    }

    @EventHandler
    public void onBlockPlaceLobby(BlockBreakEvent e) {
        Player player = e.getPlayer();

        if(!player.isOp()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreakBlockLobby(BlockPlaceEvent e) {
        Player player = e.getPlayer();

        if(!player.isOp()) {
            e.setCancelled(true);
        }

    }
    @Deprecated
    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent e) {
        Player player = e.getPlayer();

        if(!player.isOp()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        Player player = e.getPlayer();

        if(!player.isOp()) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void foodLevelChangeModeration(FoodLevelChangeEvent e) {
        Player p = (Player) e.getEntity();
            e.setCancelled(true);
            p.setFoodLevel(25);
            p.setSaturation(20);

    }

}
