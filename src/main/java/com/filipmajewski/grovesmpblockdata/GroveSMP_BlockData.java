package com.filipmajewski.grovesmpblockdata;

import com.filipmajewski.grovesmpblockdata.events.BlockBreakListener;
import com.filipmajewski.grovesmpblockdata.events.BlockPlaceListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class GroveSMP_BlockData extends JavaPlugin {

    public static GroveSMP_BlockData plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
