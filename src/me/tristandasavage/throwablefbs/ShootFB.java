package me.tristandasavage.throwablefbs;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ShootFB implements Listener {
    public static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Throwable-Fireballs");
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.hasItem()) {
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (event.getItem().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "fireball"), PersistentDataType.INTEGER)) {
                    if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        event.setCancelled(true);
                    }
                    Fireball fb = event.getPlayer().launchProjectile(Fireball.class);
                    fb.setYield(plugin.getConfig().getInt("explosionpower"));
                    if (event.getPlayer().getGameMode() == GameMode.ADVENTURE || event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                        event.getItem().setAmount(event.getItem().getAmount() - 1);
                    }
                }
            }
        }
    }
}
