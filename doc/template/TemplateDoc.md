# 数据字典模板说明
## Sheet(工作表)
- 所有SheetName切勿使用中文, SheetName会被作为模块、代码变量声明。
- 字段前缀为 ***mainobj_*** <br>
工作表以 ***mainobj_*** 开头的表格内容会被划分为可管理对象，是业务系统正常运作的依赖数据，并会被springbootAdmin项目作为生成基础，会被纳入工具包代码生成范围。
- 字段前缀为 ***app_*** <br>
工作表以 ***app_*** 开头的表格内容会被划分业务过程数据，不由后台进行管理，会被纳入工具包代码生成范围。
- 字段前缀为 ***settings_*** <br>
工作表以 ***settings_*** 开头的表格内容为配置内容，不同的后缀命名会产生的生成效果，会被纳入工具包代码生成范围。
## DataDictionary MainObj, AppObj(数据字典对象)
| 列名 | 说明 |
| ------- | ------- |
|    table name     |      【必填】表标识名称      |
|    table remark     |      【必填】表注解      |
|    primary key     |      【必填】主键标识，暂时全部填id      |
|    col name     |      【必填】列标识      |
|    col type     |      【必填】列类型，暂时只支持mysql，含长度定义      |
|    nullable     |      【非必填】是否可为空，默认true      |
|    remark     |      【非必填】列注解，如果要生成AdminApp，此列【必填】      |
|    foreign     |      【非必填】外键关联，标记表之间的关联关系，以refTable(refCol)格式填写      |
|    default     |      【非必填】字段默认值      |
|    unique     |      【非必填】唯一键定义,以ukname(col1,col2,...)格式填写，多个时换行符分隔     |
|    index     |      【非必填】索引定义,以idxname(col1,col2,...)格式填写，多个时换行符分隔     |
#### 所有表结构默认必定义字段
- id
- creator
- updator
- create_time
- update_time
#### MainObj默认必定义字段
- id
- created_by_user
- updated_by_user
- creator
- updator
- create_time
- update_time
#### 字段说明
- is_开头的布尔值字段会被特殊处理
- 包含type关键字的字段如果满足"字段说明,{值1(值1说明),值2(值2说明)}"会被特殊处理
## 配置
### fulljoin
sheet以settings_fulljoin命名时，内容会被视作连表对象定义,PandaCodeGen会自动根据MainObj与AppObj中的foreign配置查询关联关系，最终生成基于Mybatis的DAO代码
#### 表格说明
| 列名 | 说明 |
| ------- | ------- |
|    对象名     |      【必填】联表定义的对象代码类名      |
|    主表     |      【必填】联表中第一张表被视作主表，需与MainObj或AppObj的table name一致      |
|    表2~表五     |      【必填】联查的数据表      |
|    连接方式     |      【非必填】表之间的连接方式，不填写时默认使用INNER JOIN      |


