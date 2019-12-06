<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../echarts/china.js" charset="UTF-8"></script>
    <script src="../js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
</head>

<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="userMap" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart1 = echarts.init(document.getElementById('userMap'));
    var option1 = {
        title: {
            text: '用户分布图',
            subtext: '数据来源绝对可靠',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['用户数']
        },
        visualMap: {
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: []
    };
    $.get('${pageContext.servletContext.contextPath}/user/getAddress',function (data) {
        myChart1.setOption({
            series: [{
                name: '用户数',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:data
            }]
        });
    },'json');
    // 使用刚指定的配置项和数据显示图表。
    myChart1.setOption(option1);
</script>
</body>
</html>