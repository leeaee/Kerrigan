<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../inc/header.inc.jsp" %>
<%@ include file="../inc/sider.inc.jsp" %>

                <div class="span10">
                    <ul class="breadcrumb">
                        <li><a href="${ctx}"><fmt:message key="breadcrumb.system"/></a> <span class="divider">/</span></li>
                        <li><a href="${ctx}/admin"><fmt:message key="breadcrumb.admin"/></a> <span class="divider">/</span></li>
                        <li class="active"><fmt:message key="breadcrumb.browse"/></li>
                    </ul>
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="javascript:void(0)"><fmt:message key="breadcrumb.browse"/></a>
                        </li>
                        <shiro:hasPermission name="admin:change">
                        <li>
                            <a id="create" href="${ctx}/admin/create/form"><fmt:message key="breadcrumb.create"/></a>
                        </li>
                        </shiro:hasPermission>
                    </ul>
                    <c:if test="${not empty message}">
                        <div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
                    </c:if>
                    <c:if test="${not empty error}">
                        <div id="error" class="alert alert-error"><button data-dismiss="alert" class="close">×</button>${error}</div>
                    </c:if>
                    <form id="form-search" action="${ctx}/admin/browse" method="get" class="form-inline">
                        <div class="input-append">
                            <input id="search_name" type="text" name="search_name" class="input-small" placeholder="Name" maxlength="31" value="${params.name}"/>
                            <input id="search" type="submit" value="<fmt:message key="act.search"/>" class="btn btn-primary"/>
                        </div>
                    </form>
                    <table class="table table-striped table-hover data">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>True Name</th>
                            <th>Phone</th>
                            <th>Mobile</th>
                            <th>Email</th>
                            <th>Create Time</th>
                            <th>Operate</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.result}" var="admin">
                            <tr>
                                <td>${admin.name}</td>
                                <td>${admin.trueName}</td>
                                <td>${admin.phone}</td>
                                <td>${admin.mobile}</td>
                                <td>${admin.email}</td>
                                <td><html:joda pattern="yyyy-MM-dd" value="${admin.createTime}"/></td>
                                <td>
                                    <a id="detail" href="${ctx}/admin/detail/${admin.name}" rel="tooltip" title="view"><i class="icon-share"></i></a>
                                    <shiro:hasPermission name="admin:change">
                                    <a id="edit" href="${ctx}/admin/update/${admin.name}" rel="tooltip" title="edit"><i class="icon-edit"></i></a>
                                    <a id="delete" href="javascript:confirmRedirect('${ctx}/admin/delete/${admin.name}', 'Are you sure?')" rel="tooltip" title="delete"><i class="icon-remove"></i></a>
                                    </shiro:hasPermission>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <%--<html:pagination name="downerNav" page="${page}" cssClass="btnbar"/>--%>
                </div>
<%@ include file="../inc/footer.inc.jsp" %>