<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>可设置核心部分的看板娘模板</title>
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
    <script type="text/javascript">
        function change() {
            var i = document.getElementById('i');
            i.src='${pageContext.request.contextPath}/sc/sc?'+Math.random();
        }

    </script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">持名法舟后台系统</a>
            </div>
            <p class="navbar-text navbar-right">欢迎,请登录</p>
        </div>
    </nav>

    <div class="row">
        <div class="col-sm-8">
            <img src="../img/a8fb69593e.jpg" width="700px" height="400px">
        </div>
        <div class="col-sm-4" id="theload">
            ${error}
            <br> <br> <br> <br> <br>
            <form action="${pageContext.servletContext.contextPath}/admin/login" method="post">
                <div class="form-group">
                    <label>账号</label>
                    <input type="text" class="form-control" name="username" placeholder="账号" id="username">
                </div>
                <div class="form-group">
                    <label >密码</label>
                    <input type="password" class="form-control" name="password"  placeholder="密码">
                </div>
                <div class="form-group">
                    <label >验证码</label>
                    <img src="${pageContext.servletContext.contextPath}/sc/sc" id="i" onclick="change()">
                    <input type="text" class="form-control" name="sc" placeholder="密码">
                </div>
                <button type="submit" class="btn btn-default">登录</button>
            </form>

        </div>
    </div>


</div>




</body>
</html>

