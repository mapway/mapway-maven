<%@page import="org.nutz.lang.random.R"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

    <%

String path = request.getContextPath();

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>幫助文檔</title>

<style type="text/css">
	#pictures
	{
		text-align: center;
		padding: 20px;
		width:200px;
		height:200px;
	}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/solution.js"></script>

<script type="text/javascript" src="widgets/widgets.nocache.js?r=<%=R.UU16() %>"></script>
<script type="text/javascript">

var box=null;
	//组件初始化完成
	function widgetInit()
	{
		enn.Configure.setImagePrefix("http://img.ennwifi.cn/");
	    console.log("inin called");
	    box=new enn.PictureBoxes();
	    box.appendTo("pictures");   
	}
	
	function cl()
	{
		window.alert(box.getValue());
	}
	
	function setT()
	{
		Solution.setTitle("Hello frpm web page");
	}
</script>
</head>

<body>

	

	<div id="pictures">
	
	</div>
	
	<button onclick="cl()">Get</button>
	
	<button onclick="setT()">Set title</button>
</body>

</html>