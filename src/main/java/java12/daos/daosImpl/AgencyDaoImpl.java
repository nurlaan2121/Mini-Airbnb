package java12.daos.daosImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.configs.HibernateConfig;
import java12.daos.AgencyDaoInterface;
import java12.entities.Agency;
import java12.entities.Customer;
import java12.entities.Owner;
import java12.entities.RentInfo;
import java12.exceptions.Notfound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AgencyDaoImpl implements AgencyDaoInterface {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String save(Agency newAgency) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newAgency);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error";
        } finally {
            entityManager.close();
        }
        return "Success";
    }

    @Override
    public Agency findById(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, agencyId);
            entityManager.getTransaction().commit();
            return agency;
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        throw new Notfound();
    }

    @Override
    public String deleteById(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency byId = findById(agencyId);
            entityManager.remove(byId);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        throw new Notfound();
    }

    @Override
    public String updateByID(Long id, Agency newAgency) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency byId = findById(id);
            byId.setAddress(newAgency.getAddress());
            byId.setName(newAgency.getName());
            byId.setOwners(newAgency.getOwners());
            byId.setPhoneNumber(newAgency.getPhoneNumber());
            byId.setRentInfoList(newAgency.getRentInfoList());
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
    public List<Agency> getAllAgencies() {
        List<Agency> agencies = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            agencies = entityManager.createQuery("select c from Agency c", Agency.class).getResultList();
            entityManager.getTransaction().commit();
            return agencies;
        } catch (Exception e) {
            e.getMessage();
        }
        return Collections.emptyList();
    }

    @Override
    public String assignOwnerToAgency(Long agencyId, Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, ownerId);
            Agency agency = findById(agencyId);
            if (owner != null && agency != null) {
                owner.getAgencies().add(agency);
                agency.getOwners().add(owner);
                entityManager.merge(owner);
                entityManager.merge(agency);
                entityManager.getTransaction().commit();
                return "Success";
            } else {
                return "Not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }

    @Override
    public Long getCountAgencyInCity(String city) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            return entityManager.createQuery("select count(*) from Agency a join Address ad on a.address.id = ad.id where ad.city =:city", Long.class).setParameter("city", city).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public List<Owner> getAllOwnersbyAgencyId(Long agencyID) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Owner> agencyId = entityManager.createQuery("select o from Owner o where o.id = :agencyId", Owner.class).setParameter("agencyId", agencyID).getResultList();
            entityManager.getTransaction().commit();
            return agencyId;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return Collections.emptyList();
    }

    @Override
    public String assignAgencyToRentInfo(Long idAgency, Long renInfoId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, idAgency);
            RentInfo rentInfo = entityManager.find(RentInfo.class, renInfoId);
            if (rentInfo != null && agency != null) {
                agency.getRentInfoList().add(rentInfo);
                rentInfo.setAgency(agency);
                entityManager.merge(rentInfo);
                entityManager.merge(agency);
                entityManager.getTransaction().commit();
                return "Success";
            } else {
                System.out.println("Not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }


}
