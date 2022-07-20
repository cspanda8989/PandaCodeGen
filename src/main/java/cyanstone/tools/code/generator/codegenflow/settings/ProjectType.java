package cyanstone.tools.code.generator.codegenflow.settings;

public enum ProjectType {
    /**
     * 项目工具包
     */
    ShareModel("sharelib"),

    /**
     * 普通微服务应用
     */
    CommonAdmin("app-service"),

    /**
     * 管理后台vue前端
     */
    CommonAdminFrontend("admin-front"),

    /**
     * 管理后台java后端
     */
    CommonApp("admin-service"),

    /**
     * flyway类别项目，MYSQL DDL
     */
    FLYWAY("flyway"),

    /**
     * 待实现
     */
    ScheduleApp("schedule-app"),

    /**
     * 待实现
     */
    AuthGateway("auth-gateway"),

    /**
     * 待实现
     */
    APIService("api-service");

    final private String postfix;

    ProjectType(String postfix) {
        this.postfix = postfix;
    }


    public String getPredefineName(String prjName) {
        return prjName + "-" + postfix;
    }

}
