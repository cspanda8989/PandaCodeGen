package cyanstone.tools.code.generator.codegenflow.generator;

import cyanstone.tools.code.generator.codegenflow.CodeFileGenerator;
import cyanstone.tools.code.generator.codegenflow.CodeGenerator;
import cyanstone.tools.code.generator.codegenflow.settings.GlobalSettings;
import cyanstone.tools.code.generator.codegenflow.settings.ProjectModule;

import java.io.File;

public abstract class AbstractBasicMvnProjectGenerator implements CodeGenerator {
    @Override
    public void generateProject(GlobalSettings globalSettings, String directoryPath, ProjectModule projectModule) throws Exception {
        String parentPath = directoryPath + "//" + globalSettings.getPrjName();
        File parentFolder = new File(parentPath);
        parentFolder.mkdirs();
        CodeFileGenerator pomGenerator = getMavenDependencyGenerator();
        if (pomGenerator != null) {
            pomGenerator.generateFiles(globalSettings, parentPath, projectModule);
        }
        CodeFileGenerator resourceGenerator = getResourceGenerator();
        if (resourceGenerator != null) {
            resourceGenerator.generateFiles(globalSettings, parentPath, projectModule);
        }
        CodeFileGenerator codeModuleGenerator = getCodeModuleGenerator();
        if (codeModuleGenerator != null) {
            codeModuleGenerator.generateFiles(globalSettings, parentPath, projectModule);
        }
    }

    protected abstract AbstractMavenDependencyGenerator getMavenDependencyGenerator();

    protected abstract AbstractResourceGenerator getResourceGenerator();

    protected abstract AbstractCodeModuleGenerator getCodeModuleGenerator();

}
