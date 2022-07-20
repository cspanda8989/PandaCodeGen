package cyanstone.tools.code.generator.codegenflow.settings;

import cyanstone.tools.code.generator.codegenflow.domain.DBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jasypt.util.text.BasicTextEncryptor;

import java.util.List;
import java.util.Map;

@Data
public class GlobalSettings {
    private ProjectType projectType;
    private String prjPrefix;
    private String prjName;
    private String groupId;

    private String gitRepo;
    private String dataDictionaryPath;
    //schema settings, 是否需要在生成SQL脚本的时候生成对应的外键语法，默认不生成
    private boolean needForeignScript = false;

    //db settings
    private String dbUrl;
    private String dbUser;
    private String dbPwd;

    //redis settings
    private boolean needRedis = false;
    private String redisHost;
    private String redisPwd;
    private int redisPort = 6379;
    private int redisDBIndex = 0;

    private AdminSettings adminSettings;
    private Map<String, List<DBTable>> dbTables;
    private boolean needSwagger = true;
    private boolean refShareLib = true;
    private boolean needNacos = false;
    private boolean needELK = true;
    private boolean needHealthCheck = true;

    public GlobalSettings(ProjectType projectType, String prjPrefix, String prjName, String groupId, String gitRepo, String dataDictionaryPath) {
        this.projectType = projectType;
        this.prjPrefix = prjPrefix;
        this.prjName = prjName;
        this.groupId = groupId;

        this.gitRepo = gitRepo;
        this.dataDictionaryPath = dataDictionaryPath;
    }

    public String getDBEcnPwd(String salt) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(salt);
        return textEncryptor.encrypt(dbPwd);
    }

    public void setRedisInfo(String host, String pwd) {
        this.needRedis = true;
        this.redisHost = host;
        this.redisPwd = pwd;
    }

    public void setDBData(String dbUrl, String dbUser, String dbPwd) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPwd = dbPwd;
    }

    public String getPredefineNameAsNamespace(ProjectType projectType) {
        return projectType.getPredefineName(this.prjPrefix).replace("-", ".");
    }

    @AllArgsConstructor
    @Data
    public static class AdminSettings {
        private String prjCNName;

    }


}
