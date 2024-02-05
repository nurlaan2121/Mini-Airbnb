package java12.daos.daosImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.configs.HibernateConfig;
import java12.daos.AddressDaoInterface;
import java12.entities.Address;
import java12.entities.Agency;
import java12.exceptions.Notfound;

import java.util.Collections;
import java.util.List;

public class AddressDaoImpl implements AddressDaoInterface {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public Address findById(Long addressId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, addressId);
            entityManager.getTransaction().commit();
            return address;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        throw new Notfound();
    }

    @Override
    public String updateByID(Long id, Address newAddress) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Address byId = findById(id);

            byId.setAgency(newAddress.getAgency());
            byId.setCity(newAddress.getCity());
            byId.setHouse(newAddress.getHouse());
            byId.setStreet(newAddress.getStreet());
            byId.setRegion(newAddress.getRegion());


            entityManager.merge(byId);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public List<Address> getAllAddressesForCheck() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Address> selectAdFromAddressAd = entityManager.createQuery("select ad from Address ad", Address.class).getResultList();
            entityManager.getTransaction().commit();
            return selectAdFromAddressAd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
