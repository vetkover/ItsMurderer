package me.vetkover.itsmurderer.events;

import me.vetkover.itsmurderer.stuff.JsonWork;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.json.JSONObject;

public class onPlayerJoin implements Listener {
    public onPlayerJoin(PlayerJoinEvent event) {
        String playerEventName = event.getPlayer().getName();
        Player player = Bukkit.getPlayer(playerEventName);
        JSONObject userJSON = JsonWork.findOneJson(playerEventName);

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        Team team1 = scoreboard.getTeam("sus");
        Team team2 = scoreboard.getTeam("murder");
        if(!userJSON.equals(null)){

            if(userJSON.getString("status").contains("SUS")){
                player.setDisplayName("[SUS] " + playerEventName);
                player.setPlayerListName("[SUS] " + playerEventName);

                team1.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
                team1.addEntry(playerEventName);
                team1.setPrefix("[SUS] ");
                player.setScoreboard(scoreboard);
            }

            if(userJSON.getString("status").contains("MURDER")) {
                player.setDisplayName("[MURDER] " + playerEventName);
                player.setPlayerListName("[MURDER] " + playerEventName);

                team2.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
                team2.addEntry(playerEventName);
                team2.setPrefix("[MURDER] ");
                player.setScoreboard(scoreboard);
            }
        } else {
            team1.removeEntry(playerEventName);
            team2.removeEntry(playerEventName);
        }
    }
}
