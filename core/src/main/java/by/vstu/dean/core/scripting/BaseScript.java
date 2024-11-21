package by.vstu.dean.core.scripting;

import lombok.Getter;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;

@Getter
public class BaseScript {

    private final ScriptEngine scriptEngine;

    public BaseScript() {
        ScriptEngineManager manager = new ScriptEngineManager();
        scriptEngine = manager.getEngineByName("nashorn");
    }

    public void loadScript(String path) {
        try {
            FileReader reader = new FileReader(path);
            scriptEngine.eval(reader);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initCore() {
        this.scriptEngine.put("script", this);

        this.loadClass(System.class);
        this.loadClass(BaseScript.class);
    }


    public void loadClass(Class<?> clazz) {
        this.executeScript(String.format("var %s = Java.type(\"%s\");", clazz.getSimpleName(), clazz.getName()));
    }

    public Object executeFunction(String functionName, Object... args) {
        try {
            if(scriptEngine instanceof Invocable invocable) {
                return invocable.invokeFunction(functionName, args);
            }

            throw new UnsupportedOperationException("Script engine does not support Invocable interface");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void executeScript(String script) {
        try {
            scriptEngine.eval(script);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }


}
