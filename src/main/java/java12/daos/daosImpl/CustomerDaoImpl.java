package java12.daos.daosImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.configs.HibernateConfig;
import java12.daos.CustomerDaoInterface;
import java12.entities.Customer;
import java12.entities.RentInfo;
import java12.exceptions.Notfound;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CustomerDaoImpl implements CustomerDaoInterface {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();
    RentInfoDaoImpl rentInfoDao = new RentInfoDaoImpl();

    @Override
    public String save(Customer newCustomer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newCustomer);
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
    public String save(Customer newCustomer, Long idRentInfo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newCustomer);
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
    public Customer findById(Long cusId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, cusId);
            entityManager.getTransaction().commit();
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        throw new Notfound();
    }

    @Override
    public String deleteById(Long cusId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<RentInfo> resultList = entityManager.createQuery("select r from RentInfo r join Customer c on r.customers.id = c.id", RentInfo.class).getResultList();
            for (int i = 0; i < resultList.size(); i++) {
                if (resultList.get(i).getCustomers().getId().equals(cusId)) {
                    Date date = resultList.get(i).getCheckOut();

                    LocalDate currentDate = LocalDate.now();
                    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (localDate.isBefore(currentDate)) {
                        System.out.println(localDate);
                        Customer customer = entityManager.find(Customer.class, cusId);
                        entityManager.remove(customer);
                        entityManager.getTransaction().commit();
                    }
                }
            }
            Customer byId = findById(cusId);
            if (byId.getRentInfo().isEmpty()) {
                entityManager.remove(byId);
                entityManager.getTransaction().commit();
            }
//
            return "Success";
        } catch (
                Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return "Error";
    }

    @Override
    public String updateByID(Long id, Customer newCustomer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer byId = findById(id);
            byId.setGender(newCustomer.getGender());
            byId.setEmail(newCustomer.getEmail());
            byId.setDateOfBirth(newCustomer.getDateOfBirth());
            byId.setNational(newCustomer.getNational());
            byId.setFirstName(newCustomer.getFirstName());
            byId.setFamilyStatus(newCustomer.getFamilyStatus());
            byId.setLastName(newCustomer.getLastName());
            byId.setRentInfo(newCustomer.getRentInfo());
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
    public List<Customer> getAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            customers = entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
            entityManager.getTransaction().commit();
            return customers;
        } catch (Exception e) {
            e.getMessage();
        }
        return Collections.emptyList();
    }
//    CRUD
//    Customer эки жол менен тузулсун.
//    Биринчисинде озу эле тузулот, экинчисинде customer тузулуп жатканда бир
//    уйду ижарага алып тузулот(rent_info).
//    Customer уйду ижарага алса болот. Ижарага алып жатканда customer id, house
//    id, agency id жана check in check out жазышы керек.
//            Customer-ди очуруп жатканда. Ижарасы жок Customer-лер очсун, эгерде
//    ижарасы бар болсо checkout датасын текшерсин. Учурдагы датадан мурун
//    болсо rent_info customer-менен чогу очуп кетсин.

}
