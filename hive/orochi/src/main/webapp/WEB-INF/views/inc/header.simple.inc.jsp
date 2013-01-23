<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="html" uri="http://www.modoop.com/html/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Administrator Center</title>
    <meta name="viewport" content="width=1280"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link href="${ctx}/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="${ctx}/static/buddy/css/buddy.css" rel="stylesheet"/>
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/static/google/js/html5.js"></script>
    <![endif]-->
</head>

<body>

    <div id="wrap">
        <div class="navbar navbar-static-top">
            <div class="navbar-inner">
                <div class="container fixed-width">
                    <a class="brand" href="${ctx}">Admin Center</a>
                </div>
            </div>
        </div>