# opp
  该套系统是一套集考试题库管理、考试信息管理和考试成绩管理三位一体的综合性考试管理系统。
##技术说明
前端使用技术：
<ul>
    <li><a href="javascritp:;">AngularJS--数据双向绑定</a></li>
    <li><a href="javascritp:;">jQuery--优秀的Javascript库</a></li>
    <li><a href="javascritp:;">及其基于jQuery的相关组件</a></li>
</ul>

后端使用技术:
<ul>
<li><a href="javascritp:;">Spring MVC--MVC架构的Controller层</a></li>
<li><a href="javascritp:;">Spring    --MVC架构的Sevice、Domain层</a></li>
 <li><a href="javascritp:;">MyBtais   --MVC架构的Dao层</a></li>
 <li><a href="javascritp:;">Mysql     --最流行开源的关系型数据库</a></li>
</ul><br>

##创建数据库
1、创建数据库：opp <br>
```sql
CREATE DATABASE opp;
USE opp;
```
创建数据库表：
导入webapp/sql文件的opp.sql文件

2、mysql账号密码在db.properties文件中修改
```jdbc
    jdbc.username = root
    jdbc.password = root
```