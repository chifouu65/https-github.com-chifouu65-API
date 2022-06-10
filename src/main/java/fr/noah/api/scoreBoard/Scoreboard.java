package fr.noah.api.scoreBoard;

import fr.noah.api.commands.DateCommand;
import fr.noah.api.utils.reflexion.Reflexion;
import me.tigerhix.lib.scoreboard.ScoreboardLib;
import me.tigerhix.lib.scoreboard.common.EntryBuilder;
import me.tigerhix.lib.scoreboard.common.animate.HighlightedString;
import me.tigerhix.lib.scoreboard.type.Entry;
import me.tigerhix.lib.scoreboard.type.ScoreboardHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class Scoreboard {


    public static void scoreBoard() {
        for (Player player : getServer().getOnlinePlayers()) {
            me.tigerhix.lib.scoreboard.type.Scoreboard scoreboard = ScoreboardLib.createScoreboard(player)
                    .setHandler(new ScoreboardHandler() {

                        private final HighlightedString title = new HighlightedString(  "CLOVER-MC", "&a", "&2");
                        private final HighlightedString ip = new HighlightedString("ip : clovermc.fr", "&6", "&e");

                        @Override
                        public String getTitle(Player player) {
                            return null;
                        }

                        @Override
                        public List<Entry> getEntries(Player player) {
                            return new EntryBuilder()
                                    .next("" + title.next())
                                    .next("   §r" + DateCommand.Date())
                                    .blank()
                                    .next("&c ❙ &lRank : ")
                                    .next("   " + player.getCustomName())
                                    .blank()
                                    .next("&e ❙ &lPing : ")
                                    .next("   " + Reflexion.getPing(player))
                                    .blank()
                                    .next("&7§ldiscord.gg/CloverMC")
                                    .next("" + ip.next())
                                    .blank()
                                    .build();
                        }

                    })
                    .setUpdateInterval(4l);
            scoreboard.deactivate();
            scoreboard.activate();
        }
    }

}
