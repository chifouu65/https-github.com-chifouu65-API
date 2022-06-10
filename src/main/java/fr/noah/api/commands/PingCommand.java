package fr.noah.api.commands;

import fr.noah.api.API;
import fr.noah.api.commands.framework.Command;
import fr.noah.api.commands.framework.CommandArgs;
import fr.noah.api.utils.reflexion.Reflexion;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand {
    private API INSTANCE;


    public PingCommand(API INSTANCE) {
        this.INSTANCE = INSTANCE;
    }

    @Command(name = "ping", aliases = {"ping"})
    public void onStart(CommandArgs args) {
        CommandSender sender = args.getSender();
        if ((sender instanceof Player)) {

            Player player = (Player) sender;
            int ping = Reflexion.getPing(player);

            if (args.length() == 0) {
                player.sendMessage("§l§ePing :§r " + ping);
            }
        }
    }
}
