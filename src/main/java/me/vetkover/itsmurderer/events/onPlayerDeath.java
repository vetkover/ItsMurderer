package me.vetkover.itsmurderer.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import static me.vetkover.itsmurderer.stuff.JsonWork.writeJson;
import static me.vetkover.itsmurderer.stuff.YamlWork.readYaml;
public class onPlayerDeath implements Listener {

    public onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        Player victim = event.getEntity();
        Player killer = event.getEntity().getKiller();
        String victimNick = event.getEntity().getName();
        String killerNick = killer.getName();
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        Team team1 = scoreboard.getTeam("sus");
        Team team2 = scoreboard.getTeam("murder");

        Location deathLocation = victim.getLocation();
        World world = deathLocation.getWorld();
        Location spawnLocation = world.getSpawnLocation();
        Object spawnAreaProtectObj = readYaml("spawnAreaProtect");
        int spawnAreaProtect = ((Number) spawnAreaProtectObj).intValue();

        if (spawnAreaProtectObj instanceof Number) {

            if (deathLocation.distance(spawnLocation) <= spawnAreaProtect) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.sendMessage("§e WARNING §f" + victimNick + " was kill by " + killerNick + " around spawn at " +
                            "x=" + deathLocation.getBlockX() + ", " +
                            "y=" + deathLocation.getBlockY() + ", " +
                            "z=" + deathLocation.getBlockZ());
                }
            }
            writeJson(killerNick, (int) ((System.currentTimeMillis() / 1000) + ((Number) readYaml("punishMurderDuration")).intValue()), "MURDER");
            killer.setDisplayName("[MURDER] " + killerNick);
            killer.setPlayerListName("[MURDER] " + killerNick);
            team2.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
            team2.addEntry(killerNick);
            team2.setPrefix("[MURDER] ");
            killer.setScoreboard(scoreboard);
            event.setDeathMessage(null);
        }

        if (killer != null && !killer.getDisplayName().contains("[MURDER]") && deathLocation.distance(spawnLocation) > spawnAreaProtect) {
            writeJson(killerNick, (int) ((System.currentTimeMillis() / 1000) + ((Number) readYaml("punishSusDuration")).intValue()), "SUS");

            killer.setDisplayName("[SUS] " + killerNick);
            killer.setPlayerListName("[SUS] " + killerNick);

            team1.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
            team1.addEntry(killerNick);
            team1.setPrefix("[SUS] ");
            killer.setScoreboard(scoreboard);
        }
    }
}