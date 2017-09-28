1.在html页面中引入thymeleaf命名空间，即<htmlxmlns:th=http://www.thymeleaf.org></html>，
此时在html模板文件中动态的属性使用th:命名空间修饰 
2.引用静态资源文件，比如CSS和JS文件，语法格式为“@{}”，如@{/js/blog/blog.js}会引入/static目录下的/js/blog/blog.js文件 
3.访问spring-mvc中model的属性，语法格式为“${}”，如${user.id}可以获取model里的user对象的id属性 
4.循环，在html的标签中，加入th:each=“value:${list}”形式的属性，如<span th:each=”user:${users}”></span>可以迭代users的数据 
5、判断，在html标签中，加入th:if=”表达式”可以根据条件显示html元素 
<span th:if="${not #lists.isEmpty(blog.publishTime)}"> 
<span id="publishtime" th:text="${#dates.format(blog.publishTime, 'yyyy-MM-dd HH:mm:ss')}"></span> 
</span> 
以上代码表示若blog.publishTime时间不为空，则显示时间 
6、时间的格式化， 
${#dates.format(blog.publishTime,'yyyy-MM-dd HH:mm:ss')} 
表示将时间格式化为”yyyy-MM-dd HH:mm:ss”格式化写法与Java格式化Date的写法是一致的。 

7、字符串拼接，有两种形式 
比如拼接这样一个URL:/blog/delete/{blogId} 
第一种：th:href="'/blog/delete/' + ${blog.id }" 
第二种：th:href="${'/blog/delete/' + blog.id }" 