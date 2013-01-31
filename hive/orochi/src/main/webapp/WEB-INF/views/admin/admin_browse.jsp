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
                        <input id="deletes" name="names" type="hidden" value=""/>
                        <shiro:hasPermission name="admin:change">
                        <div class="btn-group right-float">
                            <input type="button" id="edit" value="Edit" class="btn sbtn" onclick="submitUrl('${ctx}/admin/update', getCheckedValues('name')[0])"/>
                            <input type="button" id="delete" value="Delete" class="btn mbtn" onclick="deleteSubmit('${ctx}/admin/delete', this.form, 'Are you sure?', getCheckedValues('name'))"/>
                        </div>
                        </shiro:hasPermission>
                        <div class="input-append">
                            <input id="search_name" type="text" name="name" class="input" placeholder="Name" maxlength="31" value="${params.name}"/>
                            <input id="search" type="submit" class="btn" value="<fmt:message key="act.search"/>"/>
                        </div>
                    </form>
                    <table class="table table-bordered table-striped table-hover data">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Name</th>
                            <th>True Name</th>
                            <th>Phone</th>
                            <th>Mobile</th>
                            <th>Email</th>
                            <th>Create Time</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.result}" var="admin">
                            <tr>
                                <td><input type="checkbox" id="chk_${admin.id}" name="name" value="${admin.name}" onclick="updateButton()"/></td>
                                <td><a id="detail" href="${ctx}/admin/detail/${admin.name}" rel="tooltip" title="View detail">${admin.name}</a></td>
                                <td>${admin.trueName}</td>
                                <td>${admin.phone}</td>
                                <td>${admin.mobile}</td>
                                <td>${admin.email}</td>
                                <td><html:joda pattern="yyyy-MM-dd" value="${admin.createTime}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <html:pagination name="pagination" page="${page}" cssClass="pagination pagination-right"/>
                </div>
<%@ include file="../inc/footer.inc.jsp" %>