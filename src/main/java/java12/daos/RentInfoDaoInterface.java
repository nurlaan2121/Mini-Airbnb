package java12.daos;

import java12.entities.RentInfo;
import org.hibernate.cache.cfg.spi.DomainDataCachingConfig;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RentInfoDaoInterface {
    List<RentInfo> getRentBetweenDate(Date checkIn, Date checkOut);
    Integer getCountRent(Long agencyId);

    void update(RentInfo rentInfo,Long id);
}
