package me.sl4v.autopath;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class AutoPathTabCompleter implements TabCompleter {
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
    {
        if (command.getName().equals("AutoPathMaterial"))
        {
            if (args.length < 4 && args.length != 0)
            {
                return AutoPathUtils.filterListStartingWith(AutoPathUtils.getMaterialTypes(), args[args.length -1]);
            }
        }
        return null;
    }
}
