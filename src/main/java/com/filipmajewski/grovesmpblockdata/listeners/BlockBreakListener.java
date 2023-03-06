package com.filipmajewski.grovesmpblockdata.listeners;

import com.filipmajewski.grovesmpblockdata.utils.BlockDataUtils;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        BlockDataUtils data = new BlockDataUtils(
                event.getPlayer(),
                block.getType(),
                block.getX(),
                block.getY(),
                block.getZ()
        );

        data.saveBlockEvent(BlockDataUtils.BlockEvent.BREAK);
    }

}
