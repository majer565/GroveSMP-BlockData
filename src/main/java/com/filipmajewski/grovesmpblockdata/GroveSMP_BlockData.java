package com.filipmajewski.grovesmpblockdata;

import com.filipmajewski.grovesmpblockdata.listeners.BlockBreakListener;
import com.filipmajewski.grovesmpblockdata.listeners.BlockPlaceListener;
import com.filipmajewski.grovesmpblockdata.listeners.CheckBlockDataListener;
import com.filipmajewski.grovesmpblockdata.utils.Database;
import org.bukkit.plugin.java.JavaPlugin;

public final class GroveSMP_BlockData extends JavaPlugin {

    public static GroveSMP_BlockData plugin;

    private Database database;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.database = new Database();

        getServer().getPluginManager().registerEvents(new BlockBreakListener(database), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(database), this);
        getServer().getPluginManager().registerEvents(new CheckBlockDataListener(database), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
