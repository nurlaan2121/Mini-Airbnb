package java12.configs;

import jakarta.persistence.EntityManagerFactory;
import java12.entities.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateConfig {
    public static EntityManagerFactory getEntityManagerFactory(){
        Properties properties  = new Properties();
        properties.put(Environment.DIALECT,"org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.URL,"jdbc:postgresql://localhost:5432/lmstask2");
        properties.put(Environment.USER,"postgres");
        properties.put(Environment.PASS,"nurlan21");
        properties.put(Environment.SHOW_SQL,"true");
        properties.put(Environment.HBM2DDL_AUTO,"update");
        properties.put(Environment.DRIVER,"org.postgresql.Driver");
        Configuration configuration = new Configuration();
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(Agency.class);
        configuration.addAnnotatedClass(Owner.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(House.class);
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(RentInfo.class);
        return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
    }
}
