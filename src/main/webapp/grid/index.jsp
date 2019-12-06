<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<script>
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
                {name: "id", align: 'center'},//colModel中全部参数都写在列配置对象
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
            rowNum: 10,//这个代表每页显示记录数
            rowList: [5, 10, 20],//生成可以指定显示每页展示多少条下拉列表
            viewrecords: true,//显示总记录数
            caption: "图片列表",//表格标题
            cellEdit: false,//开启单元格编辑功能
            editurl: "${pageContext.servletContext.contextPath}/picture/edit",//开启编辑时执行编辑操作的url路径  添加  修改  删除
            autowidth: true,//自适应外部容器
            height: 370,//指定表格高度
        }).navGrid('#pager', {edit: true, add: true, del: true, search: false},

            {
                jqModal: true, closeAfterAdd: true, recreateForm: true, onInitializeForm: function (formid) {
                    $(formid).attr('method', 'POST');
                    $(formid).attr('action', '');
                    $(formid).attr('enctype', 'multipart/form-data');
                },
                afterSubmit: function (response) {
                    var status = response.responseJSON.status;
                    var id = response.responseJSON.message;
                    alert("确认修改")
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
                }
            },

            {
                jqModal: true, closeAfterEdit: true, recreateForm: true, onInitializeForm: function (formid) {
                    $(formid).attr('method', 'POST');
                    $(formid).attr('action', '');
                    $(formid).attr('enctype', 'multipart/form-data');
                },
                afterSubmit: function (response) {
                    var status = response.responseJSON.status;
                    var id = response.responseJSON.message;
                    alert("确认添加")
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
                }
            }
        );
    })
</script>

<!--作业: 1.通过假数剧玩jqgrid
2.写一个员工和部门的后台
-->
<!--创建表格-->
<table id="tt"></table>

<!--分页工具栏-->
<div id="pager" style="height: 50px"></div>