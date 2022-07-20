package cyanstone.tools.code.generator.codegenflow.domain;

import cyanstone.tools.code.generator.utils.RegexUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

@Data
@AllArgsConstructor
public class DBTableColumn {
    private Boolean isPrimaryKey;
    private String colname;
    private String type;
    private Boolean nullable;
    private String defaultValue;
    private String comment;
    private String foreignStatement;
    private DBTable mergedTableInfo;
    private Boolean isPartOfKey;//主键\唯一键

    public SizeCheck getSizeCheckData() {
        if (isPrimaryKey) {
            return null;
        }
        if (getCodeType().equals("String") && type.contains("(")) {
            int limit = Integer.parseInt(type.substring(type.indexOf("(") + 1, type.indexOf(")")));
            return new SizeCheck(0, limit, comment.split(",")[0] + "长度不能超出" + limit + "位");
        }
        return null;
    }

    public RefColData getRefData() {
        if (StringUtils.isBlank(foreignStatement)) {
            return null;
        }

        String tableName = foreignStatement.substring(0, foreignStatement.indexOf("("));
        String colName = foreignStatement.substring(foreignStatement.indexOf("(") + 1, foreignStatement.indexOf(")"));
        return new RefColData(tableName, colName);
    }

    public boolean isBoolBizField() {
        return "Boolean".equals(getCodeType()) && colname.startsWith("is_");
    }

    public String getMySQLDefineDescribe() {
        if (colname.toLowerCase().equals("id")) {
            if (isPrimaryKey) {
                return "`" + colname + "` " + type + " NOT NULL primary key AUTO_INCREMENT COMMENT 'id'";
            }
            return "`" + colname + "` " + type + " NOT NULL COMMENT 'id'";
        }
        String sql = "";
        if (colname.toLowerCase().equals("create_time")) {
            sql = "`create_time` DATETIME NOT NULL DEFAULT NOW()";
        } else if (colname.toLowerCase().equals("update_time")) {
            sql = "`update_time` DATETIME NOT NULL DEFAULT NOW()  ON UPDATE CURRENT_TIMESTAMP";
        } else {
            String codeType = getCodeType();
            if (StringUtils.isBlank(defaultValue))
                sql = String.format("`%s` %s %s", colname, type, nullable ? "" : "NOT NULL");
            else if (codeType.equals("String") || codeType.equals("Date"))
                sql = String.format("`%s` %s %s default '%s'", colname, type, nullable ? "" : "NOT NULL", defaultValue);
            else {
                sql = String.format("`%s` %s %s default %s", colname, type, nullable ? "" : "NOT NULL", defaultValue);
            }
        }

        if (!StringUtils.isBlank(comment))
            sql += " COMMENT '" + comment + "'";
        return sql;

    }

    public String getCodeType() {
        if (type.toLowerCase().contains("char") || type.toLowerCase().contains("text")) {
            return "String";
        }
        if (type.toLowerCase().contains("bigint")) {
            return "Long";
        }
        if (type.toLowerCase().contains("date")) {
            return "Date";
        }
        if (type.toLowerCase().contains("double")) {
            return "Double";
        }
        if (type.toLowerCase().contains("float")) {
            return "Float";
        }
        if (type.toLowerCase().contains("bool")) {
            return "Boolean";
        }
        if (type.toLowerCase().contains("decimal")) {
            return "BigDecimal";
        }
        if (type.toLowerCase().contains("blob")) {
            return "String";
        }
        return "Integer";
    }

    public Integer getColSize() {
        if (type.contains("(") && type.contains(")")) {
            String v = type.substring(type.indexOf("(") + 1, type.indexOf(")"));
            if (v.contains(",")) {
                v = v.substring(0, v.indexOf(","));
            }
            return Integer.valueOf(v);
        }
        return null;
    }

    public String getCodeColumnName() {
        String[] words = colname.split("_");
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                words[i] = words[i].toLowerCase();
                continue;
            }
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1, words[i].length()).toLowerCase();
        }
        return StringUtils.join(words);
    }

    public Boolean isMainAdminEntityField() {
        return colname.equalsIgnoreCase("created_by_user") ||
                colname.equalsIgnoreCase("updated_by_user") ||
                colname.equalsIgnoreCase("create_time") ||
                colname.equalsIgnoreCase("update_time") ||
                colname.equalsIgnoreCase("creator") ||
                colname.equalsIgnoreCase("updator");
    }

    public Boolean isBaseEntityField() {
        return colname.equalsIgnoreCase("create_time") ||
                colname.equalsIgnoreCase("update_time") ||
                colname.equalsIgnoreCase("creator") ||
                colname.equalsIgnoreCase("updator");
    }

    public String getAPIExample() {
        if (!isMainAdminEntityField()) {
            switch (getCodeType()) {
                case "String":
                    if (colname.toLowerCase().contains("type")) {
                        String typeComment = RegexUtils.getMatchedFieldType(comment);
                        if (StringUtils.isNotBlank(type)) {
                            String[] options = typeComment.split(",");
                            int index = options[0].indexOf("(");
                            if (index > 0) {
                                return options[0].substring(0, index).trim();
                            } else {
                                return options[0].trim();
                            }
                        }
                    } else {
                        return comment;
                    }
                    break;
                case "Boolean":
                    return "true";
                case "Long":
                case "Integer":
                case "Double":
                case "Float":
                case "BigDecimal":
                    return String.valueOf(RandomUtils.nextInt());
                case "Date":
                    return String.valueOf(System.currentTimeMillis());
                default:
                    return "";
            }
        }
        return "";
    }

    @Data
    @AllArgsConstructor
    public static class RefColData {
        private String tableName;
        private String colName;
    }

    @AllArgsConstructor
    @Data
    public static class SizeCheck {
        private Integer min;
        private Integer max;
        private String message;

    }
}
