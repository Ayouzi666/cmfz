<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
</head>
<body style="font-family: 'Microsoft YaHei'">
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">持名法舟后台系统</a>
            </div>
            <p class="navbar-text navbar-right">欢迎：******** <a href="#" class="navbar-link">退出登录</a></p>
        </div>
    </nav>
    <div class="alert alert-info alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>Welcome!</strong> 欢迎使用本持名法舟系统！
    </div>
    <div class="row">
        <div class="col-sm-2">
            <ul class="nav navbar-nav">
                <li class="active"><a href="javascript:$('body').load('test1.html');"><span class="glyphicon glyphicon-home"></span> 后台首页 </a></li>
                <li><a href="${pageContext.servletContext.contextPath}/jsp/user.jsp"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/jsp/teacher.jsp"><span class="glyphicon glyphicon-dashboard"></span> 上师管理</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/jsp/article.jsp"><span class="glyphicon glyphicon-list"></span> 文章管理</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/jsp/zhuanji.jsp"><span class="glyphicon glyphicon-film"></span> 专辑管理</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/jsp/picture.jsp"><span class="glyphicon glyphicon-picture"></span> 轮播图管理</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/jsp/map.jsp"><span class="glyphicon glyphicon-picture"></span> 全国用户分别图</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/jsp/echarts.jsp"><span class="glyphicon glyphicon-picture"></span> 用户注册时间统计</a></li>
            </ul>
        </div>
        <div class="col-sm-10" id="theload">
            <img src="../img/2013010716232447938.jpg" width="900px" height="550px">
        </div>
    </div>


</div>
</body>
</html>

