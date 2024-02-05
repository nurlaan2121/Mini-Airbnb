package java12.daos.daosImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.configs.HibernateConfig;
import java12.daos.OwnerDaoInterface;
import java12.entities.Customer;
import java12.entities.House;
import java12.entities.Owner;
import java12.exceptions.Notfound;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OwnerDaoImpl implements OwnerDaoInterface {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String save(Owner newOwner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newOwner);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        } finally {
            entityManager.close();
        }
        return "Success";
    }

    @Override
    public String save(Owner newOwner, House newHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
//            entityManager.persist(newOwner);
            entityManager.persist(newHouse);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return "Success";
    }

    @Override
    public Owner findById(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, ownerId);
            entityManager.getTransaction().commit();
            return owner;
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        throw new Notfound();
    }

    @Override
    public String deleteById(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner byId = findById(ownerId);
            System.out.println(byId.getRentInfoList().isEmpty());
            for (int i = 0; i < byId.getRentInfoList().size(); i++) {
                if (byId.getRentInfoList().get(i).getCustomers() == null) {
                    entityManager.remove(byId);
                    entityManager.getTransaction().commit();
                } else {
                    Date date = byId.getRentInfoList().get(i).getCheckOut();
                    LocalDate currentDate = LocalDate.now();
                    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (localDate.isBefore(currentDate)) {
                        entityManager.remove(byId);
                        entityManager.getTransaction().commit();
                    }
                }

            }
            if (byId.getRentInfoList().isEmpty()) {
                entityManager.remove(byId);
                entityManager.getTransaction().commit();
            }
            return "Success";
        } catch (
                Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return "ERROR";
    }

    @Override
    public String updateByID(Long id, Owner newowner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner byId = findById(id);

            byId.setAgencies(newowner.getAgencies());
            byId.setHouses(newowner.getHouses());
            byId.setGender(newowner.getGender());
            byId.setEmail(newowner.getEmail());
            byId.setFirstName(newowner.getFirstName());
            byId.setLastName(newowner.getLastName());
            byId.setDateOfBirth(newowner.getDateOfBirth());
            byId.setRentInfoList(newowner.getRentInfoList());

            entityManager.merge(byId);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Owner> getAllOwners() {
        List<Owner> owners = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            owners = entityManager.createQuery("select c from Owner c", Owner.class).getResultList();
            entityManager.getTransaction().commit();
            return owners;
        } catch (Exception e) {
            e.getMessage();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Owner> getAllOwnersNameAndAge() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Owner> resultList = entityManager.createQuery("select concat(o.firstName,'  ',o.lastName,'  ',age(o.dateOfBirth)) from Owner o", Owner.class).getResultList();
            entityManager.getTransaction().commit();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
