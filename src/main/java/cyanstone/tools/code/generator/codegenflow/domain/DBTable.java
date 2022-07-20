package cyanstone.tools.code.generator.codegenflow.domain;

import cyanstone.tools.code.generator.codegenflow.settings.GlobalSettings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DBTable {
    private String tableName;
    private String tableRemark;
    private Boolean isMainEntity;
    private Set<String> noCodeTags = new HashSet<>();
    private List<DBTableColumn> columns = new LinkedList<>();
    private List<String> primaryKeys = new LinkedList<>();
    private List<String> uniqueString = new LinkedList<>();
    private List<String> indexs = new LinkedList<>();

    private Map<String, DBTable> parentTables = new HashMap<>();


    public List<DBTableColumn> getBoolBizCols() {
        List<DBTableColumn> retCols = new LinkedList<>();
        columns.forEach((p) -> {
            if (p.isBoolBizField()) {
                retCols.add(p);
            }
        });
        return retCols;
    }

    public Set<String> getUKeySet() {
        Set<String> keySet = new HashSet<>();
        for (String ustr : uniqueString) {
            ustr = ustr.substring(ustr.indexOf("(") + 1, ustr.indexOf(")"));
            String[] keys = ustr.split(",");
            for (String k : keys) {
                if (StringUtils.isNotBlank(k)) {
                    keySet.add(k);
                }
            }
        }
        ;
        return keySet;
    }


    public boolean isDeleteLogically() {
        for (DBTableColumn col : columns) {
            if (col.getColname().equalsIgnoreCase("is_deleted")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEnabledLogic() {
        for (DBTableColumn col : columns) {
            if (col.getColname().equalsIgnoreCase("is_enabled")) {
                return true;
            }
        }
        return false;
    }


    public String getCodeTableName(Boolean isVariableStyle) {
        String[] words = tableName.split("_");
        for (int i = 0; i < words.length; i++) {
            if (isVariableStyle) {
                if (i == 0) {
                    words[i] = words[i].toLowerCase();
                    continue;
                }
            }
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1, words[i].length()).toLowerCase();
        }
        return StringUtils.join(words);
    }


    public String getMySQLDefineDescribe(GlobalSettings globalSettings) {
        String content = "drop table if exists `%s`;\ncreate table `%s` ( \n\t %s \n) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        String defineData = "";
        List<String> foreigns = new LinkedList<>();
        for (int i = 0; i < columns.size(); i++) {
            defineData += columns.get(i).getMySQLDefineDescribe();
            if (StringUtils.isNotBlank(columns.get(i).getForeignStatement())) {
                foreigns.add(String.format("(`%s`) REFERENCES %s", columns.get(i).getColname(), columns.get(i).getForeignStatement()));
            }
            if (i < columns.size() - 1) {
                defineData += ",\n\t";
            }
        }

        if (primaryKeys.size() > 1) {
            defineData += ",\n\t";
            defineData += "PRIMARY KEY (" + StringUtils.join(primaryKeys.stream().map(p -> "`" + p + "`").collect(Collectors.toList()), ",") + ")";
        } else if (primaryKeys.size() == 1) {
            if (!primaryKeys.get(0).toLowerCase().equals("id")) {
                defineData += ",\n\t";
                defineData += "PRIMARY KEY (`" + primaryKeys.get(0) + "`)";
            }
        }

        if (uniqueString.size() > 0) {
            defineData += ",\n\t";
            for (int i = 0; i < uniqueString.size(); i++) {
                defineData += "UNIQUE KEY " + uniqueString.get(i);
                if (i < uniqueString.size() - 1) {
                    defineData += ",\n\t";
                }
            }
        }

        if (indexs.size() > 0) {
            defineData += ",\n\t";
            for (int i = 0; i < indexs.size(); i++) {
                defineData += "INDEX " + indexs.get(i);
                if (i < indexs.size() - 1) {
                    defineData += ",\n\t";
                }
            }
        }

        if (globalSettings.isNeedForeignScript() && foreigns.size() > 0) {
            defineData += ",\n\t";
            for (int i = 0; i < foreigns.size(); i++) {
                defineData += " FOREIGN KEY " + foreigns.get(i);
                if (i < foreigns.size() - 1) {
                    defineData += ",\n\t";
                }
            }
        }

        return String.format(content, tableName, tableName, defineData);
    }


}
