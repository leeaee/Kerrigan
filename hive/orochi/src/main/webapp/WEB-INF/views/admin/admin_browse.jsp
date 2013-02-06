<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../inc/header.inc.jsp" %>
<%@ include file="../inc/sider.inc.jsp" %>

                <div class="span-content">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="javascript:void(0)"><fmt:message key="nav.admin"/></a>
                        </li>
                        <li>
                            <a href="${ctx}/admin/role"><fmt:message key="nav.role"/></a>
                        </li>
                    </ul>
                    <ul class="breadcrumb">
                        <li><a href="${ctx}"><fmt:message key="breadcrumb.system"/></a> <span class="divider">/</span></li>
                        <li><a href="${ctx}/admin"><fmt:message key="breadcrumb.admin"/></a> <span class="divider">/</span></li>
                        <li class="active"><fmt:message key="breadcrumb.browse"/></li>
                    </ul>

                    <html:message message="<%=(I18NMessage) request.getAttribute(Constants.MESSAGE)%>"/>

                    <fieldset>
                    <legend><small class="form-head">Administrator</small></legend>
                    <form id="form-search" action="${ctx}/admin/browse" method="get" class="form-search">
                        <div style="padding: 15px">
                            <table class="form" border="0">
                                <tr>
                                    <td>
                                        <label><fmt:message key="prop.name"/></label><br>
                                        <input type="text" class="input-block-level" name="name" value="${param.name}"/>
                                    </td>
                                    <td>
                                        <label><fmt:message key="prop.truename"/></label><br>
                                        <input type="text" class="input-block-level" name="trueName" value="${param.trueName}"/>
                                    </td>
                                    <td>
                                        <label><fmt:message key="prop.email"/></label><br>
                                        <input type="text" class="input-block-level" name="email" value="${param.email}"/>
                                    </td>
                                    <td>
                                        <label><fmt:message key="entity.role"/></label><br>
                                        <select class="input-block-level" id="roleIds" name="roleIds" size="1">
                                            <option value="1">Administrator</option>
                                            <option value="2">Guest</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" class="bottom"><input id="search" type="submit" class="btn btn-primary" value="<fmt:message key="act.search"/>"/></td>
                                </tr>
                            </table>
                        </div>
                    </form>

                    <table class="data-nav">
                        <tr>
                            <td>
                                <shiro:hasPermission name="admin:change">
                                <div class="btn-group">
                                    <input type="button" id="create" value="<fmt:message key="breadcrumb.create"/>" class="btn" onclick="redirect('${ctx}/admin/create/form')"/>
                                    <input type="button" id="edit" value="Edit" class="btn sbtn" onclick="submitUri('form-list', '${ctx}/admin/update', 'name')" disabled="disabled"/>
                                    <input type="button" id="delete" value="Delete" class="btn mbtn" data-toggle="modal" data-target="#confirmModal" disabled="disabled"/>
                                </div>
                                </shiro:hasPermission>
                            </td>
                            <td>
                                <html:pagination name="pagination" page="${page}" cssClass="pagination pagination-right" cssStyle="margin: 0 0 10px 0"/>
                            </td>
                        </tr>
                    </table>

                    <form id="form-list" action="" method="post">
                        <table class="table table-bordered table-striped table-hover data">
                            <thead>
                            <tr>
                                <th></th>
                                <th><fmt:message key="prop.name"/></th>
                                <th><fmt:message key="prop.truename"/></th>
                                <th>Phone</th>
                                <th>Mobile</th>
                                <th>Email</th>
                                <th>Create Time</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.result}" var="admin">
                                <tr>
                                    <td><input type="checkbox" id="chk_${admin.id}" name="name" value="${admin.name}" onclick="updateButtons()"/></td>
                                    <td><a id="detail" href="${ctx}/admin/detail/${admin.name}" rel="tooltip" title="View detail">${admin.name}</a></td>
                                    <td>${admin.trueName}</td>
                                    <td>${admin.phone}</td>
                                    <td>${admin.mobile}</td>
                                    <td>${admin.email}</td>
                                    <td><html:joda pattern="yyyy-MM-dd" locale="${loc}" value="${admin.createTime}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>

                    <table class="data-nav">
                        <tr>
                            <td>
                                <html:entryinfo name="pageinfo" page="${page}" locale="${loc}" cssClass="left"/>
                            </td>
                            <td>
                                <html:pagination name="pagination" page="${page}" cssClass="pagination pagination-right" cssStyle="margin: 0"/>
                            </td>
                        </tr>
                     </table>

                    </fieldset>
                </div>
                <shiro:hasPermission name="admin:change">
                <div id="confirmModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="confirmModalLabel">Confirm delete</h3>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure?</p>
                    </div>
                    <div class="modal-footer">
                        <input type="button" onclick="submitForm('form-list', '${ctx}/admin/delete', 'post')" value="OK" class="btn btn-primary"/>
                        <input type="button" class="btn" data-dismiss="modal" aria-hidden="true" value="Close"/>
                    </div>
                </div>
                </shiro:hasPermission>
<%@ include file="../inc/footer.inc.jsp" %>