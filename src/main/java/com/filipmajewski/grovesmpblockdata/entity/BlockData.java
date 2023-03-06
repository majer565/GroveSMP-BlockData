package com.filipmajewski.grovesmpblockdata.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "block_data")
public class BlockData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "x_coord")
    private int x_coord;

    @Column(name = "y_coord")
    private int y_coord;

    @Column(name = "z_coord")
    private int z_coord;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    private String date;

    public BlockData(){}

    public BlockData(String uuid, int x_coord, int y_coord, int z_coord, String type, String date) {
        this.uuid = uuid;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.z_coord = z_coord;
        this.type = type;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public int getX_coord() {
        return x_coord;
    }

    public int getY_coord() {
        return y_coord;
    }

    public int getZ_coord() {
        return z_coord;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
