package me.sl4v.autopath;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Random;

public class AutoPath extends JavaPlugin implements Listener {
    @Override
    public void onEnable()
    {
        getLogger().info("AutoPath plugin enabled.");
        AutoPathCommand commandExecutor = new AutoPathCommand();
        getCommand("AutoPath").setExecutor(commandExecutor);
        getCommand("AutoPathMaterial").setExecutor(commandExecutor);
        getCommand("AutoPathMaterial").setTabCompleter(new AutoPathTabCompleter());
        getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
        if (!player.isOp()) { return ; }
        Material[] defaultMaterials = new Material[]
                {
                        Material.GRAVEL,
                        Material.COARSE_DIRT,
                        Material.COBBLESTONE
                };
        Material[] replaceWith = AutoPathUtils.pathMaterialForPlayer.getOrDefault(player.getDisplayName(), defaultMaterials);
        if (AutoPathUtils.enabledForPlayer.getOrDefault(player.getDisplayName(), false))
        {
            Random rand = new Random();
            Location to = event.getTo();
            Block[] blocksBeneathPlayer = AutoPathUtils.getBlocksBeneathPlayer(to);
            for (Block block: blocksBeneathPlayer)
            {
                if (block.getType().equals(Material.GRASS_BLOCK))
                {
                    block.setType(replaceWith[rand.nextInt(replaceWith.length)]);
                }
            }
        }
    }
}
