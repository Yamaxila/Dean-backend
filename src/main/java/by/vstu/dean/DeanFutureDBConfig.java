package by.vstu.dean;


import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "futureEntityManagerFactory",
        transactionManagerRef = "futureTransactionManager",
        basePackages = { "by.vstu.dean.future"}
)
@AutoConfigurationPackage(basePackages = { "by.vstu.dean.future"})
public class DeanFutureDBConfig {

    @Primary
    @Bean(name="futureDataSource")
    @ConfigurationProperties(prefix="spring.datasource.future")
    public DataSource futureDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "futureEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean futureEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                             @Qualifier("futureDataSource") DataSource primaryDataSource) {
       return builder
                .dataSource(primaryDataSource)
                .packages("by.vstu.dean.future")
               .properties(jpaProperties())
               .build();
    }

    @Bean(name = "futureTransactionManager")
    public PlatformTransactionManager futureTransactionManager(
            @Qualifier("futureEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {

        return new JpaTransactionManager(primaryEntityManagerFactory);
    }

    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
        props.put("hibernate.implicit_naming_strategy", ImplicitNamingStrategyLegacyJpaImpl.class.getName());
        return props;
    }

//    @Bean
//    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
//        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
//        resourceDatabasePopulator.addScript(new ClassPathResource("/schema.sql"));
//        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
//        dataSourceInitializer.setDataSource(dataSource);
//        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
//        return dataSourceInitializer;
//    }
//
//    @Bean(name = "futureGroupsRepo")
//    public GroupsRepo groupsRepo() {
//        return new GroupsRepoImpl();
//    }

}
