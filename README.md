# project
1.ManageSystem 是改造为Maven后的项目  
2.MVCProject 是改造为MVC后的项目   
3.MVCProject_Bean 添加为bean后的项目  
4.MVCProject_Bean 第四次作业，事务回滚  
5.springboot-Demo为最终的大作业

注  
1.第一次作业的git地址为:https://github.com/dbylx/HomeworkManagement.git  
2.这个仓库中有第二次，第三次，第四次的作业  
3.二三次作业有一个库( https://github.com/dbylx/Project.git )但是出了点问题push不上去，所以将2，3，4次作业全部放在了这里  
4.第四次作业在MVCProject_Bean中，添加日志打印类，以及server层数据事务回滚类  
5.springboot-Demo为最终的大作业


springboot-Demo项目简介如下

@[TOC](作业管理系统大作业)

# 一、系统功能
**1.教师**
- 登陆注册
- 布置作业
- 给作业打评语(批阅)
- 给作业打分(批阅)
- 查看所有学生信息
- 添加学生
- 消除学生，级联消除所有作业

**2学生**
- 登陆注册
- 查看老师布置的作业
- 查看自己提交的作业
- 完成提交老师布置的作业
- 在老师批阅之前可以更新未批阅的作业
- 可以查看优秀作业

# 二、使用技术
- 1.Spring boot
SpringBoot是一种全新的框架，目的是为了简化Spring应用的初始搭建以及开发过程。
- 2.Mybaits
MyBatis 是一个优秀的持久层框架，它对jdbc的操作数据库的过程进行封装，使开发者只需要关注 SQL 本身，而不需要花费精力去处理例如注册驱动、创建connection、创建statement、手动设置参数、结果集检索等jdbc繁杂的过程代码。

- 3.lombok
简化代码，特别是@Data注解可以省略种多get set方法。

 - 4.Docker
是一个开源的应用容器引擎，让开发者可以打包他们的应用以及依赖包到一个可移植的容器中,然后发布到任何流行的Linux机器或Windows 机器上,也可以实现虚拟化,容器是完全使用沙箱机制,相互之间不会有任何接口。

# 三、代码结构
## 1. Spring boot 的类图和包图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618233028169.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)

整个逻辑层可以分为三层，controller层，service层，mapper层。model层是表的映射，一个类对应于一个表。mapper直接和数据库联系的，直接操作数据库数据。service层对mapper层进行封装，便于controller层的调用。

## 2. 整个代码的层级结构

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618235444480.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618235532131.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
## 3. 部分代码展示
- 3.1 Teacher类(Mode层)

```java
package com.example.demo.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "s_teacher")
public class Teacher {
    @Id
    String username;
    String name;
    String password;
    String create_time;
    String update_time;
    int id;
}

```
model层的类都对应一张表

- 3.2 mapper层(StudentHomework类)

```java
package com.example.demo.db.mapper;

import com.example.demo.db.model.StudentHomework;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentHomeworkMapper {
    List<StudentHomework> SelectAllStudentHomeworkBytitle(String homework_title);
    List<StudentHomework> SelectAllStudentHomeworkByUsername(String username);
    List<StudentHomework> SelectAllStudentHomeworkByUsernameTitle(String homework_title, String username);
    List<StudentHomework> SelectAllStudentHomeworkByGood();

    void InsertStudentHomework(@Param("username") String username, @Param("title")String title, @Param("content")String content);

    void UpdateReview(StudentHomework studentHomework);
    void UpdateHomework(StudentHomework studentHomework);
}

```
model层直接操作数据库

- 3.3 service层(StudentHomework类)

```java
package com.example.demo.db.server;

import com.example.demo.db.mapper.StudentHomeworkMapper;
import com.example.demo.db.model.StudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentHomeworkServer {
    private StudentHomeworkMapper studentHomeworkMapper;
    @Autowired
    public StudentHomeworkServer(StudentHomeworkMapper studentHomeworkMapper){
        this.studentHomeworkMapper = studentHomeworkMapper;
    }
    public List<StudentHomework> SelectAllStudentHomeworkBytitle(String title){
//        return null;
        System.out.println(title);
        return studentHomeworkMapper.SelectAllStudentHomeworkBytitle(title);
    }

    public List<StudentHomework> SelectAllStudentHomeworkByUsername(String username){
//        return null;
//        System.out.println(title);
        return studentHomeworkMapper.SelectAllStudentHomeworkByUsername(username);
    }

    public List<StudentHomework> SelectAllStudentHomeworkByUsernameTitle(String title, String username){
//        return null;
//        System.out.println(title);
        return studentHomeworkMapper.SelectAllStudentHomeworkByUsernameTitle(title,username);
    }

    public List<StudentHomework> SelectAllStudentHomeworkByGood(){
//        return null;
//        System.out.println(title);
        return studentHomeworkMapper.SelectAllStudentHomeworkByGood();
    }




    public boolean InsertStudentHomework(String username,String title,String content){
        try {
            studentHomeworkMapper.InsertStudentHomework(username, title, content);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean UpdateReview(StudentHomework studentHomework){
        try{
            studentHomeworkMapper.UpdateReview(studentHomework);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean UpdateHomework(StudentHomework studentHomework){
        try{
            studentHomeworkMapper.UpdateHomework(studentHomework);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

```
service层对mapper进行封装
- Controller层(TeacherController类)

```java
package com.example.demo.controller;

import com.example.demo.core.response.DataResponse;
import com.example.demo.core.response.ListResponse;
import com.example.demo.db.model.Student;
import com.example.demo.db.model.StudentHomework;
import com.example.demo.db.server.HomeworkServer;
import com.example.demo.db.server.StudentHomeworkServer;
import com.example.demo.db.server.StudentServer;
import com.example.demo.db.server.TeacherServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/teacher")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class TeacherController {

    private final TeacherServer teacherServer;
    private final StudentServer studentServer;
    private final StudentHomeworkServer studentHomeworkServer;
    private final HomeworkServer homeworkServer;
    @Autowired
    public TeacherController(TeacherServer teacherServer,StudentServer studentServer,StudentHomeworkServer studentHomeworkServer,HomeworkServer homeworkServer){
        this.teacherServer = teacherServer;
        this.studentServer = studentServer;
        this.studentHomeworkServer = studentHomeworkServer;
        this.homeworkServer = homeworkServer;
    }

    @RequestMapping("/addStudentOption")
    public String addStudentOption(){
//        System.out.println(username);
//        boolean flag = studentServer.addStudent(name,username,password);
        return "addStudent";
    }

    @RequestMapping("/deleteStudentJump")
    public String deleteStudentJump(){
//        System.out.println(username);
//        boolean flag = studentServer.addStudent(name,username,password);
        return "deleteStudent";
    }



    @RequestMapping("/addHomeworkOption")
    public String addHomeworkOption(){
//        System.out.println(username);
//        boolean flag = studentServer.addStudent(name,username,password);
        return "addHomework";
    }

    @RequestMapping("/queryHomeworkOption")
    public String queryHomeworkOption(){
//        System.out.println(username);
//        boolean flag = studentServer.addStudent(name,username,password);
        return "queryHomework";
    }


    @RequestMapping("/addStudent")
    @ResponseBody
    public DataResponse addStudent(String name, String username,String password){
//        System.out.println(username);
        boolean flag = studentServer.addStudent(name,username,password);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setFlag(flag);
        return dataResponse;
    }

    @RequestMapping("/addHomework")
    @ResponseBody
    public DataResponse addHomework(String title,String content){
        boolean flag = homeworkServer.addHomework(title,content);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setFlag(flag);
        return dataResponse;
    }

    @RequestMapping("/queryOneHomeworkSumbitJump")
    public String queryOneHomeworkSumbitJump(){

        return "queryStudentSumbitHomework";
    }

    @RequestMapping("/reviewHomework")
    public String reviewHomework(){

        return "reviewHomework";
    }

    @ResponseBody
    @RequestMapping("/dealReviewHomework")
    public DataResponse dealReviewHomework(StudentHomework studentHomework){
        System.out.println(studentHomework.toString());
        boolean flag = studentHomeworkServer.UpdateReview(studentHomework);


        DataResponse dataResponse = new DataResponse();
        dataResponse.setFlag(true);
        return dataResponse;
    }


    @ResponseBody
    @RequestMapping("/deleteStudent")
    public DataResponse deleteStudent(String username){
        System.out.println(username);
        boolean flag = studentServer.deleteStudent(username);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setFlag(flag);
        return dataResponse;
    }



    @RequestMapping("/queryOneHomeworkSumbit")
    @ResponseBody
    public ListResponse<StudentHomework> queryOneHomeworkSumbit(String title){
        List<StudentHomework> list = studentHomeworkServer.SelectAllStudentHomeworkBytitle(title);
        ListResponse<StudentHomework> reList = new ListResponse<>();
        reList.setData(list);
        return reList;
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public ListResponse<Student> selectAll(String title){
        List<Student> list = studentServer.selectAll();
        ListResponse<Student> reList = new ListResponse<>();
        reList.setData(list);
        return reList;
    }
}

```
Controller调用Service层服务完成业务逻辑操作。
# 四、数据库生成
建立学生表，教师表，学生作业提交表，以及作业表

```sql
CREATE TABLE `s_teacher` (
    `username` VARCHAR(30) NOT NULL,
	`password` VARCHAR(30) NOT NULL,
	`name` VARCHAR(30) NOT NULL,
	`create_time` TIMESTAMP NOT NULL default CURRENT_TIMESTAMP,
	PRIMARY KEY (`username`));
	
	
CREATE TABLE `s_student` (
    `username` VARCHAR(30) NOT NULL,
	`password` VARCHAR(30) NOT NULL,
	`name` VARCHAR(30) NOT NULL,
	`create_time` TIMESTAMP NOT NULL default CURRENT_TIMESTAMP,

	PRIMARY KEY (`username`));
	
	
CREATE TABLE `s_student_homework` (
    `student_username` VARCHAR(30) NOT NULL,
	`homework_title` VARCHAR(30) NOT NULL,
	`homework_content` VARCHAR(1000) NOT NULL,
	`create_time` TIMESTAMP NOT NULL default CURRENT_TIMESTAMP,
	`grade` float DEFAULT -1,
	`comment` VARCHAR(1000),
	
	FOREIGN KEY(student_username) REFERENCES s_student(username) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(homework_title) REFERENCES s_homework(title) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (`student_username`,`homework_title`));
	
alter table `s_student_homework` MODIFY column `comment` VARCHAR(100) default "未批阅";


	
CREATE TABLE `s_homework` (
    `title` VARCHAR(100) NOT NULL,
	`content` VARCHAR(100) NOT NULL,
	`create_time` TIMESTAMP NOT NULL default CURRENT_TIMESTAMP,

	PRIMARY KEY (`title`));

```

# 五、功能展示
## 1.教师
### 1.1登陆注册功能
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618222007786.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)通过单选框选择教师注册教师账号,注册成功会弹出提示框

