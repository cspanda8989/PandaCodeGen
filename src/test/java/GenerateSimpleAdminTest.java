import cyanstone.tools.code.generator.adminapp.backend.CommonAdminPlatformCodeGeneratorImpl;
import cyanstone.tools.code.generator.adminapp.frontend.FrontendCodeGeneratorImpl;
import cyanstone.tools.code.generator.codegenflow.CodeGenerator;
import cyanstone.tools.code.generator.codegenflow.settings.GlobalSettings;
import cyanstone.tools.code.generator.codegenflow.settings.ProjectType;
import org.junit.Test;

import static cyanstone.tools.code.generator.codegenflow.settings.ProjectModule.DEFAULT;


public class GenerateSimpleAdminTest {
    @Test
    public void generateDemo() throws Exception {
        String outputPath = "E:\\code\\autogen\\demo-auto-gen";

        GlobalSettings globalSettings = new GlobalSettings(
                ProjectType.CommonAdmin,
                "test-admin",
                "test-admin-admin-service",
                "pandacodegen.tech",
                "https://xxxx.git",
                "src\\test\\resources\\data-dictionary-template.xlsx"
        );
        //if elk == true, missing elk dependencies in pom
        globalSettings.setNeedELK(false);
        globalSettings.setAdminSettings(new GlobalSettings.AdminSettings("demo platform"));

        globalSettings.setDBData("jdbc:mysql://ip:port/dbname?useSSL=false", "username", "password");
        CodeGenerator commonAdminPlatformGenerator = new CommonAdminPlatformCodeGeneratorImpl();
        commonAdminPlatformGenerator.generateProject(globalSettings, outputPath, DEFAULT);
    }


    @Test
    public void generateAdminDemoVue() throws Exception {
        String outputPath = "E:\\code\\autogen\\demo-auto-gen";

        GlobalSettings globalSettings = new GlobalSettings(
                ProjectType.CommonAdminFrontend,
                "test-admin",
                "test-admin-admin-frontend",
                "pandacodegen.tech",
                "https://xxx.git",
                "src\\test\\resources\\data-dictionary-template.xlsx"
        );
        globalSettings.setAdminSettings(new GlobalSettings.AdminSettings("demo platform"));
        CodeGenerator frontendGenerator = new FrontendCodeGeneratorImpl();
        frontendGenerator.generateProject(globalSettings, outputPath, DEFAULT);
    }
}
