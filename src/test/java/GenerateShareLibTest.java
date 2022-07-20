import cyanstone.tools.code.generator.codegenflow.CodeGenerator;
import cyanstone.tools.code.generator.codegenflow.settings.GlobalSettings;
import cyanstone.tools.code.generator.codegenflow.settings.ProjectType;
import cyanstone.tools.code.generator.sharelib.ShareLibProjectCodeGeneratorImpl;
import org.junit.Test;

import static cyanstone.tools.code.generator.codegenflow.settings.ProjectModule.DEFAULT;

public class GenerateShareLibTest {

    @Test
    public void generateShareLibDemo() throws Exception {
        String outputPath = "E:\\code\\autogen\\demo-auto-gen";

        GlobalSettings globalSettings = new GlobalSettings(
                ProjectType.ShareModel,
                "test-admin",
                "test-admin-sharelib",
                "pandacodegen.tech",
                "https://xxx.git",
                "src\\test\\resources\\data-dictionary-template.xlsx"
        );


        CodeGenerator shareLibProjectGenerator = new ShareLibProjectCodeGeneratorImpl();
        shareLibProjectGenerator.generateProject(globalSettings, outputPath, DEFAULT);
    }
}
