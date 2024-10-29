package by.vstu.dean.core.scripting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScriptConfig {

    private String name;
    private String scriptName;
    private List<String> preloadedClasses;
    private int order;
    private BaseScript loadedScript;
}
