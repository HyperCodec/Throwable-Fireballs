package me.tristandasavage.throwablefbs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UWU implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("uwu")) {
            Player plr = (Player) sender;
            plr.setInvulnerable(false);
            sender.sendMessage("fixed");
        }
        return true;
    }
}
