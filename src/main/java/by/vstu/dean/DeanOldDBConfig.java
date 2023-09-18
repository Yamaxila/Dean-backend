package by.vstu.dean;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "deanEntityManagerFactory",
        transactionManagerRef = "deanTransactionManager",
        basePackages = { "by.vstu.dean.old" }
)
public class DeanOldDBConfig {

    @Bean(name="deanDataSource")
    @ConfigurationProperties(prefix="spring.datasource.old")
    public DataSource deanDataSource() {
        HikariDataSource ds = (HikariDataSource) DataSourceBuilder.create().build();

        ds.setConnectionTestQuery("SELECT 1");
        return ds;
    }

    @Bean(name = "deanEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean deanEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                              @Qualifier("deanDataSource") DataSource deanDataSource) {
        return builder
                .dataSource(deanDataSource)
                .packages("by.vstu.dean.old")
                .properties(jpaProperties())
                .build();
    }

    @Bean(name = "deanTransactionManager")
    public PlatformTransactionManager deanTransactionManager(
            @Qualifier("deanEntityManagerFactory") EntityManagerFactory deanEntityManagerFactory) {
        return new JpaTransactionManager(deanEntityManagerFactory);
    }
    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", PhysicalNamingStrategyStandardImpl.class.getName());
        props.put("hibernate.implicit_naming_strategy", ImplicitNamingStrategyLegacyJpaImpl.class.getName());
        return props;
    }
}
