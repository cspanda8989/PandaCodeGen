# PandaCodeGen Quik Start
## 本项目使用条件
- 安装jdk1.8+
- 安装maven
## 本项目产物运行条件
### flyway
- 安装maven
- 拥有数据库访问权限
### sharelib
作为maven dependency被引用即可
### springbootApp
- 安装jdk1.8+
- 安装maven
- nacos（非必需）
- MYSQL（非必需）
- Redis（非必需）

### springbootAdmin
#### 前端
- NodeJS 12+

#### 后端
- 安装jdk1.8+
- 安装maven
- nacos（非必需）
- MYSQL（非必需）
- Redis（非必需）

## 使用步骤
- 下载代码
- 打开src/test/resources/data-dictionary-template.xlsx文件
- 安装模板填写好数据字典
- 保存并关闭数据字典
- 生成Flyway项目，打开GenerateFlywayProjectTest，修改generateFlywayDemo中的globalSettings必填属性，</br>
执行
``` 
mvn test -Dtest=GenerateFlywayProjectTest#generateFlywayDemo 
```
- 生成Sharelib项目，打开GenerateShareLibTest，修改generateShareLibDemo中的globalSettings必填属性，</br>
执行
``` 
mvn test -Dtest=GenerateShareLibTest#generateShareLibDemo 
```
- 生成普通SpringBoot项目，打开GenerateSimpleAppTest，修改generateSimpleAppDemo中的globalSettings必填属性，</br>
执行
``` 
mvn test -Dtest=GenerateSimpleAppTest#generateSimpleAppDemo 
```
- 生成管理后台SpringBoot/SpringCloud项目，打开GenerateSimpleAdminTest，修改generateDemo中的globalSettings必填属性，</br>
执行
``` 
mvn test -Dtest=GenerateSimpleAdminTest#generateDemo 
```
- 生成管理后台H5项目，打开GenerateSimpleAdminTest，修改generateAdminDemoVue中的globalSettings必填属性，</br>
执行
``` 
mvn test -Dtest=GenerateSimpleAdminTest#generateAdminDemoVue 
```