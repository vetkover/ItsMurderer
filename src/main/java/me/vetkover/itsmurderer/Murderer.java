package me.vetkover.itsmurderer;

import me.vetkover.itsmurderer.commands.clearmurder;
import me.vetkover.itsmurderer.commands.setmurder;
import me.vetkover.itsmurderer.events.Events;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import static me.vetkover.itsmurderer.stuff.checkingFiles.checkDirectory;
import static me.vetkover.itsmurderer.stuff.checkingFiles.checkFiles;

public final class Murderer extends JavaPlugin {
    ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

    @Override
    public void onEnable() {
        checkDirectory();
        checkFiles();

        scoreboardManager = Bukkit.getScoreboardManager();

        try {
            Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
            scoreboard.registerNewTeam("sus").setPrefix("[SUS] ");
            scoreboard.registerNewTeam("murder").setPrefix("[MURDER] ");
        } catch (IllegalArgumentException e){
        }

        getCommand("setmurder").setExecutor(new setmurder());
        getCommand("clearmurder").setExecutor(new clearmurder());
        Bukkit.getPluginManager().registerEvents(new Events(), this);
    }
    @Override
    public void onDisable() {
        try {
            Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
            Team team1 = scoreboard.getTeam("sus");
            if (team1 != null) {
                team1.unregister();
            }
            Team team2 = scoreboard.getTeam("murder");
            if (team2 != null) {
                team2.unregister();
            }
        } catch (IllegalArgumentException e){}
    }

}
