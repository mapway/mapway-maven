<%@page import="org.nutz.lang.random.R"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link  rel="stylesheet" href="css/ui.css">
	        
	<script type="text/javascript" src="monitor/monitor.nocache.js?r=<%=R.UU16() %>"></script>
	<title>CIM-泛能网络科技</title>
</head>
<body>
	 <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>
</body>
</html>