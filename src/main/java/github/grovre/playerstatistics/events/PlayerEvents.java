package github.grovre.playerstatistics.events;

import github.grovre.playerstatistics.statistics.Tracked;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.util.UUID;

public class PlayerEvents implements Listener, TrackedEvent {

    @EventHandler
    public void OnPlayerJoinEvent(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        this.add(Tracked.SERVER_JOINS, uuid, 1);
    }

    @EventHandler
    public void OnPlayerBedtimeEvent(PlayerBedEnterEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        this.add(Tracked.BEDTIME, uuid, 1);
    }

    @EventHandler
    public void OnPlayerKickEvent(PlayerKickEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        this.add(Tracked.KICKS, uuid, 1);
    }

    @EventHandler
    public void OnPlayerSendCommandEvent(PlayerCommandPreprocessEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        this.add(Tracked.COMMANDS_SENT, uuid, 1);
    }

    @EventHandler
    public void OnPlayerDropItemEvent(PlayerDropItemEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        int amount = event.getItemDrop().getItemStack().getAmount();
        this.add(Tracked.DROPPED_ITEMS, uuid, amount);
    }

    @EventHandler
    public void OnPlayerGainExpEvent(PlayerExpChangeEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        int amount = event.getAmount();
        if(amount <= 0) return;
        this.add(Tracked.EXP_GAINED, uuid, amount);
    }

    @EventHandler
    public void OnPlayerThrowEggEvent(PlayerEggThrowEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        this.add(Tracked.THROWN_EGGS, uuid, 1);
    }

    @EventHandler
    public void OnPlayerCatchFishEvent(PlayerFishEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        Entity caught = event.getCaught();
        if(!(caught instanceof Fish)) return;
        this.add(Tracked.CAUGHT_FISH, uuid, 1);
    }

    @EventHandler
    public void OnPlayerMessageEvent(AsyncPlayerChatEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        this.add(Tracked.MESSAGES_SENT, uuid, 1);
        int charCount = event.getMessage().length();
        this.add(Tracked.CHARACTERS_TYPED, uuid, charCount);
    }
}
