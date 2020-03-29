package me.sl4v.autopath;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoPathUtils
{
    public static Map<String, Boolean> enabledForPlayer =  new HashMap<String, Boolean>();

    public enum Yaw {
        NORTH, SOUTH, EAST, WEST;
    }
    public static Yaw get(Location loc)
    {
        float yaw = loc.getYaw();
        if (yaw < 0) {
            yaw += 360;
        }
        if (yaw >= 315 || yaw < 45) {
            return Yaw.SOUTH;
        } else if (yaw < 135) {
            return Yaw.WEST;
        } else if (yaw < 225) {
            return Yaw.NORTH;
        } else if (yaw < 315) {
            return Yaw.EAST;
        }
        return Yaw.NORTH;
    }

    public static void sendErrorMessage(Player player, String message)
    {
        player.sendMessage(ChatColor.RED + "Error" + ChatColor.WHITE + ": " + ChatColor.RED + message);
    }

    public static Map<String, Material[]> pathMaterialForPlayer = new HashMap<String, Material[]>();

    public static Block[] getBlocksBeneathPlayer(Location loc)
    {
        if (get(loc) == Yaw.NORTH || get(loc) == Yaw.SOUTH)
        {
            Location[] locs = new Location[]{
                    loc.clone().subtract(0, 1, 0),
                    loc.clone().subtract(1,1, 0),
                    loc.clone().subtract(-1, 1, 0)
            };
            return new Block[]
                    {
                            locs[0].getBlock(),
                            locs[1].getBlock(),
                            locs[2].getBlock()
                    };
        }
        else if (get(loc) == Yaw.EAST || get(loc) == Yaw.WEST)
        {
            Location[] locs = new Location[]{
                    loc.clone().subtract(0, 1, 0),
                    loc.clone().subtract(0,1, 1),
                    loc.clone().subtract(0, 1, -1)
            };
            return new Block[]
                    {
                            locs[0].getBlock(),
                            locs[1].getBlock(),
                            locs[2].getBlock()
                    };
        }
        return null;
    }

    public static List<String> getMaterialTypes()
    {
        List<String> materialNames = new ArrayList<String>();

        for (Material material : Material.values())
        {
            materialNames.add(material.name());
        }
        return materialNames;
    }

    public static List<String> filterListStartingWith(List<String> list, String startsWith)
    {
        List<String> filteredList = new ArrayList<String>();

        for (String unfilteredString : list)
        {
            if (unfilteredString.startsWith(startsWith))
            {
                filteredList.add(unfilteredString);
            }
        }
        return filteredList;
    }
}
