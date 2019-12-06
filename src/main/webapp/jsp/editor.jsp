<%--
  Created by IntelliJ IDEA.
  User: 杨宜楠
  Date: 2019/11/29
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="../boot/assets/waifu.min.css?v=1.4.2"/>

    <!--引入css bootstrap -->
    <link rel="stylesheet" href="../boot/css/bootstrap.min.css">
    <!--引入jqgrid的css-->
    <link rel="stylesheet" href="../boot/css/ui.jqgrid-bootstrap.css">
    <!--引入 jquery -->
    <script src="../boot/js/jquery-3.4.1.min.js"></script>
    <!--引入 jqgrid-->
    <script src="../boot/js/jquery.jqGrid.min.js"></script>
    <!--引入 jqgrid 国际化-->
    <script src="../boot/js/grid.locale-cn.js"></script>
    <!--引入 boot的js-->
    <script src="../boot/js/bootstrap.min.js"></script>
    <script src="../js/ajaxfileupload.js"></script>
    <script charset="utf-8" src="../editor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="../editor/lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function(K) {
            var options = {
                cssPath : '/css/index.css',
                filterMode : true,
                allowFileManager:true,
                uploadJson: "${pageContext.servletContext.contextPath}/edit/uploadImg"
            };
            var editor = K.create('textarea[name="content"]', options);
            window.editor = K.create('#editor_id');
        });
    </script>
</head>
<body>
<textarea id="editor_id" name="content" style="width:700px;height:300px;">
&lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>
</body>
</html>
