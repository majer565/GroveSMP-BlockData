package com.filipmajewski.grovesmpblockdata.listeners;

import com.filipmajewski.grovesmpblockdata.utils.BlockDataUtils;
import com.filipmajewski.grovesmpblockdata.utils.Database;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.hibernate.HibernateException;

public class BlockBreakListener implements Listener {

    private final Database database;

    public BlockBreakListener(Database database) {
        this.database = database;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        try {
            database.addBlockData(
                    event.getPlayer().getUniqueId().toString(),
                    block.getX(),
                    block.getY(),
                    block.getZ(),
                    block.getType().toString(),
                    BlockDataUtils.getCurrentDate(),
                    BlockDataUtils.BlockEvent.BREAK.getAction()
            );
        } catch (HibernateException e) {
            System.out.println(ChatColor.RED + "[BlockData] Error. Cannot add block data to database in BlockBreakListener" + ChatColor.RESET);
        }
    }

}