![在这里插入图片描述](https://img-blog.csdnimg.cn/202006182221577.png)
然后跳到登陆界面，教师可以使用注册的账号进行登陆。登陆的时候不需要选择，系统自动根据账号信息进行判断是教师还是学生。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200619003140525.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
### 1.2教师管理界面
登陆成功进入教室管理界面，有添加学生，消除学生，查询作业(包含对作业进行批阅的功能),和添加作业的功能

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618222347790.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
### 1.3添加学生
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618222607750.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
通过学生姓名，学生用户名以及密码等信息创建学生账号

### 1.4 消除学生(查看所有学生信息)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618222758117.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
可以查看所有注册的学生信息，同时可以对学生进行管理，点击消除按钮即可消除学生，同时会级联消除学生的作业信息

### 1.5布置作业
教师可以布置作业
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200619002801398.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)

### 1.6查看已经布置过的作业
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618223402978.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
教师可以看到自己布置过的作业，点击查看可以查看学生作业提交情况,点击查看第五次作业的信息。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618224051196.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
### 1.7批改作业
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618224155137.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618224248489.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
可以为学生的作业添加评语，并对学生的作业进行打分。

## 2学生
### 2.1注册
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618224705821.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
选取学生可对学生进行注册，注册之后可以进行登陆,登陆同教师登陆一致不再赘述。注册的时候需要选择是教师注册还是学生注册，但是在登陆的时候不需要选择，系统自动根据账号信息进行判断是教师还是学生。
### 2.2学生管理界面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618225113976.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
学生可以查看所有需要提交的作业，和我已经提交的作业，以及优秀作业(95分以上的作业)
### 2.3查看所有作业
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618225220939.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
这里有老师布置的所有作业，可以选择某一项作业进行提交。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618225254940.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
可以写入具体的作业内容，然后进行提交，只可以提交一次作业，但是可以在查看作业模块在老师未批阅之前对作业进行更新。

