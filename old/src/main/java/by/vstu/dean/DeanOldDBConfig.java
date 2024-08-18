package by.vstu.dean;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация базы данных для старой БД.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "deanEntityManagerFactory",
        transactionManagerRef = "deanTransactionManager",
        basePackages = {"by.vstu.dean.old"}
)
public class DeanOldDBConfig {

    /**
     * Создает источник данных для старой БД.
     *
     * @return Источник данных для старой БД.
     */
    @Bean(name = "deanDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.old")
    public DataSource deanDataSource() {
        System.err.println("Old dean dataSource");
        HikariDataSource ds = (HikariDataSource) DataSourceBuilder.create().build();

        ds.setConnectionTestQuery("SELECT 1");
        return ds;
    }

    /**
     * Создает фабрику менеджера сущностей для старой БД.
     *
     * @param builder        Построитель фабрики менеджера сущностей.
     * @param deanDataSource Источник данных для старой БД.
     * @return Фабрика менеджера сущностей для старой БД.
     */
    @Bean(name = "deanEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean deanEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("deanDataSource") DataSource deanDataSource) {
        System.out.println("ssssssssssssssssssssssssss");
        return builder
                .dataSource(deanDataSource)
                .packages("by.vstu.dean.old")
                .properties(jpaProperties())
                .build();
    }

    /**
     * Создает менеджер транзакций для старой БД.
     *
     * @param deanEntityManagerFactory Фабрика менеджера сущностей для старой БД.
     * @return Менеджер транзакций для старой БД.
     */
    @Bean(name = "deanTransactionManager")
    public PlatformTransactionManager deanTransactionManager(
            @Qualifier("deanEntityManagerFactory") EntityManagerFactory deanEntityManagerFactory) {
        System.out.println("ssssssssssssssssssssssssss");

        return new JpaTransactionManager(deanEntityManagerFactory);
    }

    /**
     * Возвращает настройки JPA.
     *
     * @return Map объект с настройками JPA.
     */
    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        System.out.println("ssssssssssssssssssssssssss");

        props.put("hibernate.physical_naming_strategy", PhysicalNamingStrategyStandardImpl.class.getName());
        props.put("hibernate.implicit_naming_strategy", ImplicitNamingStrategyLegacyJpaImpl.class.getName());
        return props;
    }
}
