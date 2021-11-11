package me.tristandasavage.throwablefbs;

import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.logging.Level;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        ItemManager.init();
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new ShootFB(), this);
        this.getLogger().log(Level.INFO, "Throwable Fireballs v1.1 loaded!");
    }

    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "Throwable Fireballs plugin unloaded");
    }
}
