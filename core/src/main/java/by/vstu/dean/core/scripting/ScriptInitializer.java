package by.vstu.dean.core.scripting;

import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class ScriptInitializer {

    @Value("${dean.scripts.path}")
    private String scriptsPath;

    @Getter
    private List<ScriptConfig> configs;

    @PostConstruct
    @Order(100)
    public void init() {
        this.configs = this.loadConfigs();

        this.configs.forEach((config) -> {
            log.info("Loading {}", config.getScriptName());
            config.setLoadedScript(this.loadScript(config));
        });
    }

    public List<ScriptConfig> loadConfigs() {

        if (!new File(scriptsPath).exists()) {
            if (!new File(scriptsPath).mkdirs())
                throw new RuntimeException("Cannot create script directory");
            return new ArrayList<>();
        }

        List<ScriptConfig> scriptConfigs = new ArrayList<>();
        for (File file : Objects.requireNonNull(new File(this.scriptsPath).listFiles())) {
            if (file.getName().endsWith(".json")) {
                try {
                    scriptConfigs.add(new Gson().fromJson(new FileReader(file), ScriptConfig.class));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return scriptConfigs.stream().sorted(Comparator.comparing(ScriptConfig::getOrder)).toList();
    }

    public BaseScript loadScript(ScriptConfig config) {
        try {
            BaseScript script = new BaseScript();
            config.getPreloadedClasses().forEach((className) -> this.loadClass(script, className));
            script.initCore();
            script.loadScript(this.scriptsPath + "/" + config.getScriptName());
            script.getScriptEngine().put("manager", this);
            return script;
        } catch (Exception e) {
            log.error("Can't load script {}", config.getScriptName());
            return null;
        }
    }

    public void loadClass(BaseScript script, String className) {
        try {
            Class<?> clazz = Class.forName(className);
            script.loadClass(clazz);
        } catch (Exception e) {
            log.warn("Can't load class {}", className);
        }
    }

}
