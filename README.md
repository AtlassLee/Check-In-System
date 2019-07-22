这是去年做的一个系统，由于自己当时也刚开始学习前端，不可能在短时间内开发一个前端模板，所以使用了国外团队开发的SPRFLAT模板，在此基础上进行二次开发
# 基于SpringMVC的学生考勤系统
## SpringMVC简单流程图
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/springMVC.png)
## 1.需求分析
### 1.1数据需求
1.学生的基本信息录入，包括手动录入，通过excel文件录入，包括学生姓名，所属班级，学号，性别，出生日期，联系方式  
2.学生信息的修改  
3.课程信息的查询，添加，删除，包括课程的id，名称，任课老师，开始时间，结束时间(指的是一节课)  
4.班级信息的查询，添加，删除，包括班级名称，班主任，班长，联系方式  
5.考勤信息的查询，添加，删除，包括学生id，签到状态，课程id，相当于签到表  
6.教师信息的查询，添加，删除，包括账户名，真实名字，密码，账号激活状态，账号激活密码，账号申请时间，申请重置码，申请重置时间，电子邮箱地址

### 1.2功能需求
1.在学生信息管理部分,要求:  
a.查询各个学生的考勤率，旷课次数  
b.可以手动增删信息、或者用excel表导入信息  
2.在课程信息管理部分,要求:  
A.可以增删查改课程信息  
B.通过课程id生成二维码，二维码的URL为签到页面  
3.在班级信息管理部分,要求:。  
a.可以录入，删除，修改班级信息  
4.在考勤信息管理部分,要求:  
A.根据考勤信息计算总旷课率反馈到前端图表，便于观察  
A.根据考勤信息计算课程旷课率反馈到前端图表，便于观察  
A.根据考勤信息计算周旷课率反馈到前端图表，便于观察  
A.根据考勤信息计算每个学生旷课信息反馈到前端图表，便于观察  

## 2.E-R图
### 2.1用户信息实体E-R图
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/E-R-1.png)
### 2.2学生信息实体E-R图
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/E-R-2.png)
### 2.3课程信息实体E-R图
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/E-R-3.png)
### 2.4班级信息实体E-R图
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/E-R-4.png)
### 2.5总的信息实体E-R图
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/E-R-5.png)
## 3.数据字典
### 3.1用户信息表

 表中列表  | 数据类型  | 可否为空
 ---- | ----- | ------  
 用户名  | varchar | not null(主键) 
 密码  | varchar | not null 
 真实姓名  | varchar | not null 
 电子邮箱  | varchar | not null 
 账户激活状态  | varchar | not null 
账户申请时间  | varchar  |	not null
账户激活码	 |  varchar	|  not null
重置申请时间	|  varchar	| null
重置申请码	 |  varchar	 |  null

### 3.2考勤信息表
表中列名 |	数据类型	|  可否为空
 ---- | ----- | ------ 
考勤状态	| int	| not null
学生学号	|int	| not null(外键)
课程编号	|int	| not null(外键)
课程名称	|Varchar	|not null

### 3.3学生信息表
表中列名 |	数据类型	|  可否为空
 ---- | ----- | ------ 
学号	| int	| not null(主键)
姓名	| Varchar	| not null
所在班级	| Varchar	| not null(外键)
性别	|Varchar	|null
出生日期	|Varchar	|not null
联系方式	| Varchar	 | null
### 3.4班级信息表
表中列名	| 数据类型	| 可否为空
 ---- | ----- | ------ 
班级名称	|Varchar	|not null(主键）
班长	| Varchar	| not null
班主任	| Varchar	| not null
联系方式	| Varchar	| null
### 3.5用户信息表
表中列名	| 数据类型	| 可否为空
---- | ----- | ------ 
用户名	| Varchar	| not null(主键）
密码	| Varchar	| not null
真实名字	| Varchar	| not null
电子邮箱	| Varchar	| not null
账户激活状态	| Varchar	| not null
账户申请时间	| Varchar	| not null
账户激活码	| Varchar	| not null
重置申请码	| Varchar	 | null
重置申请时间	| Varchar	| null
## 4.数据库实现
### 4.1学生信息表
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/table1.png)
### 4.2班级信息表
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/table2.png)
### 4.3课程信息表
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/table3.png)
### 4.4考勤信息表
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/table4.png)
### 4.5用户信息表
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/table5.png)
### 4.6各表之间的关系
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/relation.png)
## 5.功能模块窗口截图
### 5.1登录注册模块
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/U1.png)
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/U2.png)
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/U3.png)
### 5.2用户信息模块
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/U4.png)
### 5.3视图总览模块
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/U5.png)
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/U6.png)
### 5.4学生考勤模块
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/U7.png)
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/U8.png)
### 5.5课程管理模块
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/U8.png)
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/U9.png)
### 5.6二维码扫码模块
![Image text](https://github.com/AtlassLee/Check-In-System/blob/master/Check-In-System/picture/U10.png)
该二维码为内网URL,扫描后跳转无效，需要将项目上传至服务器web容器上才能生成可用的二维码
