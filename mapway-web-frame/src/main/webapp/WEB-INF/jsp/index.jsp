<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>解决方案</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px;
        }

        .c1 {
            text-align: center;
            font-weight: bold;
            font-size: 24px;
            position: absolute;
            left: 410px;
            top: 198px;
            color: black;
            text-decoration: none;

        }

        .c1:HOVER {
            color: blue;
        }

        .c2 {
            text-align: center;
            background-image: url("img/back.png");
            width: 905px;
            height: 409px;
            background-repeat: no-repeat;
            vertical-align: middle;
            margin: auto;
            position: relative;
        }

        .copy {
            height: 64px;
            background-color: #f0f0f0;
            text-align: center;
            position: fixed;
            bottom: 0px;
            width: 100%;
            line-height: 64px;
        }

        body {
            background-color: #f0f0f0;
            text-align: center;

        }
    </style>
</head>

<body>
<table width="100%" height="100%">
    <tr>
        <td valign="middle" align="center">
            <div class="c2">
                <a href="adminindex" class="c1">CIM-管理</a>
            </div>

        </td>
    </tr>
    <tr height="30px;">
        <td>
            <div class="copy">

                </span> <span><a href="doc/index">API文档</a></span>

            </div>
        </td>
    </tr>
</table>


</body>
</html>
                                                        