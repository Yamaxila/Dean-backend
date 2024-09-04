package by.vstu.dean.core.docx;

import by.vstu.dean.core.utils.ReflectionUtils;
import lombok.Getter;
import lombok.Setter;
import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;


public class BaseDocX {

    private File input;
    @Getter
    private Docx docx;

    @Getter
    @Setter
    protected String templatesPath;
    @Getter
    @Setter
    protected String tempPath;

    public BaseDocX(String templatesPath, String tempPath) {
        this.templatesPath = templatesPath;
        this.tempPath = tempPath;
    }

    public BaseDocX load(String name) {
        return this.load(new File(this.templatesPath + "/" + name));
    }

    public BaseDocX load(File input) {
        this.input = input;

        this.docx = new Docx(this.input.getAbsolutePath());
        this.docx.setVariablePattern(new VariablePattern("${", "}"));
        return this;
    }


    public Variables fill(Object model) {
        Variables variables = new Variables();

        docx.findVariables().stream().map(m -> {

            String o = m.replace("${", "")
                    .replace("}", "")
                    .replace(" ", "");
            String path = o.contains(":") ? o.split(":")[1] : o;
            Object value = ReflectionUtils.findFieldAndGet(path, model);

            if(value == null)
                return new TextVariable(m, m);

            return new TextVariable(m, value.toString());
        }).forEach(variables::addTextVariable);

        return variables;
    }


    public void process(Variables variables) {
        this.docx.fillTemplate(variables);
    }

    public File save() {
        File out = new File((this.tempPath + "/" + UUID.randomUUID() + this.input.getName()).replace("//", "/"));

        try {
            this.docx.save(Files.newOutputStream(out.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out;
    }


}
