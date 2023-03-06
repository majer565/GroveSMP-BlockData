package com.filipmajewski.grovesmpblockdata.events;

import com.filipmajewski.grovesmpblockdata.utils.BlockDataUtils;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

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

        data.saveBlockEvent(BlockDataUtils.BlockEvent.PLACE);
    }

}
