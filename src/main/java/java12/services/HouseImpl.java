package java12.services;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java12.Gender;
import java12.HouseType;
import java12.daos.daosImpl.AddressDaoImpl;
import java12.daos.daosImpl.HouseDaoImpl;
import java12.daos.daosImpl.OwnerDaoImpl;
import java12.entities.Address;
import java12.entities.House;
import java12.entities.Owner;
import java12.entities.RentInfo;
import java12.exceptions.Notfound;
import java12.services.interfaces.HouseInterface;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HouseImpl implements HouseInterface {
    HouseDaoImpl houseDao = new HouseDaoImpl();
    AddressDaoImpl addressDao = new AddressDaoImpl();
    OwnerDaoImpl ownerDao = new OwnerDaoImpl();


    @Override
    public String save(House newHouse) {

        System.out.println("Write count room");
        newHouse.setRoom(new Scanner(System.in).nextInt());
        System.out.println("Write price: ");
        newHouse.setPrice(new Scanner(System.in).nextBigDecimal());
        System.out.println("Write rating");
        newHouse.setRating(new Scanner(System.in).nextDouble());
        System.out.println("Write 1||2");
        newHouse.setFurniture(true);
        System.out.println("Write description");
        newHouse.setDescription(new Scanner(System.in).nextLine());
        System.out.println("Write house type");
        newHouse.setHouseType(HouseType.APARTMENT);



        System.out.println("Write address region");
        newHouse.getAddress().setRegion(new Scanner(System.in).nextLine());
        System.out.println("Write address city");
        newHouse.getAddress().setCity(new Scanner(System.in).nextLine());
        System.out.println("Write address street");
        newHouse.getAddress().setStreet(new Scanner(System.in).nextLine());
        newHouse.getAddress().setHouse(newHouse);


        System.out.println("OWNER YES OR NO if YES (1)|| or NO (2)");
        int action = new Scanner(System.in).nextInt();
        switch (action) {
            case 1 -> {
                System.out.println("Write owner id");
                Long idOwner = new Scanner(System.in).nextLong();
                Owner foudOwner = ownerDao.findById(idOwner);
                foudOwner.getHouses().add(newHouse);
                newHouse.setOwner(foudOwner);
                RentInfo rentInfo = new RentInfo();
                rentInfo.setHouse(newHouse);
                newHouse.setRentInfo(rentInfo);
                foudOwner.getHouses().add(newHouse);
                foudOwner.getRentInfoList().add(rentInfo);

            }
            case 2 -> {
                Owner owner = new Owner();
                System.out.println("Write email ");
                owner.setEmail(new Scanner(System.in).nextLine());
                System.out.println("Write firstName ");
                owner.setFirstName(new Scanner(System.in).nextLine());
                System.out.println("Write last name ");
                owner.setLastName(new Scanner(System.in).nextLine());
                System.out.println("Write gender ");
                owner.setGender(Gender.MALE);
                owner.setDateOfBirth(Date.valueOf(LocalDate.of(2000, 1, 1)));
                owner.setHouses(new ArrayList<>());
                owner.getHouses().add(newHouse);
                owner.setAgencies(new ArrayList<>());
                owner.setRentInfoList(new ArrayList<>());
                String saveOwner = ownerDao.save(owner);
                newHouse.setOwner(owner);


                RentInfo rentInfo = new RentInfo();
                rentInfo.setHouse(newHouse);
                rentInfo.setOwner(owner);
                newHouse.setRentInfo(rentInfo);
                owner.getRentInfoList().add(rentInfo);
                owner.getHouses().add(newHouse);
                ownerDao.save(owner);
            }
        }
        return houseDao.save(newHouse);
    }

    @Override
    public House findById(Long houseId) {
        return houseDao.findById(houseId);
    }

    @Override
    public String deleteById(Long houseId) {
        return houseDao.deleteById(houseId);
    }

    @Override
    public String updateByID(Long id, House newHouse) {
        House foundHouse = findById(id);
        newHouse.setAddress(foundHouse.getAddress());
        newHouse.setHouseType(foundHouse.getHouseType());
        newHouse.setRoom(foundHouse.getRoom());
        newHouse.setDescription(foundHouse.getDescription());
        newHouse.setPrice(foundHouse.getPrice());
        newHouse.setRating(foundHouse.getRating());
        newHouse.setFurniture(foundHouse.isFurniture());
        newHouse.setOwner(foundHouse.getOwner());
        newHouse.setRentInfo(foundHouse.getRentInfo());
        System.out.println("""
                  Choose command
                  1.Room
                  2.Description
                3.Rating
                4.Furniture
                5.House type
                6.price
                  """);
        int action = new Scanner(System.in).nextInt();
        switch (action) {
            case 1 -> {
                System.out.println("write room int");
                newHouse.setRoom(new Scanner(System.in).nextInt());
            }case 2->{
                System.out.println("Write description");
                newHouse.setDescription(new Scanner(System.in).nextLine());
            }case 3-> {
                System.out.println("Write rating");
                newHouse.setRating(new Scanner(System.in).nextDouble());
            }case 4->{
                System.out.println("Weite furniture");
                newHouse.setFurniture(false);
            }case 5->{
                System.out.println("Write house tipe");
                newHouse.setHouseType(HouseType.HOUSE);
            }case 6->{
                System.out.println("Write price");
                newHouse.setPrice(new Scanner(System.in).nextBigDecimal());
            }
        }
        return houseDao.updateByID(id, newHouse);
    }

    @Override
    public List<House> getAllCustomer() {
        return houseDao.getAllCustomer();
    }

    @Override
    public List<House> getAllhouseseinOneAgency(Long agencyId) {
        return houseDao.getAllhouseseinOneAgency(agencyId);
    }

    @Override
    public List<House> getAllhousesinOneOwner(Long ownerID) {
        return houseDao.getAllhousesinOneOwner(ownerID);
    }

    @Override
    public List<House> getAllHousesBetween(Date in,Date out) {
        return houseDao.getHouseBetween(in,out);
    }
}
