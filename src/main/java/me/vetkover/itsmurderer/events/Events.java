package me.vetkover.itsmurderer.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {

    @EventHandler
    public void onPlayersDeath(PlayerDeathEvent event)
    {
        new onPlayerDeath(event);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        new onPlayerJoin(event);
    }
}
