package fr.noah.api;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.noah.api.manager.command.CommandsManager;
import fr.noah.api.manager.event.EventManager;
import fr.noah.api.manager.game.GameManager;
import me.tigerhix.lib.scoreboard.ScoreboardLib;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class API extends JavaPlugin {

    private EventManager eventManager;
    public List<Player> players = new ArrayList<>();
    public List<Location> spawn = new ArrayList<>();
    private GameManager gameManager;
    public static API INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        //Addons
        CommandsManager.setupCommandsSystem(this);
        ScoreboardLib.setPluginInstance(this);

        //Manager
        this.gameManager = new GameManager(this);
        this.eventManager = new EventManager(this);

        //initialization
        onEnableServer();
        MongoConnect();

    }

    private static void MongoConnect() {

        //connect to the server/database/collection
        MongoClient mongoClient = MongoClients.create("mongodb+srv://bob123:Password123@spigotcluster.jv71r.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("mongodb_test");
        MongoCollection<Document> col = database.getCollection("newdata");

        //Print out the contents of each document in the collection
        Document filter = new Document("class_id", 355);
        col.find(filter).forEach((Consumer<Document>) document -> {
            System.out.println(document.toJson());
        });

        //Write a new document to the collection
        Document data = new Document("type", "car")
                .append("wheels", 4)
                .append("isFast", true);
        col.insertOne(data);

        System.out.println("Plugin has started up");
    }

    public void onEnableServer () {
        Bukkit.getWorlds().forEach(world -> {
            world.setGameRuleValue("commandBlockOutput", "false");
            world.setGameRuleValue("announceAdvancements", "false");
            world.setGameRuleValue("doMobSpawning", "false");
            world.setGameRuleValue("doDaylightCycle", "false");
            world.setGameRuleValue("doWeatherCycle", "false");
            world.setGameRuleValue("randomTickSpeed", "false");
        });
    }

    @Override
    public void onDisable() {
        System.out.println("API is DISABLE");

    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public static API getINSTANCE() {
        return INSTANCE;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Location> getGameSpawn() {
        return spawn;
    }
}

