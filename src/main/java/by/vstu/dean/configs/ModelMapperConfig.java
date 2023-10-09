package by.vstu.dean.configs;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Установка стратегии сопоставления
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Добавление других настроек по необходимости

        return modelMapper;
    }

}
