package fr.noah.api.commands;

import fr.noah.api.API;
import fr.noah.api.commands.framework.Command;
import fr.noah.api.commands.framework.CommandArgs;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand {

    private API main;

    public TestCommand(API main){
        this.main = main;
    }

    @Command(name = "start", aliases = {"start"})
    public void onStart(CommandArgs args) {
        CommandSender sender = args.getSender();
        if ((sender instanceof Player)) {

            Player player = (Player) sender;

            player.sendMessage("§i test ");

        }
    }
}


