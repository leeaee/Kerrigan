<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../inc/header.inc.jsp" %>
<%@ include file="../inc/sider.inc.jsp" %>

                <div class="span10">
                    <ul class="breadcrumb">
                        <li><a href="${ctx}">System</a> <span class="divider">/</span></li>
                        <li><a href="${ctx}/admin">Admin</a> <span class="divider">/</span></li>
                        <li class="active">Browse</li>
                    </ul>
                    <c:if test="${not empty message}">
                        <div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
                    </c:if>
                    <c:if test="${not empty error}">
                        <div id="error" class="alert alert-error"><button data-dismiss="alert" class="close">×</button>${error}</div>
                    </c:if>
                    <form id="adminListForm" name="adminListForm" action="${ctx}/admin/browse" method="get">
                        <div class="input-append">
                            <input id="search_name" type="text" name="search_name" class="input-small" placeholder="Name" maxlength="31" value="${params.name}"/>
                            <input type="submit" id="search" value="Search" class="btn"/>
                            <shiro:hasPermission name="admin:change">
                                <input type="button" id="create" value="Create" class="btn" onclick="redirect('${ctx}/admin/create/form', null)"/>
                                <input type="button" id="edit" value="Edit" class="btn sbtn" onclick="redirect('${ctx}/admin/update', getCheckedValues('name')[0])"/>
                                <input type="button" id="delete" value="Delete" class="btn mbtn" onclick="confirmSubmit(this.form, '${ctx}/admin/delete', 'post', 'Are you sure?')"/>
                            </shiro:hasPermission>
                            <input type="button" id="detail" value="Detail" class="btn sbtn" onclick="redirect('${ctx}/admin/detail', getCheckedValues('name')[0])"/>
                        </div>
                        <table class="table table-bordered table-striped table-hover data">
                            <caption>&nbsp;</caption>
                            <thead>
                                <tr>
                                    <th style="width: 20px;"></th>
                                    <th>Name</th>
                                    <th>True Name</th>
                                    <th>Phone</th>
                                    <th>Mobile</th>
                                    <th>Email</th>
                                    <th>Create Time</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.result}" var="admin" varStatus="vs">
                                <tr>
                                    <td><input type="checkbox" id="chk_${vs.index}" name="name" value="${admin.name}" onclick="updateButton()"/></td>
                                    <td>${admin.name}</td>
                                    <td>${admin.trueName}</td>
                                    <td>${admin.phone}</td>
                                    <td>${admin.mobile}</td>
                                    <td>${admin.email}</td>
                                    <td><html:joda pattern="yyyy-MM-dd" value="${admin.createTime}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
<%@ include file="../inc/footer.script.inc.jsp" %>