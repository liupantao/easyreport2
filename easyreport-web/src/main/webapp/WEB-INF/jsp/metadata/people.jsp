<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>用户管理</title>
    <%@ include file="/WEB-INF/jsp/includes/common.jsp" %>
    <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>
    <script src="${ctxPath}/assets/js/membership/people.js?v=${version}"></script>

</head>

<style type="text/css">
    .time-vertical {
        list-style-type: none;
        border-left: 1px solid #707070;
        padding: 10px;
        height: 400px;
    }

    .time-vertical li {
        height: 100px;
        position: relative;
    }

    .time-vertical li a {
        display: inline-block;
        margin-left: 20px;
        margin-top: 15px;
        text-decoration: none;
        color: #000;
    }

    .time-vertical li b:before {
        content: '';
        position: absolute;
        top: 15px;
        left: -12px;
        width: 18px;
        height: 18px;
        border: 2px solid #98FB98;
        border-radius: 10px;
        background: #98FB98;
    }

    .time-vertical li span {
        position: absolute;
        color: #fff;
        top: 18px;
        left: -6px;
    }

</style>

<body class="easyui-layout">
<div id="toolbar" class="toolbar">



    姓名: <input  name="name"  id="name"/>
    柜员号:<input  name="code"  id="code"/>
    机构号:<input  name="orgCode"  id="orgCode"/>
    身份证号:<input  name="card"  id="card"/>
    <a id="btn-search" href="#" class="easyui-linkbutton" iconCls="icon-search"> 搜索 </a>
    <input id="modal-action" type="hidden" name="action" value=""/>
</div>
<div style="height: 93%; padding: 2px">
    <div id="user-datagrid"></div>
</div>
<div id="user-dlg">



    <div class="container">
        <ul class="time-vertical" id="timeLine">
           <%-- <li><b></b><span>1</span><a href="javascript:void(0)">keso</a></li></a></li>
            <li><b></b><span>2</span><a href="javascript:void(0)">FlyElephant</a></li>
            <li><b></b><span>3</span><a href="javascript:void(0)">博客园</a></li>
            <li><b></b><span>4</span><a href="javascript:void(0)">创业</a></li>--%>
        </ul>
    </div>


</div>

</body>
</html>