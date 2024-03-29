package com.filipmajewski.grovesmpblockdata.utils;

import com.filipmajewski.grovesmpblockdata.entity.BlockData;
import com.mysql.cj.QueryResult;
import jdk.swing.interop.SwingInterOpUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private final Configuration configuration;

    private final SessionFactory sessionFactory;

    public Database() {
        this.configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(BlockData.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public void closeSessionFactory() {
        sessionFactory.close();
    }

    public List<BlockData> getBlockData(int x, int y, int z) throws HibernateException {
        String getBlockDataQuery = "FROM BlockData WHERE x_coord=" + x + " AND y_coord=" + y + " AND z_coord=" + z;
        Session session = sessionFactory.openSession();
        Query<BlockData> query = session.createQuery(getBlockDataQuery, BlockData.class);
        List<BlockData> blockData = query.list();

        session.close();

        return blockData;
    }

    public List<BlockData> getBlockData(int x, int y, int z, int limit) throws HibernateException {
        String getBlockDataQuery = "FROM BlockData WHERE x_coord=" + x + " AND y_coord=" + y + " AND z_coord=" + z + "ORDER BY id DESC" ;
        Session session = sessionFactory.openSession();
        Query<BlockData> query = session.createQuery(getBlockDataQuery, BlockData.class).setMaxResults(limit);
        List<BlockData> blockData = query.list();

        session.close();

        return blockData;
    }

    public void addBlockData(String uuid, int x, int y, int z, String type, String date, String action) throws HibernateException{
        Session session = sessionFactory.openSession();
        BlockData blockData = new BlockData(uuid, x, y, z, type, date, action);

        session.beginTransaction();
        session.persist(blockData);
        session.getTransaction().commit();

        session.close();
    }

}
