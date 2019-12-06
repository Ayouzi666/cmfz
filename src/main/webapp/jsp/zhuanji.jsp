<%--
  Created by IntelliJ IDEA.
  User: 杨宜楠
  Date: 2019/11/27
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
                url: "${pageContext.servletContext.contextPath}/zhuanji/getAll",//获取服务端数据url 注意获取结果要json
                datatype: "json",//预期服务器返回结果类型
                mtype: "post",//请求方式
                colNames: ["ID", "标题", "封面", "作者", "播音", "集数","简介","创建日期","星级"],//列名称数组
                colModel: [
                    {name: "id", align: 'center',hidden:true},//colModel中全部参数都写在列配置对象
                    {name: "tatle", editable: true, align: 'center'},
                    {
                        name: 'picture',
                        index: 'picture',
                        edittype: "file",
                        editable: true,
                        editoptions: {enctype: "multipart/form-data"},
                        formatter: function (value, option, rows) {
                            return "<img  style='width:30%;height:10s%;' src='${pageContext.request.contextPath}/img/" + rows.picture + "'/>";
                        }
                    },
                    {name: "author", editable: true},
                    {name: "boyin", editable: true},
                    {name: "jishu", editable: true},
                    {name: "jianjie", editable: true},
                    {name: "date", editable: true,formatter:"date",formatoptions:{scrformat:'Y-m-d H:i:s',newformat:'Y-m-d'}},
                    {name: "start", editable: true}
                ],//列数组值配置列对象
                pager: "#pager",//设置分页工具栏html
                // 注意: 1.一旦设置分页工具栏之后在根据指定url查询时自动向后台传递page(当前页) 和 rows(每页显示记录数)两个参数
                rowNum: 2,//这个代表每页显示记录数
                rowList: [2, 4, 6],//生成可以指定显示每页展示多少条下拉列表
                viewrecords: true,//显示总记录数
                caption: "图片列表",//表格标题
                cellEdit: false,//开启单元格编辑功能
                editurl: "${pageContext.servletContext.contextPath}/zhuanji/edit",//开启编辑时执行编辑操作的url路径  添加  修改  删除
                autowidth: true,//自适应外部容器
                height: 400,//指定表格高度
                subGrid:true,
                subGridRowExpanded : function(subgrid_id, row_id) {
                    addTable(subgrid_id,row_id);
                }
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
                                url: "${pageContext.request.contextPath}/zhuanji/upload",
                                fileElementId: "picture",
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
                                url: "${pageContext.request.contextPath}/zhuanji/upload",
                                fileElementId: "picture",
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
                    reloadAfterSubmit:true
                }
            );
        });
        function addTable(subgrid_id,row_id) {

            // 声明子表格|工具栏id
            var subgridTable = subgrid_id + "table";
            var subgridPage = subgrid_id + "page";
            // 根据下方空间id 创建表格及工具栏
            $("#"+subgrid_id).html("<table id='"+subgridTable+"'></table><div style='height: 50px' id='"+subgridPage+"'></div>")
            // 子表格JqGrid声明
            $("#"+subgridTable).jqGrid({
                url : "${pageContext.request.contextPath}/zhangjie/getAll?belong="+row_id,
                datatype : "json",
                colNames : [ 'id', '章节名','大小','时长','路径','所属专辑'],
                colModel : [
                    {name : "id",hidden:true},
                    {name : "name",editable:true},
                    {name : "size",editable:false},
                    {name : "time",editable:false},
                    {
                        name : "path",
                        edittype: "file",
                        editable: true,
                        editoptions: {enctype: "multipart/form-data"},
                        formatter:function (value,option,rows) {
                            return "<audio controls loop>\n" +
                                "  <source  src=${pageContext.servletContext.contextPath}/music/"+value+" type=\"audio/mpeg\">\n" +
                                "</audio>";
                        }
                    },
                    {name : "belong",editable:true,edittype:"select",editoptions:{
                            dataUrl:"${pageContext.servletContext.contextPath}/zhuanji/All"
                        }}
                ],
                pager : "#"+subgridPage,
                sortname : 'num',
                sortorder : "asc",
                height : '100%',
                styleUI:"Bootstrap",
                rowNum: 2,//这个代表每页显示记录数
                rowList: [2, 4, 6],//生成可以指定显示每页展示多少条下拉列表
                viewrecords: true,//显示总记录数
                caption: "图片列表",//表格标题
                cellEdit: false,//开启单元格编辑功能
                editurl: "${pageContext.servletContext.contextPath}/zhangjie/edit",//开启编辑时执行编辑操作的url路径  添加  修改  删除
                autowidth: true,//自适应外部容器
            });
            $("#" + subgridTable).jqGrid('navGrid',
                "#" + subgridPage, {
                    edit : true,
                    add : true,
                    del : true,
                    edittext:"编辑",
                    addtext:"添加",
                    deltext:"删除"
                },{
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
                                url: "${pageContext.request.contextPath}/zhangjie/upload",
                                fileElementId: "path",
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
                                url: "${pageContext.request.contextPath}/zhangjie/upload",
                                fileElementId: "path",
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
                    reloadAfterSubmit:true
                }
            );
        }
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
