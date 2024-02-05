package java12.services;

import java12.daos.RentInfoDaoInterface;
import java12.daos.daosImpl.RentInfoDaoImpl;
import java12.entities.RentInfo;
import java12.services.interfaces.RentInfoInterface;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class RentInfoImpl implements RentInfoInterface {
    RentInfoDaoImpl rentInfoDao = new RentInfoDaoImpl();

    @Override
    public List<RentInfo> getRentBetweenDate(LocalDate checkIn, LocalDate checkOut) {
        return rentInfoDao.getRentBetweenDate(Date.valueOf(checkIn), java.sql.Date.valueOf(checkOut));
    }

    @Override
    public Integer getCountRent(Long agencyId) {
        return rentInfoDao.getCountRent(agencyId);
    }
}
