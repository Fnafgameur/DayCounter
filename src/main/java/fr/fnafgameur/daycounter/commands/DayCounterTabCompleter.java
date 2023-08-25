package fr.fnafgameur.daycounter.commands;

import fr.fnafgameur.daycounter.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DayCounterTabCompleter implements TabCompleter {

    private final Main main;

    public DayCounterTabCompleter(Main main) {
        this.main = main;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        List<String> list = new ArrayList<>();
        List<String> completions = new ArrayList<>();
        int arg = 0;

        if (args.length == 1) {
            list.add("days");
            if (player.isOp()) {
                list.add("setdisplay");
            }
        }

        if (!player.isOp()) {
            StringUtil.copyPartialMatches(args[arg], list, completions);
            Collections.sort(completions);

            return completions;
        }

        if (args.length == 2) {
            arg = 1;
            list.add("actionbar");
            list.add("scoreboard");
        }

        if (args.length == 3) {
            arg = 2;
            if (args[1].equalsIgnoreCase("actionbar")) {
                list.add(!main.getConfig().getBoolean("actionbar") + "");
            }
            else if (args[1].equalsIgnoreCase("scoreboard")) {
                list.add(!main.getConfig().getBoolean("scoreboard") + "");
            }
        }

        if (args.length > 3) {
            return null;
        }

        StringUtil.copyPartialMatches(args[arg], list, completions);
        Collections.sort(completions);

        return completions;
    }
}
