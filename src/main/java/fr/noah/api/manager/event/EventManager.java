package fr.noah.api.manager.event;

import fr.noah.api.API;
import fr.noah.api.listeners.hub.LobbyListeners;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    private final API api;
    private final PluginManager pluginManager;

    public EventManager(API api) {
        this.api = api;
        this.pluginManager = Bukkit.getPluginManager();
        registerEvents();
    }

    private void registerEvents() {
        pluginManager.registerEvents(new LobbyListeners(api), api);
    }

    public void addNewEveListener(Listener listener) {
        pluginManager.registerEvents(listener, api);
    }


}
