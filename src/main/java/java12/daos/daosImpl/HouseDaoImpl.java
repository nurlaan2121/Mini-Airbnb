package java12.daos.daosImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.configs.HibernateConfig;
import java12.daos.HouseDaoInterface;
import java12.entities.*;
import java12.exceptions.Notfound;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class HouseDaoImpl implements HouseDaoInterface {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String save(House newHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            //1
            entityManager.merge(newHouse);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return "error";
    }

    @Override
    public House findById(Long houseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class, houseId);
            entityManager.getTransaction().commit();
            return house;
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        throw new Notfound();
    }

    @Override
    public String deleteById(Long houseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House byId = findById(houseId);
            if (byId.getRentInfo() == null) {
                entityManager.remove(byId);
                entityManager.getTransaction().commit();
                return "Success";
            } else {
                if (byId.getRentInfo().getCustomers() == null) {
                    entityManager.remove(byId);
                    entityManager.getTransaction().commit();
                    return "Success";
                } else {
                    LocalDate currentDate = LocalDate.now();
                    Date date = byId.getRentInfo().getCheckOut();
                    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (localDate.isBefore(currentDate)) {
                        entityManager.remove(byId);
                        entityManager.getTransaction().commit();
                        return "Success";
                    }
                }
            }

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return "ERROR";
    }

    @Override
    public String updateByID(Long id, House newHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House byId = findById(id);

            byId.setAddress(newHouse.getAddress());
            byId.setDescription(newHouse.getDescription());
            byId.setFurniture(newHouse.isFurniture());
            byId.setPrice(newHouse.getPrice());
            byId.setOwner(newHouse.getOwner());
            byId.setHouseType(newHouse.getHouseType());
            byId.setRating(newHouse.getRating());
            byId.setRoom(newHouse.getRoom());

            entityManager.merge(byId);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return "Error";
    }

    @Override
    public List<House> getAllCustomer() {
        List<House> houses = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select c from House c", House.class).getResultList();
            entityManager.getTransaction().commit();
            return houses;
        } catch (Exception e) {
            e.getMessage();
        }
        return Collections.emptyList();
    }

    @Override
    public List<House> getAllhouseseinOneAgency(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<House> agencyId1 = entityManager.createQuery("select h from House h join RentInfo r on h.rentInfo.id = r.id join Agency a on r.agency.id = a.id where a.id =:agencyId", House.class).setParameter("agencyId", agencyId).getResultList();
            entityManager.getTransaction().commit();
            return agencyId1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<House> getAllhousesinOneOwner(Long ownerID) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<House> ownerID1 = entityManager.createQuery("select h from House h join Owner o on h.owner.id = o.id where o.id =:ownerID", House.class).setParameter("ownerID", ownerID).getResultList();
            entityManager.getTransaction().commit();
            return ownerID1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<House> getHouseBetween(Date checkIn, Date checkOut) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<House> resultList = entityManager
                    .createQuery("select h from House h join RentInfo r on h.rentInfo.id = r.id WHERE r.checkIn BETWEEN :checkin AND :checkout AND r.checkOut BETWEEN :checkin AND :checkout", House.class)
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
}
