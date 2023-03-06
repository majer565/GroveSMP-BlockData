package com.filipmajewski.grovesmpblockdata.listeners;

import com.filipmajewski.grovesmpblockdata.utils.BlockDataUtils;
import com.filipmajewski.grovesmpblockdata.utils.Database;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private Database database;

    public BlockPlaceListener(Database database) {
        this.database = database;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        BlockDataUtils data = new BlockDataUtils(
                event.getPlayer(),
                block.getType(),
                block.getX(),
                block.getY(),
                block.getZ()
        );

        if(!data.saveBlockEvent(BlockDataUtils.BlockEvent.PLACE, database)) {
            System.out.println(ChatColor.RED + "[BlockData] Error. Cannot add block data to database in BlockBreakListener");
        }
    }

}
