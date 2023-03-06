package com.filipmajewski.grovesmpblockdata.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BlockDataUtils {

    public enum BlockEvent {
        BREAK, PLACE;
    }

    private final Player player;

    private final Material blockMaterial;

    private final int x_coords;

    private final int y_coords;

    private final int z_coords;

    private final String date;

    public BlockDataUtils(Player player, Material blockMaterial, int x_coords, int y_coords, int z_coords) {
        this.player = player;
        this.blockMaterial = blockMaterial;
        this.x_coords = x_coords;
        this.y_coords = y_coords;
        this.z_coords = z_coords;
        this.date = getCurrentDate();
    }

    public Player getPlayer() {
        return player;
    }

    public Material getBlockMaterial() {
        return blockMaterial;
    }

    public int getX_coords() {
        return x_coords;
    }

    public int getY_coords() {
        return y_coords;
    }

    public int getZ_coords() {
        return z_coords;
    }

    public String getDate() {
        return date;
    }

    public boolean saveBlockEvent(BlockEvent event) {
        try {
            switch (event) {
                case BREAK:
                    //Add JDBC configuration
                    break;
                case PLACE:
                    //Add JDBC configuration
                    break;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private String getBlockDataMessage(BlockEvent event) {
        if(event == BlockEvent.BREAK) {
            return getDate() + ": " + getPlayer().getDisplayName() + " broke " + getBlockMaterial().toString();
        } else if (event == BlockEvent.PLACE) {
            return getDate() + ": " + getPlayer().getDisplayName() + " placed " + getBlockMaterial().toString();
        }
        return "";
    }

    private String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return now.toString();
    }
}
