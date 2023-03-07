package com.filipmajewski.grovesmpblockdata.utils;

import com.filipmajewski.grovesmpblockdata.entity.BlockData;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BlockDataUtils {

    public enum BlockEvent {
        BREAK("BREAK"),
        PLACE("PLACE");

        private final String action;

        BlockEvent(String action) {
            this.action = action;
        }

        public String getAction() {
            return action;
        }
    }

    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
