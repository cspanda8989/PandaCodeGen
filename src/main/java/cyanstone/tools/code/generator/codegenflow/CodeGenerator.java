package cyanstone.tools.code.generator.codegenflow;

import cyanstone.tools.code.generator.codegenflow.settings.GlobalSettings;
import cyanstone.tools.code.generator.codegenflow.settings.ProjectModule;

public interface CodeGenerator {
    void generateProject(GlobalSettings globalSettings, String directoryPath, ProjectModule projectModule) throws Exception;
}