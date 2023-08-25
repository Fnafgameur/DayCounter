package fr.fnafgameur.daycounter.commands;

import fr.fnafgameur.daycounter.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class DayCounterCommand implements CommandExecutor {

    private final Main main;

    public DayCounterCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cOnly players can execute this command !");
            return false;
        }

        if (args.length == 0) {
            player.sendMessage("§7---------- §bDayCounter's command §7----------");
            player.sendMessage("§6/daycounter days §7- §aGet the number of days in the world");
            if (!player.isOp()) {
                return false;
            }
            player.sendMessage("§6/daycounter setdisplay <actionbar|scoreboard> <true|false> §7- §aSet the display of the day counter");
            return false;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("days")) {
                String worldName = player.getWorld().getName();
                if (worldName.equalsIgnoreCase("world")) {
                    worldName = "Overworld";
                }
                else if (worldName.equalsIgnoreCase("world_nether")) {
                    worldName = "Nether";
                }
                else if (worldName.equalsIgnoreCase("world_the_end")) {
                    worldName = "End";
                }
                player.sendMessage("§aDay number : §6" + player.getWorld().getFullTime() / 24000 + " §aWorld : §6" + worldName);
                return false;
            }
        }

        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("setdisplay")) {
                if (!player.isOp()) {
                    player.sendMessage("§cYou don't have the permission to execute this command !");
                    return false;
                }
                if (args[1].equalsIgnoreCase("actionbar")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        main.getConfig().set("actionbar", true);
                        player.sendMessage("§aThe day counter is now displayed in the action bar !");
                    }
                    else if (args[2].equalsIgnoreCase("false")) {
                        main.getConfig().set("actionbar", false);
                        player.sendMessage("§aThe day counter is no longer displayed in the action bar !");
                    }
                    else {
                        player.sendMessage("§cThe third argument must be true or false !");
                    }
                } else if (args[1].equalsIgnoreCase("scoreboard")) {

                    if (args[2].equalsIgnoreCase("true")) {
                        main.getConfig().set("scoreboard", true);
                        player.getScoreboard().getObjective("daycounter").setDisplaySlot(DisplaySlot.SIDEBAR);
                        player.sendMessage("§aThe day counter is now displayed in the scoreboard !");
                    }
                    else if (args[2].equalsIgnoreCase("false")) {
                        main.getConfig().set("scoreboard", false);
                        player.getScoreboard().getObjective("daycounter").setDisplaySlot(null);
                        player.sendMessage("§aThe day counter is no longer displayed in the scoreboard !");
                    }
                    else {
                        player.sendMessage("§cThe third argument must be true or false !");
                    }
                }
            }
            main.saveConfig();
            return false;
        }
        player.sendMessage("§7---------- §bDayCounter's command §7----------");
        player.sendMessage("§6/daycounter days §7- §aGet the number of days in the world");
        if (!player.isOp()) {
            return false;
        }
        player.sendMessage("§6/daycounter setdisplay <actionbar|scoreboard> <true|false> §7- §aSet the display of the day counter");
        return false;
    }
}
