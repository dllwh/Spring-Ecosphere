# 2.1 基本内置对象

&emsp;&emsp;在JSP页面中,我们可以很轻松的获取几个内置对象，例如request、session等。在Thymeleaf的模板中，也提供了类似的内置对象：

|对象名称|描述|作用范围
|---|---|---|
|**#ctx**|表示模板引擎的全局上下文对象||
|**#locale**|直接访问与当前请求关联的java.util.Locale||
|**#request**|表示HttpServletRequest对象|Web环境|
|**#response**|表示HttpServletResponse对象|Web环境|
|**#session**|表示HttpSession对象|Web环境|
|**#servletContext**|表示ServletContext对象|Web环境|
|**#param**|||
|**#application**|||

- #ctx：上下⽂对象。
    - ${#ctx.request} 
    - ${#ctx.response} 
    - ${#ctx.session} 
    - ${#ctx.servletContext}
- #request：直接访问与当前请求关联的 javax.servlet.http.HttpServletRequest对象
    - ${#request.getAttribute('foo')}
    - ${#request.getParameter('foo')}
    - ${#request.getContextPath()}
    - ${#request.getRequestName()} 
- #session：直接访问与当前请求关联的javax.servlet.http.HttpSession 对象
    - ${#session.getAttribute('foo')}
    - ${#session.id}
    - ${#session.lastAccessedTime}
- #servletContext：直接访问与当前请求关联的 javax.servlet.ServletContext对象
    - ${#servletContext.getAttribute('foo')}
    - ${#servletContext.contextPath}

# 2.2 标准表达式语法

&emsp;&emsp;⾸先，我们来看看标准表达式功能的快速总结：

- 简单表达式
    - 变量表达式：$ {...}
    - 选择变量表达式：\* {...}
    - 消息表达式：＃{...}
    - 链接⽹址表达式：@ {...}
    - ⽚段表达式：〜{...}

# 2.3 链接表达式 **th:href**

&emsp;&emsp;在Web环境中，URL的处理尤为重要，Thymeleaf对此提供了链接表达式 **@{...}** ，该表达式支持绝对路径与相对路径。假设content-path 是`dllwh`，则在链接表达式中使用下表中所列的几种格式的URL。

||表达式|处理结果|访问路径|
|---|---|---|---|
|绝对路径|@{http://www.baidu.com}|http://www.baidu.com|www.baidu.com|
|页面相对路径  |@{user/getList}  |user/getList       | http://ip:port/dllwh/user/getList|
|上下文相对路径|@{/user/getList} |/dllwh/user/getList| http://ip:port/dllwh/user/getList|
|服务器相对路径|@{~/user/getList}|/user/getList      | http://ip:port/user/getList|
|协议相对路径  |@{/user/getList}|/user/getList     | user/getListx|

注意：
- th:href是⼀个修饰符属性：⼀旦处理，它将计算要使⽤的链接URL，并将该值设置为<a>标签的href属性。
- 我们被允许使⽤表达式的URL参数，所需的URL参数编码操作也将⾃动执⾏。
- 如果需要⼏个参数，这些参数将以逗号分隔
- 以/开头的相对URL将⾃动以应⽤程序上下⽂名称为前缀。

# 2.4 数字对象 #numbers

&emsp;&emsp;Thymeleaf主要使用`org.thymeleaf.expression.Numbers`这个类来处理数字，在模板中使用 **#numbers** 来表示这个对象

## 2.4.1 整数格式化 

&emsp;&emsp;**#numbers**提供了四个格式化整数的方法：

- **formatInteger(numbers,digits)**
    - 第一个参数是处理的整数，第二个参数是用于设置最少的整数位数。
```
    ${#numbers.formatInteger(15,3,'DEFAULT'))}
```

- **arrayFormatInteger(numbers,digits)**
    - 与formatInteger类似，但要传入数组，也会返回数组
```
    ${#numbers.formatInteger({10,20},3,'DEFAULT'))}
```

- **listFormatInteger(numbers,digits)**
    - 与formatInteger类似，但要传入List，返回处理后的List
```
    ${#numbers.listFormatInteger(numList,3,'DEFAULT'))}
```

- **setFormatInteger(numbers,digits)**
    - 与formatInteger类似，但要传入Set，返回处理后的Set
```
    ${#numbers.setFormatInteger(numSet,3,'DEFAULT'))}
```

> **注意事项**

1. 如何输入的整数位不足，则会加上`0`进行补充。
2. 如果输入的数字存在小数点，则会进行取整，四舍五入到个位。
3. 这四个方法每一个都存在重载方法，调用时如何传入第三个参数，则该参数将会用来作为分隔符

|||
|---|---
|POINT|使用`.`作为千位分割符|
|COMMA|使用`,`作为分隔符|
|WHITESPACE|使用` `(空格)作为分隔符|
|NONE|不使用分隔符|
|default|根据`Locale`对象来决定使用哪种分隔符|

## 2.4.2 小数格式化

小数格式化与整数格式化类似

- **formatDecimal(num,intDig,DecDig)**
    - 处理单个小数，第二个参数表示最少的整数位置，第三个参数表示保留的小数位数
```
    ${#numbers.formatDecimal(num,3,2,'DEFAULT'))}
```

- **arrayFormatDecimal(numArray,intDig,DecDig)**
    - 与formatDecimal类似，处理数组
```
    ${#numbers.arrayFormatDecimal(numArray,3,2,'DEFAULT')}
```

- **listFormatDecimal(numList,intDig,DecDig)**
    - 与formatDecimal类似，处理小数的List
```
    ${#numbers.listFormatDecimal(numList,3,2,'DEFAULT')}
```

- **setFormatDecimal(numSet,intDig,DecDig)**
    - 与formatDecimal类似，处理小数数组的Set
```
    ${#numbers.setFormatDecimal(numSet,3,2,'COMMA')}
```

## 2.4.3 货币格式化

**#numbers** 对象提供了`formatCurrency` 、`arrayFormatCurrency`、 `listFormatCurrency`、 `setFormatCurrency`等四个方法用来格式化数字，将它们转换成货币格式的字符串。

## 2.4.4 百分比格式化

百分比格式化与小数格式化类似，调用`formatPercent`方法对数字进行百分比格式化，需要传入数字、整数最少位数、保留的小数位数三个参数。除了这个方法之外，还有处理数组、List、Set的方法。

## 2.4.5 算术运算符

Thyme Leaf标准表达式还⽀持⼀些算术运算：+， - ，*，/和％。

```
<div th:with="isEven=(${prodStat.count} % 2 == 0)"></div>
```

# 2.5 字符串对象: #strings

&emsp;&emsp;String⼯具类。Thymeleaf主要使用org.thymeleaf.expression.Strings这个类来处理字符串，⽂本⽂字只是包含在单引号之间的字符串。它们可以包含任何字符，但是应该使⽤\'转义其中的任何单引号。

## 2.5.1 toString
## 2.5.2 length
## 2.5.3 非空判断与默认值处理

- 布尔字⾯量

布尔字⾯量包含true和false。 例如：

```
<div th:if="${user.isAdmin()} == false"> ...</div>
```
在这个例⼦中，== false被写在⼤括号之外，所以它是Thymeleaf来处理的。如果它是写在⼤括号内，那将是OGNL / SpringEL引擎的责任：

```
<div th:if="${user.isAdmin() == false}"> ...</div>
```

- NULL 字⾯量

Thyme Leaf标准表达式语法中也可以使⽤null字⾯量：

```
<div th:if="${variable.something} == null"> ...</div>
```

## 2.5.4 包含
## 2.5.5 截取与替换
## 2.5.6 追加与拼接

- 追加⽂本

⽆论是字符串⽂本常量，还是通过变量表达式或消息表达式计算的结果，都可以使⽤+运算符轻松地追加⽂本：

```
<span th:text="'The name of the user is ' + ${user.name}"></span>
```

## 2.5.7 分割、连接、替换

- ⽂本替换

⽂本替换允许容易地格式化包含变量值的字符串，⽽不需要使 ⽤"..."+"..."附加⽂字，这些替换必须被垂直条（|）包围，如：

```
<span th:text="|Welcome to our application, ${user.name}!| "></span>
```

注意：

1. 只有变量/消息表达式（$ {...}，* {...}，＃{...}）才允许包含在|...|中来实现 ⽂本替换。 其他情况则不允许，如⽂本（'...'），布尔/数字令牌，条件表 达式等。

## 2.5.8 大小写切换
## 2.5.9 其他

# 2.6 日期对象: #dates、#calendars

- #dates：java.util.Date对象的实⽤程序⽅法
- #calendars：类似于#dates，但对于java.util.Calendar对象

## 2.6.1 格式化
## 2.6.2 获取日期字段
## 2.6.3 创建时间

# 2.7 数组对象: #arrays

# 2.8 集合对象: #lists、#sets、#maps

## 2.8.1 #Lists
## 2.8.2 #sets
## 2.8.3 #maps

# 2.9 其他

## #messages

&emsp;&emsp;⽤于在变量表达式中获取外部化消息的⼯具⽅法，与使⽤＃{...}语法获得的⽅式相同。

```
${#messages.msg('msgKey')} 
${#messages.msg('msgKey', param1)} 
${#messages.msg('msgKey', param1, param2)} 
${#messages.msg('msgKey', param1, param2, param3)} 
${#messages.msgWithParams('msgKey', new Object[] {param1, param2, param3, param4})} 
${#messages.arrayMsg(messageKeyArray)} 
${#messages.listMsg(messageKeyList)} 
${#messages.setMsg(messageKeySet)}
```

## #uris

&emsp;&emsp;⽤于在Thymeleaf标准表达式中执⾏URI/URL操作（尤其是转义/取消转义）的⼯具对象。

```
${#uris.escapePath(uri)}
${#uris.escapePath(uri, encoding)}
${#uris.unescapePath(uri)}
${#uris.unescapePath(uri, encoding)}

${#uris.escapePathSegment(uri)}
${#uris.escapePathSegment(uri, encoding)}
${#uris.unescapePathSegment(uri)}
${#uris.unescapePathSegment(uri, encoding)}

${#uris.escapeFragmentId(uri)}
${#uris.escapeFragmentId(uri, encoding)}
${#uris.unescapeFragmentId(uri)}
${#uris.unescapeFragmentId(uri, encoding)}

${#uris.escapeQueryParam(uri)}
${#uris.escapeQueryParam(uri, encoding)}
${#uris.unescapeQueryParam(uri)}
${#uris.unescapeQueryParam(uri, encoding)}
```

## #objects

## #bools

## #aggregates

## #ids

处理可能重复的id属性的实⽤⽅法，例如，作为迭代的结果。
