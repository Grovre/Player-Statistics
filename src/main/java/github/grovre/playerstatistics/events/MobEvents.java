package github.grovre.playerstatistics.events;

import github.grovre.playerstatistics.statistics.Tracked;
import github.grovre.playerstatistics.statistics.TrackedEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.UUID;

public class MobEvents implements Listener, TrackedEvent {

    @EventHandler
    public void OnMobSlayEvent(EntityDeathEvent event) {
        Player player = event.getEntity().getKiller();
        if(player == null) return;
        UUID uuid = player.getUniqueId();
        this.add(Tracked.KILLED_MOBS, uuid, 1);
    }
}
