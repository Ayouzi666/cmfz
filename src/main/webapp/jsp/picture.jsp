<%--
  Created by IntelliJ IDEA.
  User: 杨宜楠
  Date: 2019/11/26
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
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
    <script src="../js/ajaxfileupload.js"></script>
    <script type="text/javascript">
        $(function () {

            //表格初始化
            $("#tt").jqGrid({
                //width:800,
                styleUI: "Bootstrap",//设置为bootstrap风格的表格
                url: "/cmfz/picture/getAll",//获取服务端数据url 注意获取结果要json
                datatype: "json",//预期服务器返回结果类型
                mtype: "post",//请求方式
                colNames: ["ID", "图片名", "地址", "简介", "网址", "状态"],//列名称数组
                colModel: [
                    {name: "id", align: 'center',hidden:true},//colModel中全部参数都写在列配置对象
                    {name: "name", editable: true, align: 'center'},
                    {
                        name: 'address',
                        index: 'address',
                        edittype: "file",
                        editable: true,
                        editoptions: {enctype: "multipart/form-data"},
                        formatter: function (value, option, rows) {
                            return "<img  style='width:30%;height:10s%;' src='${pageContext.request.contextPath}/img/" + rows.address + "'/>";
                        }
                    },
                    {name: "jianjie", editable: true},
                    {name: "link", editable: true},
                    {
                        name: "status", editable: true, edittype: "select",
                        editoptions: { //1.本地方式获取数据   //2.远程方式获取数据
                            value: "已使用:已使用;未使用:未使用"//本地数据
                            // "<select><option value="21">研发部</option></select>"//获取所有部门 远程数据获取的不是json数据 获取是html标签字符串
                        }, align: 'center'
                    }

                ],//列数组值配置列对象
                pager: "#pager",//设置分页工具栏html
                // 注意: 1.一旦设置分页工具栏之后在根据指定url查询时自动向后台传递page(当前页) 和 rows(每页显示记录数)两个参数
                rowNum: 2,//这个代表每页显示记录数
                rowList: [2, 4, 6],//生成可以指定显示每页展示多少条下拉列表
                viewrecords: true,//显示总记录数
                caption: "图片列表",//表格标题
                cellEdit: false,//开启单元格编辑功能
                editurl: "${pageContext.servletContext.contextPath}/picture/edit",//开启编辑时执行编辑操作的url路径  添加  修改  删除
                autowidth: true,//自适应外部容器
                height: 200,//指定表格高度
            }).navGrid('#pager', {edit: true, add: true, del: true, search: false,
                    edittext:"编辑",addtext:"添加",deltext:"删除"},
                {
                    jqModal: true, closeAfterEdit: true, recreateForm: true, onInitializeForm: function (formid) {
                        $(formid).attr('method', 'POST');
                        $(formid).attr('action', '');
                        $(formid).attr('enctype', 'multipart/form-data');
                    },
                    afterSubmit: function (response,postData) {
                        var status = response.responseJSON.status;
                        var id = response.responseJSON.pictureid;
                        window.alert("确认修改")
                        if (status) {
                            $.ajaxFileUpload({
                                url: "${pageContext.request.contextPath}/picture/upload",
                                fileElementId: "address",
                                data: {id: id},
                                type: "post",
                                success: function () {
                                    $("#tt").trigger("reloadGrid")
                                }
                            });
                        }
                        return postData;
                    }
                },

                {
                    jqModal: true, closeAfterAdd: true, recreateForm: true, onInitializeForm: function (formid) {
                        $(formid).attr('method', 'POST');
                        $(formid).attr('action', '');
                        $(formid).attr('enctype', 'multipart/form-data');
                    },
                    afterSubmit: function (response,postData) {
                        var status = response.responseJSON.status;
                        var id = response.responseJSON.pictureid;
                        window.alert("确认添加")
                        if (status) {
                            $.ajaxFileUpload({
                                url: "${pageContext.request.contextPath}/picture/upload",
                                fileElementId: "address",
                                data: {id: id},
                                type: "post",
                                success: function () {
                                    $("#tt").trigger("reloadGrid")
                                }
                            });
                        }
                        return postData;
                    }
                },{
                    recreateForm: true
                }
            );
        })
    </script>
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
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12" id="a">
            <!--创建表格-->
            <table id="tt"></table>

            <!--分页工具栏-->
            <div id="pager" style="height: 50px"></div>
        </div>
    </div>
</div>
</body>
</html>
