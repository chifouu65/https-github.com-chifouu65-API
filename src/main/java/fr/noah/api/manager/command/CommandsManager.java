package fr.noah.api.manager.command;

import fr.noah.api.API;
import fr.noah.api.commands.DateCommand;
import fr.noah.api.commands.PingCommand;
import fr.noah.api.commands.TestCommand;
import fr.noah.api.commands.framework.CommandFramework;

public class CommandsManager {

    private static CommandFramework framework;

    public static void setupCommandsSystem(API plugin) {
        framework = new CommandFramework(plugin);

        registerNewCommand(new TestCommand(plugin));
        registerNewCommand(new PingCommand(plugin));
        registerNewCommand(new DateCommand(plugin));
    }

    public static void registerNewCommand(Object object) {
        framework.registerCommands(object);
    }

}