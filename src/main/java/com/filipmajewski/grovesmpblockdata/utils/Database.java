package com.filipmajewski.grovesmpblockdata.utils;

import com.filipmajewski.grovesmpblockdata.entity.BlockData;
import com.mysql.cj.QueryResult;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private final Configuration configuration;

    public Database() {
        this.configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
    }

    public List<BlockData> getBlockData(int x, int y, int z) throws HibernateException {
        String getBlockDataQuery = "FROM BlockData WHERE x_coord=" + x + " AND y_coord=" + y + " AND z_coord=" + z;
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query<BlockData> query = session.createQuery(getBlockDataQuery, BlockData.class);
        List<BlockData> blockData = query.list();

        session.close();
        sessionFactory.close();

        return blockData;
    }

    public void addBlockData(String uuid, int x, int y, int z, String type, String date, String action) throws HibernateException{
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        BlockData blockData = new BlockData(uuid, x, y, z, type, date, action);

        session.beginTransaction();
        session.persist(blockData);
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }

}