### 2.4查看我提交的作业
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618225903750.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
可以查看我提交的作业，点击详细信息可以查看详情

### 2.5更新作业
进入详情界面，可以查看自己作业的详细信息包括教师评语与分数。

![](https://img-blog.csdnimg.cn/2020061823014616.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618230256896.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
如果对作业不满意还可以在老师批阅作业之前对作业进行更新

### 2.6查看优秀作业
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618230351634.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
学生可以参观九十五分以上的优秀学生作业,同样点击详细信息可以查看作业的详情。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618230638997.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618230656137.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNDQ2ODc5,size_16,color_FFFFFF,t_70)
# 六、系统部署

# 1. docker镜像生成与运行

 - 在idea项目中使用clean和package对项目进行打包，生成jar包
 - 在jar包的目录下创建一个DockerFile([参考地址](https://blog.csdn.net/qq_33204709/article/details/106766474))
 

```
FROM openjdk:8
MAINTAINER acgkaka
LABEL name="demo" version="1.0" author="acgkaka"
COPY demo-0.0.1-SNAPSHOT.jar demo-docker-image.jar
CMD ["java", "-jar", "demo-docker-image.jar"]
```
- 进入cmd运行命令行进入当前文件夹下运行下面命令就可以生成镜像(前提配置好docker)

```
docker build -t demo-docker-image .
```

 - 执行下面语句就可以看到所有的image了

```
docker build -t demo-docker-image .
```

 - 运行镜像指定端口
```
docker run -d -p 8080:8080 demo-docker-image
```
 - docker ps -a 命令可查看所有运行的容器

# 2 数据库部署
数据库模块已部署到服务器，spring boot 前后端框架已经通过docker打包成镜像，端口为8080，因为数据库在阿里云服务器上，所以不论在那种环境下打包好的镜像可以直接运行。
