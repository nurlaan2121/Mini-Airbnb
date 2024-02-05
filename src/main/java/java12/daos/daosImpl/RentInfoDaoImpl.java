package java12.daos.daosImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.configs.HibernateConfig;
import java12.daos.RentInfoDaoInterface;
import java12.entities.House;
import java12.entities.RentInfo;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RentInfoDaoImpl implements RentInfoDaoInterface {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public List<RentInfo> getRentBetweenDate(Date checkIn, Date checkOut) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<RentInfo> resultList = entityManager
                    .createQuery("SELECT r FROM RentInfo r WHERE r.checkIn BETWEEN :checkin AND :checkout AND r.checkOut BETWEEN :checkin AND :checkout", RentInfo.class)
                    .setParameter("checkin", checkIn)
                    .setParameter("checkout", checkOut)
                    .getResultList();
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
            Long count = entityManager.createQuery("select count(*) from RentInfo r join Agency a on r.agency.id = a.id where a.id = :id", Long.class).setParameter("id", agencyId).getSingleResult();
            entityManager.getTransaction().commit();
            return Math.toIntExact(count);
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return 0;
    }

    @Override
    public void update(RentInfo rentInfo,Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            RentInfo rentInfo1 = entityManager.find(RentInfo.class, id);
            //1
            rentInfo1.setCheckOut(rentInfo.getCheckOut());
            rentInfo1.setHouse(rentInfo.getHouse());
            rentInfo1.setOwner(rentInfo.getOwner());
            rentInfo1.setCheckIn(rentInfo.getCheckIn());
            rentInfo1.setCustomers(rentInfo.getCustomers());
            rentInfo1.setAgency(rentInfo.getAgency());
            //bilmeim nege ishtep atat birok 2 suctomer saltabai atat

            entityManager.merge(rentInfo);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }


}
