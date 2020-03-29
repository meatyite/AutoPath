package me.sl4v.autopath;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AutoPathCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            /*if (command.getName().equals("setAutoPathMaterials"))
            {

            }
             */
            if (!player.isOp())
            {
                AutoPathUtils.sendErrorMessage(player, "OP required for this command.");
                return true;
            }
            if (command.getName().equals("AutoPath"))
            {
                if (!AutoPathUtils.enabledForPlayer.getOrDefault(player.getDisplayName(), false))
                {
                    AutoPathUtils.enabledForPlayer.put(player.getDisplayName(), true);
                    player.sendMessage("AutoPath Enabled");
                }
                else
                {
                    AutoPathUtils.enabledForPlayer.put(player.getDisplayName(), false);
                    player.sendMessage("AutoPath Disabled");
                }
            } else if (command.getName().equals("AutoPathMaterial"))
            {
                if (args.length < 3)
                {
                    AutoPathUtils.sendErrorMessage(player, "3 materials required for this command.");
                } else
                {
                    Material[] materials = new Material[3];
                    for (int i = 0; i<=2; i++)
                    {
                        try
                        {
                            materials[i] = Material.getMaterial(args[i]);
                            if (!materials[i].isBlock())
                            {
                                player.sendMessage(ChatColor.RED + args[i] + " ERROR");
                                player.sendMessage("This material isn't a placeable block.");
                                return true;
                            }
                            player.sendMessage(ChatColor.GREEN + args[i] + " OK");
                        } catch (Exception e)
                        {
                            player.sendMessage(ChatColor.RED + args[i] + " ERROR");
                            player.sendMessage("Are you sure this is a valid block?");
                            return true;
                        }
                        AutoPathUtils.pathMaterialForPlayer.put(player.getDisplayName(), materials);
                    }
                }
            }
        }
        return true;
    }
}
