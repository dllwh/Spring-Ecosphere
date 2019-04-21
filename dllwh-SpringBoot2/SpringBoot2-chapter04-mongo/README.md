# SpringBoot2-chapter4-mongo

> 即使你是天才，如果你不努力，你也会被其它人超越。

![](pic/mongodb.png)

## 开发环境

JDK1.8、Maven、Eclipse、SpringBoot2.0.5、mongodb3.6.4，Robomongo(可视化工具)

## MongoDB 简介

### 什么是MongoDB

&emsp;&emsp;MongoDB 是由C++语言编写的开源数据库系统，在高负载的情况下，添加更多的节点，可以保证服务器性能，MongoDB 旨在为WEB应用提供可扩展的高性能数据存储解决方案。

![](pic/mongodb-logo.png)

### 主要特点

- MongoDB的提供了一个面向文档存储，操作起来比较简单和容易。
- 你可以在MongoDB记录中设置任何属性的索引 (如：FirstName="Sameer",Address="8 Gandhi Road")来实现更快的排序。
- 你可以通过本地或者网络创建数据镜像，这使得MongoDB有更强的扩展性。
- 如果负载的增加（需要更多的存储空间和更强的处理能力） ，它可以分布在计算机网络中的其他节点上这就是所谓的分片。
- Mongo支持丰富的查询表达式。查询指令使用JSON形式的标记，可轻易查询文档中内嵌的对象及数组。
- MongoDb 使用update()命令可以实现替换完成的文档（数据）或者一些指定的数据字段 。
- Mongodb中的Map/reduce主要是用来对数据进行批量处理和聚合操作。
- Map和Reduce。Map函数调用emit(key,value)遍历集合中所有的记录，将key与value传给Reduce函数进行处理。
- Map函数和Reduce函数是使用Javascript编写的，并可以通过db.runCommand或mapreduce命令来执行MapReduce操作。
- GridFS是MongoDB中的一个内置功能，可以用于存放大量小文件。
- MongoDB允许在服务端执行脚本，可以用Javascript编写某个函数，直接在服务端执行，也可以把函数的定义存储在服务端，下次直接调用即可。
- MongoDB支持各种编程语言:RUBY，PYTHON，JAVA，C++，PHP，C#等多种语言。
- MongoDB安装简单。

### MongoDB 概念解析 

|SQL术语/概念|MongoDB术语/概念|解释/说明|
|---|---|---|
|database		|database	|数据库
|table			|collection	|数据库表/集合
|row			|document	|数据记录行/文档
|column			|field		|数据字段/域

**数据库**

&emsp;&emsp;一个mongodb中可以建立多个数据库。MongoDB的默认数据库为"db"，该数据库存储在data目录中。MongoDB的单个实例可以容纳多个独立的数据库，每一个都有自己的集合和权限，不同的数据库也放置在不同的文件中。

- **show dbs** 命令可以显示所有数据的列表
- 执行 **db** 命令可以显示当前数据库对象或集合
- 运行 **use** 命令，可以连接到一个指定的数据库
- 删除数据库

```
db.dropDatabase();
```

**集合**

&emsp;&emsp;集合就是 MongoDB 文档组，类似于`RDBMS`中的表格，集合存在于数据库中，集合没有固定的结构。

- 合法的集合名：
  - 集合名不能是空字符串""。
  - 集合名不能含有\0字符（空字符)，这个字符表示集合名的结尾。
  - 集合名不能以"system."开头，这是为系统集合保留的前缀。
  - 用户创建的集合名字不能含有保留字符。

- 查询某集合一条数据

```
db.collection_name.findOne()
```

- 删除集合

```
db.collection_name.drop()
```

**元数据**

&emsp;&emsp;数据库的信息是存储在集合中。它们使用了系统的命名空间

- 插入文档

```
db.collection_name.insert({"name":"独泪了无痕"})
```

- 更新文档

```
db.collection_name.update( criteria, objNew, upsert, multi);
```
> - criteria : update的查询条件，类似sql update查询内where后面的
> - objNew : update的对象和一些更新的操作符（如$,$inc...）等，也可以理解为sql update查询内set后面的
> - upsert : 这个参数的意思是，如果不存在update的记录，是否插入objNew,true为插入，默认是false，不插入
> - multi : mongodb默认是false,只更新找到的第一条记录，如果这个参数为true,就把按条件查出来多条记录全部更新

- 删除文档

```
db.collection_name.remove(query);
```
> - query :（可选）删除的文档的条件

- 查询文档

```
db.collection_name.find(); // 以非结构化的方式来显示所有文档
db.collection_name.find().pretty(); // 以格式化的方式来显示所有文档
```

- 条件操作符

&emsp;&emsp;条件操作符用于比较两个表达式并从mongoDB集合中获取数据，MongoDB中条件操作符有：
> - (>) 大于 - $gt
> - (<) 小于 - $lt
> - (>=) 大于等于 - $gte
> - (<= ) 小于等于 - $lte

如果你想获取 "col" 集合中 "likes" 大于 100 的数据，你可以使用以下命令：

```
db.collection_name.find({"likes" : {$gt : 100}})
``` 

类似于SQL语句：

```
Select * from col where likes > 100;
```

- MongoDB 数据类型

下表为MongoDB中常用的几种数据类型

|数据类型|描述|
|---|---
|String		|字符串。存储数据常用的数据类型。在 MongoDB 中，UTF-8 编码的字符串才是合法的。
|Integer	|整型数值。用于存储数值。根据你所采用的服务器，可分为 32 位或 64 位。
|Boolean	|布尔值。用于存储布尔值（真/假）。
|Double		|双精度浮点值。用于存储浮点值。
|Arrays		|用于将数组或列表或多个值存储为一个键。
|Timestamp	|时间戳。记录文档修改或添加的具体时间。
|Object		|用于内嵌文档。
|Null		|用于创建空值。
|Date		|日期时间。用 UNIX 时间格式来存储当前日期或时间。你可以指定自己的日期时间：创建 Date 对象，传入年月日信息。
|Object ID	|对象 ID。用于创建文档的 ID。

- Limit() 方法

&emsp;&emsp;如果你需要在MongoDB中读取指定数量的数据记录，可以使用MongoDB的Limit方法，limit()方法接受一个数字参数，该参数指定从MongoDB中读取的记录条数。

```
db.collection_name.find().limit(number);
```

- Skip() 方法

&emsp;&emsp;我们除了可以使用limit()方法来读取指定数量的数据外，还可以使用skip()方法来跳过指定数量的数据，skip方法同样接受一个数字参数作为跳过的记录条数。

```
db.collection_name.find().limit(number).skip(number);
```

- 排序

&emsp;&emsp;在MongoDB中使用使用sort()方法对数据进行排序，sort()方法可以通过参数指定排序的字段，并使用 1 和 -1 来指定排序的方式，其中 1 为升序排列，而-1是用于降序排列。

```
db.collection_name.find().sort({KEY:1})
```

- 索引

&emsp;&emsp;MongoDB使用 ensureIndex() 方法来创建索引

```
db.collection_name.ensureIndex({KEY:1})
```
> 语法中 Key 值为你要创建的索引字段，1为指定按升序创建索引，如果你想按降序来创建索引指定为-1即可
