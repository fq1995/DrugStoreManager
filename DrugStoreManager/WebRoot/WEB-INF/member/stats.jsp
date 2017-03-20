<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">
$(function() {
	var name = new Array();
	var integration = new Array();
	var memberLevel = new Array();
	$.ajax({
		 url: 'member_stats.action',
		 dataType: 'json',
		 success: function(data) {
			 data = $.parseJSON(data);
			 for(var i in data){
				 name.push(data[i].memberName);
				 integration.push(Number(data[i].integration));
				 memberLevel.push(Number(data[i].memberLevel));
			 } 

			 
			  // 路径配置
		        require.config({
		            paths: {
		                echarts: '${pageContext.request.contextPath}/js/echarts/build/dist'
		            }
		        });
		        
		        // 使用
		        require(
		            [
		                'echarts',
		                'echarts/chart/line', 
		                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
		                
		            ],
		            function (ec) {
		                // 基于准备好的dom，初始化echarts图表
		                var myChart = ec.init(document.getElementById('main')); 

		                var option = {
		                	title : {
		                		text: '会员积分排名',
		                	},
		                	tooltip: {
		                		trigger: 'axis',
		                        show: true
		                    },
		                    toolbox: {
		                        show : true,
		                        feature : {
		                            mark : {show: true},
		                            dataView : {show: true, readOnly: false},
		                            magicType : {show: true, type: ['line', 'bar']},
		                            restore : {show: true},
		                            saveAsImage : {show: true}
		                        }
		                    },
		                    calculable : true,
		                    legend: {
		                        data:['积分','等级']
		                    },
		                    xAxis : [
		                        {
		                            type : 'category',
		                            name : '会员',
		                            axisLabel : {
		                    			interval : 0,
		                    			rotate:45,
		                                margin:2,
		                    		},
		                            data : name
//		                            data : ["唐必呋","奉宫酒","胃必治","速效伤风胶囊","云南白药粉","炎痛喜康片","永龙去痛胶囊","灭滴灵片","阿昔洛韦软膏","地巴唑片"]
		                        }
		                    ],
		                    yAxis : [
		                        {
		                            type : 'value',
		                            
		                        }
		                    ],
		                    series : [
		                        {
		                            name : "积分",
		                            type : "bar",
		                            data : integration,
//		                            "data":[30,100,100,100,100,120,200,200,200,200],
		                             markPoint : {
		                                data : [
		                                    {type : 'max', name: '最大值'},
		                                    {type : 'min', name: '最小值'}
		                                ]
		                            },
		                            markLine : {
		                                data : [
		                                    {type : 'average', name: '平均值'}
		                                ]
		                            }
		                        },
		                        {
		                            "name":"等级",
		                            "type":"bar",
		                            "data":memberLevel,
//		                            "data":[30,100,100,100,100,120,200,200,200,200],
		                             markPoint : {
		                                data : [
		                                    {type : 'max', name: '最大值'},
		                                    {type : 'min', name: '最小值'}
		                                ]
		                            },
		                            markLine : {
		                                data : [
		                                    {type : 'average', name: '平均值'}
		                                ]
		                            }
		                        }
		                    ]
		                };
		        
		                // 为echarts对象加载数据 
		                myChart.setOption(option); 
		            }
		        );
			 
			 
			 
			 
		 }
	})
})
</script>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
     <script type="text/javascript">
     	
      
    </script>
</head>
<body>
	 <div id="main" style="height:500px"></div>
	 

</body>
</html>