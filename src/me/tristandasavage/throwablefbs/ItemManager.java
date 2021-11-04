package me.tristandasavage.throwablefbs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Throwable-Fireballs");

    public static ItemStack fireball;

    public static void init() {
        createFireBall();
    }

    public static void createFireBall() {
        ItemStack fb = new ItemStack(Material.FIRE_CHARGE);
        ItemMeta meta = fb.getItemMeta();
        NamespacedKey key = new NamespacedKey(plugin, "fireball");

        meta.setDisplayName(ChatColor.RED + "Fireball");

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "Throwable fire charge!");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
        fb.setItemMeta(meta);

        fireball = fb;

        ShapedRecipe recipe = new ShapedRecipe(key, fb);
        recipe.shape(
                "FFF",
                "FEF",
                "FFF"
        );

        recipe.setIngredient('F', Material.FIRE_CHARGE);
        recipe.setIngredient('E', Material.EGG);

        Bukkit.addRecipe(recipe);
    }
}
