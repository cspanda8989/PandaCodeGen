import cyanstone.tools.code.generator.codegenflow.CodeGenerator;
import cyanstone.tools.code.generator.codegenflow.settings.GlobalSettings;
import cyanstone.tools.code.generator.codegenflow.settings.ProjectType;
import cyanstone.tools.code.generator.simpleapp.CommonAppProjectCodeGeneratorImpl;
import org.junit.Test;

import static cyanstone.tools.code.generator.codegenflow.settings.ProjectModule.DEFAULT;

public class GenerateSimpleAppTest {

    @Test
    public void generateSimpleAppDemo() throws Exception {
        String outputPath = "E:\\code\\autogen\\demo-auto-gen";

        GlobalSettings globalSettings = new GlobalSettings(
                ProjectType.CommonApp,
                "test-admin",
                "test-admin-app-service",
                "pandacodegen.tech",
                "https://xxxx.git",
                "src\\test\\resources\\data-dictionary-template.xlsx"
        );
        //if elk == true, missing elk dependencies in pom
        globalSettings.setDBData("jdbc:mysql://ip:port/dbname?useSSL=false", "username", "password");
        globalSettings.setNeedELK(false);
        CodeGenerator commonAppProjectGenerator = new CommonAppProjectCodeGeneratorImpl();
        commonAppProjectGenerator.generateProject(globalSettings, outputPath, DEFAULT);

    }
}
