<%--
  Created by IntelliJ IDEA.
  User: MECHREVO
  Date: 2019/12/2
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="../echarts/echarts.min.js"></script>
    <script src="../boot/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <title>echarts</title>
</head>
<body>
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '用户注册时间统计'
        },
        tooltip: {},
        legend: {
            data: ['销量']
        },
        xAxis: {
            data: ["1天", "7天", "30天", "一年"]
        },
        yAxis: {},
        series: []
    };

    $.get('${pageContext.servletContext.contextPath}/user/get', function (data) {
        myChart.setOption({
            series: [{
                // 根据名字对应到相应的系列
                name: '男',
                type: 'bar',
                data: [
                    data.m1, data.m7, data.m30, data.m365
                ]
            }, {
                name: '女',
                type: 'bar',
                data: [
                    data.n1, data.n7, data.n30, data.n365
                ]
            }]
        });
    }, 'json')

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
<script type="text/javascript">
    var goEasy = new GoEasy({
        host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-e867852a68b24a99822912f9943c4080", //替换为您的应用appkey
    });
    goEasy.subscribe({
        channel: "cmfz", //替换为您自己的channel
        onMessage: function (message) {
            var i = JSON.parse(message.content)
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '男',
                    type: 'bar',
                    data: [
                        i.count.m1, i.count.m7, i.count.m30, i.count.m365
                    ]
                }, {
                    name: '女',
                    type: 'bar',
                    data: [
                        i.count.n1, i.count.n7, i.count.n30, i.count.n365
                    ]
                }]
            });
            console.log(message);
            console.log(JSON.parse(message.content));
            myChart.setOption(option);
        }
    });
</script>
</body>
</html>
