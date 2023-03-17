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
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CheckBlockDataListener implements Listener {

    private final Database database;

    public CheckBlockDataListener(Database database) {
        this.database = database;
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getHand() == EquipmentSlot.HAND) {
            Player p = event.getPlayer();
            ItemStack itemStack = p.getInventory().getItemInMainHand();
            if (itemStack.getType() == Material.STICK) {
                Block block = event.getClickedBlock();
                List<BlockData> blockData = database.getBlockData(block.getX(), block.getY(), block.getZ(), 100);
                Collections.reverse(blockData);
                if(!blockData.isEmpty()) {
                    p.sendMessage(
                            ChatColor.DARK_GRAY + "-=-=-=-=-=-=-=-=-=-=-" +
                                    ChatColor.YELLOW + "BlockData" +
                                    ChatColor.DARK_GRAY + "-=-=-=-=-=-=-=-=-=-=-" +
                                    ChatColor.RESET
                    );
                    blockData.forEach(data -> {
                        p.sendMessage(getBlockDataMessage(data));
                    });
                }
            }
        }
    }

    private String getBlockDataMessage(BlockData blockData) {
        Player p = Bukkit.getPlayer(UUID.fromString(blockData.getUuid()));
        BlockDataUtils.BlockEvent event = BlockDataUtils.BlockEvent.valueOf(blockData.getAction());
        if (event == BlockDataUtils.BlockEvent.BREAK) {
            return ChatColor.DARK_GRAY + "[" +
                    ChatColor.DARK_AQUA + blockData.getDate() +
                    ChatColor.DARK_GRAY + "]: " +
                    ChatColor.DARK_AQUA + p.getDisplayName() +
                    ChatColor.YELLOW + " broke " +
                    ChatColor.DARK_AQUA + blockData.getType();
        } else if (event == BlockDataUtils.BlockEvent.PLACE) {
            return ChatColor.DARK_GRAY + "[" +
                    ChatColor.DARK_AQUA + blockData.getDate() +
                    ChatColor.DARK_GRAY + "]: " +
                    ChatColor.DARK_AQUA + p.getDisplayName() +
                    ChatColor.YELLOW + " placed " +
                    ChatColor.DARK_AQUA + blockData.getType();
        }
        return "";
    }

}
