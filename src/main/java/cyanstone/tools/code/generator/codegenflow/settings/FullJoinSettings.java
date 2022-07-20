package cyanstone.tools.code.generator.codegenflow.settings;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

@Data
@ToString
public class FullJoinSettings implements Serializable {
    private String entityName;
    private String t1;
    private String t2;
    private String t3;
    private String t4;
    private String t5;
    private String joinMehtod;

    public FullJoinSettings(String entityName, String t1, String t2, String t3, String t4, String t5, String joinMehtod) {
        this.entityName = entityName;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.joinMehtod = StringUtils.isBlank(joinMehtod) ? "INNER JOIN" : joinMehtod;
    }
}
