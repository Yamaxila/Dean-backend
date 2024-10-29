package by.vstu.dean.core.docx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringDocX extends BaseDocX {

    @Value("${dean.templates.path}")
    private String templatesPath;
    @Value("${dean.temp.path}")
    private String tempPath;


    public SpringDocX() {
        super(null, null);
    }


}
