package me.tristandasavage.throwablefbs;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.logging.Level;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        ItemManager.init();
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new ShootFB(), this);
        this.getLogger().log(Level.INFO, "Throwable Fireballs v1.0 loaded!");
        this.getServer().getPluginCommand("uwu").setExecutor(new UWU());
    }

    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "Throwable Fireballs plugin unloaded");
    }
}
