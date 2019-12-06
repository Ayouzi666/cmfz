<%--
  Created by IntelliJ IDEA.
  User: MECHREVO
  Date: 2019/12/2
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <script type="text/javascript">
        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-e867852a68b24a99822912f9943c4080", //替换为您的应用appkey
        });
        goEasy.publish({
            channel: "cmfz", //替换为您自己的channel
            message: "Hello, GoEasy!" //替换为您想要发送的消息内容
        });
    </script>
</head>
<body>

</body>
</html>
