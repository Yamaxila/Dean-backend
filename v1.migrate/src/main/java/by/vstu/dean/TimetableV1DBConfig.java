package by.vstu.dean;


import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация базы данных для новой БД.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "timetableV1EntityManagerFactory",
        transactionManagerRef = "timetableV1TransactionManager",
        basePackages = {
                "by.vstu.migrate.timetable"
        }
)
@ComponentScan(basePackages = {
        "by.vstu.migrate.timetable"
})
@EntityScan(basePackages = {
        "by.vstu.migrate.timetable"
})
@AutoConfigurationPackage(basePackages = {
        "by.vstu.migrate.timetable"
})
public class TimetableV1DBConfig {

    /**
     * Создает источник данных для новой БД.
     *
     * @return Источник данных для новой БД.
     */
    @Bean(name = "timetableV1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.timetable.v1")
    public DataSource futureDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Создает фабрику менеджера сущностей для новой БД.
     *
     * @param builder           Построитель фабрики менеджера сущностей.
     * @param primaryDataSource Источник данных для новой БД.
     * @return Фабрика менеджера сущностей для новой БД.
     */
    @Bean(name = "timetableV1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean futureEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                             @Qualifier("timetableV1DataSource") DataSource primaryDataSource) {
        return builder
                .dataSource(primaryDataSource)
                .packages(
                        "by.vstu.migrate.timetable"
                )
                .properties(jpaProperties())
                .build();
    }

    /**
     * Создает менеджер транзакций для новой БД.
     *
     * @param primaryEntityManagerFactory Фабрика менеджера сущностей для новой БД.
     * @return Менеджер транзакций для новой БД.
     */
    @Bean(name = "timetableV1TransactionManager")
    public PlatformTransactionManager futureTransactionManager(
            @Qualifier("timetableV1EntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }

    /**
     * Возвращает настройки JPA.
     *
     * @return Map объект с настройками JPA.
     */
    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
        props.put("hibernate.implicit_naming_strategy", ImplicitNamingStrategyLegacyJpaImpl.class.getName());
        return props;
    }
}