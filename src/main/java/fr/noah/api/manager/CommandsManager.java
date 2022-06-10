package fr.noah.api.manager;

import fr.noah.api.API;
import fr.noah.api.commands.TestCommand;
import fr.noah.api.commands.framework.CommandFramework;

public class CommandsManager {

    private static CommandFramework framework;

    public static void setupCommandsSystem(API plugin) {
        framework = new CommandFramework(plugin);

        registerNewCommand(new TestCommand(plugin));
    }

    public static void registerNewCommand(Object object) {
        framework.registerCommands(object);
    }

}