import cyanstone.tools.code.generator.codegenflow.CodeGenerator;
import cyanstone.tools.code.generator.codegenflow.settings.GlobalSettings;
import cyanstone.tools.code.generator.codegenflow.settings.ProjectType;
import cyanstone.tools.code.generator.flyway.FlywayProjectCodeGeneratorImpl;
import org.junit.Test;

import static cyanstone.tools.code.generator.codegenflow.settings.ProjectModule.DEFAULT;

public class GenerateFlywayProjectTest {

    @Test
    public void generateFlywayDemo() throws Exception {
        String outputPath = "E:\\code\\autogen\\demo-auto-gen";

        GlobalSettings globalSettings = new GlobalSettings(
                ProjectType.FLYWAY,
                "test-admin",
                "test-admin-flyway",
                "pandacodegen.tech",
                "https://xxxx.git",
                "src\\test\\resources\\data-dictionary-template.xlsx"
        );

        globalSettings.setDBData("jdbc:mysql://ip:port/dbname?useSSL=false", "", "");

        CodeGenerator flywayGenerator = new FlywayProjectCodeGeneratorImpl();
        flywayGenerator.generateProject(globalSettings, outputPath, DEFAULT);
    }
}
