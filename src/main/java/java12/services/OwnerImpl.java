package java12.services;

import java12.Gender;
import java12.HouseType;
import java12.daos.daosImpl.HouseDaoImpl;
import java12.daos.daosImpl.OwnerDaoImpl;
import java12.entities.House;
import java12.entities.Owner;
import java12.entities.RentInfo;
import java12.services.interfaces.OwnerInterface;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class OwnerImpl implements OwnerInterface {
    OwnerDaoImpl ownerDao = new OwnerDaoImpl();
    HouseDaoImpl houseDao = new HouseDaoImpl();

    @Override
    public String save(Owner newOwner) {
        System.out.println("Write first name");
        newOwner.setFirstName(new Scanner(System.in).nextLine());
        System.out.println("Write last name");
        newOwner.setLastName(new Scanner(System.in).nextLine());
        System.out.println("Write email");
        newOwner.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Write gender");
        newOwner.setGender(Gender.MALE);
        System.out.println("Write date of birth");
        newOwner.setDateOfBirth(Date.valueOf(LocalDate.of(2000, 4, 8)));
        newOwner.setHouses(new ArrayList<>());
        newOwner.setAgencies(new ArrayList<>());
        newOwner.setRentInfoList(new ArrayList<>());

        return ownerDao.save(newOwner);
    }

    @Override
    public String save(Owner ownernew, House newHouse) {


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


        Owner owner = new Owner();
        System.out.println("Write email ");
        String email = new Scanner(System.in).nextLine();
        List<Owner> owners = getAllOwners();
        for (int i = 0; i < owners.size(); i++) {
            if (owners.get(i).getEmail().equalsIgnoreCase(email)) {
                System.out.println("Write uniq email");
                Map<Integer, String> map = new HashMap<>();
                map.put(1, "rwe");
                map.put(2, "qoupy");
                map.put(3, "weqe");
                map.put(4, "rqqw");
                map.put(5, "rqqrewee");
                map.put(6, "rew");
                map.put(7, "rqyrwee");
                map.put(8, "rwrye");
                map.put(9, "rwuwye");
                StringBuilder uniqStreet = new StringBuilder();
                for (int j = 0; j < map.size(); j++) {
                    Random random = new Random();
                    int  integer = random.nextInt( 9);
                    uniqStreet.append(map.get(integer));
                }
                owner.setEmail(uniqStreet.toString() + "@gmail.com");
            }
        }
        owner.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Write firstName ");
        owner.setFirstName(new Scanner(System.in).nextLine());
        System.out.println("Write last name ");
        owner.setLastName(new Scanner(System.in).nextLine());
        System.out.println("Write gender ");
        owner.setGender(Gender.MALE);

        System.out.println("Write your date of birth");
        LocalDate localDate = LocalDate.of(new Scanner(System.in).nextInt(), new Scanner(System.in).nextInt(), new Scanner(System.in).nextByte());
        if (localDate.getYear() > 18) {
            owner.setDateOfBirth(Date.valueOf(localDate));
        } else {
            owner.setDateOfBirth(Date.valueOf(LocalDate.of(2000, 4, 4)));
        }
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

        return houseDao.save(newHouse);

    }

    @Override
    public Owner findById(Long ownerId) {
        return ownerDao.findById(ownerId);
    }

    @Override
    public String deleteById(Long ownerId) {
        return ownerDao.deleteById(ownerId);
    }

    @Override
    public String updateByID(Long id, Owner newowner) {
        Owner byId = ownerDao.findById(id);
        newowner.setRentInfoList(byId.getRentInfoList());
        newowner.setEmail(byId.getEmail());
        newowner.setGender(byId.getGender());
        newowner.setHouses(byId.getHouses());
        newowner.setAgencies(byId.getAgencies());
        newowner.setLastName(byId.getLastName());
        newowner.setFirstName(byId.getFirstName());
        newowner.setDateOfBirth(byId.getDateOfBirth());
        System.out.println("""
                Choose update : 
                1.First name
                2.LAst name
                3.Email
                4.Gender
                5.Date of birth
                """);
        int action = new Scanner(System.in).nextInt();
        switch (action) {
            case 1 -> {
                System.out.println("Write first name");
                newowner.setFirstName(new Scanner(System.in).nextLine());
            }
            case 2 -> {
                System.out.println("Write last name");
                newowner.setLastName((new Scanner(System.in).nextLine()));
            }
            case 3 -> {
                System.out.println("Write email");
                newowner.setEmail(new Scanner(System.in).nextLine());
            }
            case 4 -> {
                System.out.println("Write gender");
                newowner.setGender(Gender.FEMALE);
            }
            case 5 -> {
                System.out.println("Write date of birth");
                newowner.setDateOfBirth(Date.valueOf(LocalDate.now()));
            }
        }
        return ownerDao.updateByID(id, newowner);
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerDao.getAllOwners();
    }

    @Override
    public List<Owner> getAllOwnersNameAndAge() {
        return ownerDao.getAllOwnersNameAndAge();
    }
}
