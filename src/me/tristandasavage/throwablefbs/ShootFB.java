package me.tristandasavage.throwablefbs;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_17_R1.metadata.EntityMetadataStore;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

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
                    fb.setShooter(event.getPlayer());
                    if (event.getPlayer().getGameMode() == GameMode.ADVENTURE || event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                        event.getItem().setAmount(event.getItem().getAmount() - 1);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getEntityType() == EntityType.PLAYER) {
            if(event.getDamager().getType() == EntityType.FIREBALL && event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                if(((Fireball) event.getDamager()).getShooter() == event.getEntity()) {
                    event.setDamage(6.5);
                }
            }
        }
        if(plugin.getConfig().getBoolean("customkb")) {
            if (!event.getEntity().isDead()) {
                Vector velocity = event.getDamager().getLocation().toVector().subtract(event.getEntity().getLocation().getDirection()).multiply(plugin.getConfig().getInt("knockback"));
                event.getEntity().setVelocity(velocity.normalize().add(event.getEntity().getVelocity()).normalize());
            }
        }
    }
}
