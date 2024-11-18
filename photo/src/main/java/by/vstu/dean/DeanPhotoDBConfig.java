package by.vstu.dean;

import by.vstu.old.dean.PreventAnyUpdate;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация базы данных для выгрузки фотографий студентов.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "deanEntityManagerFactory",
        transactionManagerRef = "deanTransactionManager",
        basePackages = {"by.vstu.photo.dean"}
)
@ComponentScan(basePackages = "by.vstu.photo.dean")
@EntityScan(basePackages = {
        "by.vstu.photo.dean"
})
@AutoConfigurationPackage(basePackages = {
        "by.vstu.photo.dean"
})
//Сразу запрещаем любые изменения в старой базе
@Import({PreventAnyUpdate.class})
public class DeanPhotoDBConfig {


    /**
     * Создает источник данных для БД с фото студентов.
     *
     * @return Источник данных для БД с фото студентов.
     */
    @Bean(name = "photoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.photo")
    public DataSource deanDataSource() {
        HikariDataSource ds = (HikariDataSource) DataSourceBuilder.create().build();
        ds.setConnectionTestQuery("SELECT 1");
        return ds;
    }

    /**
     * Создает фабрику менеджера сущностей для БД с фото студентов.
     *
     * @param builder        Построитель фабрики менеджера сущностей.
     * @param deanDataSource Источник данных для БД с фото студентов.
     * @return Фабрика менеджера сущностей для БД с фото студентов.
     */
    @Bean(name = "photoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean photoEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                            @Qualifier("photoDataSource") DataSource deanDataSource) {
        return builder
                .dataSource(deanDataSource)
                .packages("by.vstu.photo.dean")
                .properties(jpaProperties())
                .build();
    }

    /**
     * Создает менеджер транзакций для БД с фото студентов.
     *
     * @param deanEntityManagerFactory Фабрика менеджера сущностей для БД с фото студентов.
     * @return Менеджер транзакций для БД с фото студентов.
     */
    @Bean(name = "photoTransactionManager")
    public PlatformTransactionManager photoTransactionManager(
            @Qualifier("photoEntityManagerFactory") EntityManagerFactory deanEntityManagerFactory) {
        return new JpaTransactionManager(deanEntityManagerFactory);
    }

    /**
     * Возвращает настройки JPA.
     *
     * @return Map объект с настройками JPA.
     */
    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", PhysicalNamingStrategyStandardImpl.class.getName());
        props.put("hibernate.implicit_naming_strategy", ImplicitNamingStrategyLegacyJpaImpl.class.getName());
        return props;
    }

}
