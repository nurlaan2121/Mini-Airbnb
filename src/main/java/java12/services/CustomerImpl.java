package java12.services;

import java12.FamilyStatus;
import java12.Gender;
import java12.daos.daosImpl.AgencyDaoImpl;
import java12.daos.daosImpl.CustomerDaoImpl;
import java12.daos.daosImpl.HouseDaoImpl;
import java12.daos.daosImpl.RentInfoDaoImpl;
import java12.entities.Customer;
import java12.entities.House;
import java12.entities.RentInfo;
import java12.services.interfaces.CustomerInterface;
import org.hibernate.boot.archive.scan.spi.ScanEnvironment;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CustomerImpl implements CustomerInterface {
    CustomerDaoImpl customerDao = new CustomerDaoImpl();
    AgencyDaoImpl agencyDao = new AgencyDaoImpl();
    HouseDaoImpl houseDao = new HouseDaoImpl();
    RentInfoDaoImpl rentInfoDao = new RentInfoDaoImpl();

    @Override
    public String save(Customer newCustomer) {
        newCustomer.setRentInfo(new ArrayList<>());
        System.out.println("Write firstName");
        newCustomer.setFirstName(new Scanner(System.in).nextLine());
        System.out.println("Write lastName");
        newCustomer.setLastName(new Scanner(System.in).nextLine());
        System.out.println("Write email");
        newCustomer.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Write national");
        newCustomer.setNational(new Scanner(System.in).nextLine());
        System.out.println("Write gender");
        newCustomer.setGender(Gender.FEMALE);
        System.out.println("Write female status");
        newCustomer.setFamilyStatus(FamilyStatus.SINGLE);
        newCustomer.setDateOfBirth(Date.valueOf(LocalDate.of(2000, 1, 4)));

        return customerDao.save(newCustomer);
    }


    @Override
    public String save(Customer newCustomer, Long idHome) {
        System.out.println("Write agency id:  ");
        Long agencyId=new Scanner(System.in).nextLong();

        House byId = houseDao.findById(idHome);
        RentInfo rentInfo = byId.getRentInfo();
        System.out.println(rentInfo);
        Long id = rentInfo.getId();



        System.out.println("Write firstName");
        newCustomer.setFirstName(new Scanner(System.in).nextLine());
        System.out.println("Write lastName");
        newCustomer.setLastName(new Scanner(System.in).nextLine());
        System.out.println("Write email");
        newCustomer.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Write national");
        newCustomer.setNational(new Scanner(System.in).nextLine());
        System.out.println("Write gender");
        newCustomer.setGender(Gender.FEMALE);
        System.out.println("Write female status");
        newCustomer.setFamilyStatus(FamilyStatus.SINGLE);
        newCustomer.setDateOfBirth(Date.valueOf(LocalDate.of(2000, 1, 4)));
        newCustomer.setRentInfo(new ArrayList<>());

        rentInfo.setCustomers(newCustomer);
        newCustomer.getRentInfo().add(rentInfo);


        rentInfo.setCheckIn(Date.valueOf(LocalDate.of(2000, 4, 5)));
        rentInfo.setCheckOut(Date.valueOf(LocalDate.of(2006, 4, 5)));

        customerDao.save(newCustomer);
        rentInfoDao.update(rentInfo, id);


        agencyDao.assignAgencyToRentInfo(agencyId,rentInfo.getId());
        return "Success";
    }

    @Override
    public Customer findById(Long cusId) {
        return customerDao.findById(cusId);
    }

    @Override
    public String deleteById(Long cusId) {
        return customerDao.deleteById(cusId);
    }

    @Override
    public String updateByID(Long id, Customer newCustomer) {
        Customer byId = findById(id);

        newCustomer.setRentInfo(byId.getRentInfo());
        newCustomer.setEmail(byId.getEmail());
        newCustomer.setId(byId.getId());
        newCustomer.setGender(byId.getGender());
        newCustomer.setFamilyStatus(byId.getFamilyStatus());
        newCustomer.setDateOfBirth(byId.getDateOfBirth());
        newCustomer.setFirstName(byId.getFirstName());
        newCustomer.setLastName(byId.getLastName());
        newCustomer.setNational(byId.getNational());
        System.out.println("""
                Choose command:
                1.Email
                2.Gender
                3.Family status
                4.Date od birh
                5.First name
                6.LAst name
                7.National
                              
                """);
        int action = new Scanner(System.in).nextInt();
        switch (action) {
            case 1 -> {
                System.out.println("Write email");
                newCustomer.setEmail(new Scanner(System.in).nextLine());
            }
            case 2 -> {
                System.out.println("Write gendr");
                newCustomer.setGender(Gender.FEMALE);
            }
            case 3 -> {
                System.out.println("Write family status");
                newCustomer.setFamilyStatus(FamilyStatus.MARRIED);
            }
            case 4 -> {
                System.out.println("Write date");
                newCustomer.setDateOfBirth(Date.valueOf(LocalDate.now()));
            }
            case 5 -> {
                System.out.println("Write firt name");
                newCustomer.setFirstName(new Scanner(System.in).nextLine());
            }
            case 6 -> {
                System.out.println("Write last name");
                newCustomer.setLastName(new Scanner(System.in).nextLine());
            }
            case 7 -> {
                System.out.println("Write national");
                newCustomer.setNational(new Scanner(System.in).nextLine());
            }
        }
        return customerDao.updateByID(id, newCustomer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerDao.getAllCustomer();
    }
}
