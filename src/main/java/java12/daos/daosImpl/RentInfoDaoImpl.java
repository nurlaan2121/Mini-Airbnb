package java12.daos.daosImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.configs.HibernateConfig;
import java12.daos.RentInfoDaoInterface;
import java12.entities.Customer;
import java12.entities.RentInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RentInfoImpl implements RentInfoDaoInterface {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public List<RentInfo> getRentBetweenDate(Date checkIn, Date checkOut) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<RentInfo> resultList = entityManager.createQuery("select r from RentInfo r where checkIn = :checkin and checkOut = :checkout", RentInfo.class).setParameter("checkin", checkIn).setParameter("checkout", checkOut).getResultList();
            entityManager.getTransaction().commit();

            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public Integer getCountRent(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Integer singleResult = entityManager.createQuery("select count(*) from RentInfo r join Agency a on r.agency.id = a.id", Integer.class).getSingleResult();
            entityManager.getTransaction().commit();
            return singleResult;
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return 0;
    }
}
