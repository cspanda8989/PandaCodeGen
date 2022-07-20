package cyanstone.tools.code.generator.codegenflow.generator;

import cyanstone.tools.code.generator.codegenflow.CodeFileGenerator;
import cyanstone.tools.code.generator.codegenflow.settings.GlobalSettings;

import java.io.File;

public abstract class AbstractMavenDependencyGenerator implements CodeFileGenerator {

    protected File getPOMFile(String prjDirectoryPath) {
        return new File(prjDirectoryPath + "//pom.xml");
    }

    protected String getScmTagContent(GlobalSettings globalSettings) {
        String template = " <scm>\n" +
                "        <connection>\n" +
                "            scm:git:$${gitRepo}\n" +
                "        </connection>\n" +
                "        <developerConnection>\n" +
                "            scm:git:$${gitRepo}\n" +
                "        </developerConnection>\n" +
                "        <tag>HEAD</tag>\n" +
                "    </scm>";
        return template.replace("$${gitRepo}", globalSettings.getGitRepo());
    }

}
