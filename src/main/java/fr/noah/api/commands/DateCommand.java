package fr.noah.api.commands;

import fr.noah.api.API;
import fr.noah.api.commands.framework.Command;
import fr.noah.api.commands.framework.CommandArgs;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCommand {

    private API INSTANCE;

    public DateCommand(API INSTANCE) {
        this.INSTANCE = INSTANCE;
    }

    @Command(name = "date", aliases = {"date"})
    public void onStart(CommandArgs args) {
        CommandSender sender = args.getSender();
        if ((sender instanceof Player)) {

            Player player = (Player) sender;

            if (args.length() == 0) {
                player.sendMessage("Date is : " + Date());
            }

            player.sendMessage("§i test ");

        }
    }

    public static String Date() {
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        Date d = new Date();
        String date = "§a" + format.format(d);

        return date;
    }

}