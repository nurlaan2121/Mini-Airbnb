package java12.services.interfaces;

import java12.entities.RentInfo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RentInfoInterface {
    List<RentInfo> getRentBetweenDate(LocalDate checkIn, LocalDate checkOut);
    Integer getCountRent(Long agencyId);
}
