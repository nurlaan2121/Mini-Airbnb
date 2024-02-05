package java12;

import java12.entities.*;
import java12.services.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AgencyImpl agency = new AgencyImpl();
        HouseImpl house = new HouseImpl();
        AddressImpl address = new AddressImpl();
        CustomerImpl customer = new CustomerImpl();
        OwnerImpl owner = new OwnerImpl();
        RentInfoImpl rentInfo = new RentInfoImpl();
        while (true) {
            System.out.println("""
                              AGENCY
                    1. save(Agency newAgency)
                    2. findById(Long agencyId)
                    3. deleteById(Long agencyId)
                    4. updateByID(Long id, Agency newAgency)
                    5. List<Agency> getAllAgencies()
                    ___________________________________________
                               HOUSE
                    6. save(House newHouse)
                    7. findById(Long houseId)
                    8. deleteById(Long houseId)
                    9. updateByID(Long id, House newHouse)
                    10. List<House> getAllCustomer()
                    _____________________________________
                               Customer
                    11. save(Customer newCustomer)
                    12. save(Customer newCustomer, Long idRenInfoForBron)
                    13. findById(Long cusId)
                    14. deleteById(Long cusId)
                    15. updateByID(Long id, Customer newCustomer)
                    16. List<Customer> getAllCustomer()
                    ______________________________________
                               Owner
                    17. save(Owner newOwner)
                    18. save(Owner newCustomer, House newHouse)
                    19. findById(Long ownerId)
                    20. deleteById(Long ownerId)
                    21. updateByID(Long id, Owner newOwner)
                    22. List<Owner> getAllCustomer()
                    __________________________________
                                     RentInfo
                    23. List<RentInfo> getRentBetweenDate(Date checkIn, Date checkOut)
                    24. getCountRent(Long agencyId)
                    ____________________________________
                                     ADDRESS
                    25. findById(Long addressId)
                    26. updateByID(Long id, Address newAddress)
                                     ASSIGN
                    27. Assign owner to agency
                                       MORE
                    28. get Address and Agency
                    29. get count agencies in one city
                    30. Map<String, List<Agency>> groupByRegion
                    31. Get all Owners by AgencyId
                    32. getAllOwner name and age
                    33. Get all houses in one agency
                    34. Assign agency to rent info
                    35. Get all houses in one owner
                    36. Get all houses in checkIn between checkOut date
                    """);

            int action = new Scanner(System.in).nextInt();
            switch (action) {
                case 1 -> {
                    Address address1 = new Address();
                    Agency agency1 = new Agency();
                    address1.setAgency(agency1);
                    agency1.setAddress(address1);
                    System.out.println(agency.save(agency1));
                }
                case 2 -> {
                    System.out.println("Write id");
                    Long id = new Scanner(System.in).nextLong();
                    if (id < 0) {
                        System.out.println("KOICHU");
                    } else {
                        System.out.println(agency.findById(id));
                    }
                }
                case 3 -> {
                    System.out.println("Write id");
                    System.out.println(agency.deleteById(new Scanner(System.in).nextLong()));
                }
                case 4 -> {
                    System.out.println("Write id");
                    Long id = new Scanner(System.in).nextLong();
                    Agency agency1 = new Agency();
                    System.out.println(agency.updateByID(id, agency1));
                }
                case 5 -> {
                    System.out.println(agency.getAllAgencies());
                }
                case 6 -> {
                    Address address1 = new Address();
                    House house1 = new House();
                    address1.setHouse(house1);
                    house1.setAddress(address1);
                    System.out.println(house.save(house1));
                }
                case 7 -> {
                    System.out.println(house.findById(new Scanner(System.in).nextLong()));
                }
                case 8 -> {
                    System.out.println(house.deleteById(new Scanner(System.in).nextLong()));
                }
                case 9 -> {
                    System.out.println("Write id house");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(house.updateByID(id, new House()));
                }
                case 10 -> {
                    System.out.println(house.getAllCustomer());
                }
                case 11 -> {
                    System.out.println(customer.save(new Customer()));
                }
                case 12 -> {
                    System.out.println("Write house id");
                    System.out.println(customer.save(new Customer(), new Scanner(System.in).nextLong()));
                }
                case 13 -> {
                    System.out.println("Wirte id");
                    System.out.println(customer.findById(new Scanner(System.in).nextLong()));
                }
                case 14 -> {
                    System.out.println("Write id");
                    System.out.println(customer.deleteById(new Scanner(System.in).nextLong()));
                }
                case 15 -> {
                    System.out.println("Write id");
                    System.out.println(customer.updateByID(new Scanner(System.in).nextLong(), new Customer()));
                }
                case 16 -> {
                    System.out.println(customer.getAllCustomer());
                }
                case 17 -> {
                    System.out.println(owner.save(new Owner()));
                }
                case 18 -> {
                    Address address1 = new Address();
                    House house1 = new House();
                    address1.setHouse(house1);
                    house1.setAddress(address1);
                    System.out.println(owner.save(new Owner(), house1));
                }
                case 19 -> {
                    System.out.println("Write id");
                    System.out.println(owner.findById(new Scanner(System.in).nextLong()));
                }
                case 20 -> {
                    System.out.println("Write id");
                    System.out.println(owner.deleteById(new Scanner(System.in).nextLong()));
                }
                case 21 -> {
                    System.out.println(owner.updateByID(new Scanner(System.in).nextLong(), new Owner()));
                }
                case 22 -> {
                    System.out.println(owner.getAllOwners());
                }
                case 23 -> {
                    System.out.println(rentInfo.getRentBetweenDate(LocalDate.of(1800, 4, 4), LocalDate.of(2022, 4, 4)));

                }
                case 24 -> {
                    System.out.println("Write id agency");
                    System.out.println(rentInfo.getCountRent(new Scanner(System.in).nextLong()));

                }
                case 25 -> {
                    System.out.println("Write id");
                    System.out.println(address.findById(new Scanner(System.in).nextLong()));

                }
                case 26 -> {
                    System.out.println("Write id");
                    Long id = new Scanner(System.in).nextLong();
                    Address address1 = new Address();
                    System.out.println(address.updateByID(id, address1));
                }
                case 27 -> {
                    System.out.println("Write (Long agencyId, Long ownerId)");
                    System.out.println(agency.assignOwnerTOAgency(new Scanner(System.in).nextLong(), new Scanner(System.in).nextLong()));
                }
                case 28 -> {
                    System.out.println(agency.getAddressAndAgency());
                }
                case 29 -> {
                    System.out.println("Write city for total count agency containst");
                    System.out.println(agency.getTotalCountAgencyInCity(new Scanner(System.in).nextLine()));
                }
                case 30 -> {
                    System.out.println(agency.groupByRegion());
                }
                case 31 -> {
                    System.out.println(agency.getAllOwnersbyAgencyId(new Scanner(System.in).nextLong()));
                }
                case 32 -> {
                    System.out.println(owner.getAllOwnersNameAndAge());
                }
                case 33 -> {
                    System.out.println("Write id agency");
                    System.out.println(house.getAllhouseseinOneAgency(new Scanner(System.in).nextLong()));
                }
                case 34 -> {
                    System.out.println("Write id agency");
                    Long idAgency = new Scanner(System.in).nextLong();
                    System.out.println("Write id retInfo");
                    System.out.println(agency.assignAgencyToRentInfo(idAgency, new Scanner(System.in).nextLong()));
                }
                case 35 -> {
                    System.out.println("Write id owner");
                    System.out.println(house.getAllhousesinOneOwner(new Scanner(System.in).nextLong()));
                }
                case 36 -> {
                    System.out.println("Write check date: ");
                    LocalDate checkIn = LocalDate.of(1800, 4, 4);
                    LocalDate checkOut = LocalDate.now();
                    System.out.println(house.getAllHousesBetween(Date.valueOf(checkIn), java.sql.Date.valueOf(checkOut)));
                }
            }

        }
    }
}
