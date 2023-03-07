package com.filipmajewski.grovesmpblockdata.listeners;

import com.filipmajewski.grovesmpblockdata.entity.BlockData;
import com.filipmajewski.grovesmpblockdata.utils.BlockDataUtils;
import com.filipmajewski.grovesmpblockdata.utils.Database;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CheckBlockDataListener implements Listener {

    private final Database database;

    public CheckBlockDataListener(Database database) {
        this.database = database;
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack itemStack = p.getInventory().getItemInMainHand();
        if (itemStack.getType() == Material.STICK) {
            Block block = event.getClickedBlock();
            List<BlockData> blockData = database.getBlockData(block.getX(), block.getY(), block.getZ());
            blockData.forEach(data -> {
                p.sendMessage(getBlockDataMessage(data));
            });
        }
    }

    private String getBlockDataMessage(BlockData blockData) {
        Player p = Bukkit.getPlayer(blockData.getUuid());
        BlockDataUtils.BlockEvent event = BlockDataUtils.BlockEvent.valueOf(blockData.getAction());
        if (event == BlockDataUtils.BlockEvent.BREAK) {
            return ChatColor.DARK_GRAY + "[" +
                    ChatColor.RED + blockData.getDate() +
                    ChatColor.DARK_GRAY + "]: " +
                    ChatColor.RED + p.getDisplayName() +
                    ChatColor.YELLOW + " broke " +
                    ChatColor.RED + blockData.getType();
        } else if (event == BlockDataUtils.BlockEvent.PLACE) {
            return ChatColor.DARK_GRAY + "[" +
                    ChatColor.RED + blockData.getDate() +
                    ChatColor.DARK_GRAY + "]: " +
                    ChatColor.RED + p.getDisplayName() +
                    ChatColor.YELLOW + " placed " +
                    ChatColor.RED + blockData.getType();
        }
        return "";
    }

}
