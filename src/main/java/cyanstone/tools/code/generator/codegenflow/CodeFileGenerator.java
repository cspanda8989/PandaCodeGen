package cyanstone.tools.code.generator.codegenflow;

import cyanstone.tools.code.generator.codegenflow.settings.GlobalSettings;
import cyanstone.tools.code.generator.codegenflow.settings.ProjectModule;

import java.io.IOException;

public interface CodeFileGenerator {
    void generateFiles(GlobalSettings globalSettings, String directoryPath, ProjectModule projectModule) throws IOException, Exception;
}
