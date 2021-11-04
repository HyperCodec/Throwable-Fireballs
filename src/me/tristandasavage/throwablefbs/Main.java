package me.tristandasavage.throwablefbs;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.logging.Level;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        registerGlow();
        ItemManager.init();
        this.getServer().getPluginManager().registerEvents(new ShootFB(), this);
        this.getLogger().log(Level.INFO, "Throwable Fireballs v1.0 loaded!");
    }

    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "Throwable Fireballs plugin unloaded");
    }

    public void registerGlow() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            NamespacedKey key = new NamespacedKey(this, getDescription().getName());

            Glow glow = new Glow(key);
            Enchantment.registerEnchantment(glow);
        }
        catch (IllegalArgumentException e){
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
