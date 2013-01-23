<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="html" uri="http://www.modoop.com/html/tags" %>
<%@ include file="../inc/header.inc.jsp" %>
<%@ include file="../inc/sider.inc.jsp" %>

                <div class="span10">
                    <ul class="breadcrumb">
                        <li><a href="${ctx}">System</a> <span class="divider">/</span></li>
                        <li><a href="${ctx}/admin">Admin</a> <span class="divider">/</span></li>
                        <li class="active">Detail</li>
                    </ul>
                    <form id="adminCreateForm" action="${ctx}/admin/detail" method="post" class="form-horizontal">
                        <input type="hidden" name="name" value="${admin.name}"/>
                        <fieldset>
                            <legend><small class="form-head">Administrator</small></legend>
                            <div class="control-group">
                                <label class="control-label">Name</label>
                                <div class="controls align">${admin.name}</div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">True Name</label>
                                <div class="controls align">${admin.trueName}</div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Phone</label>
                                <div class="controls align">${admin.phone}</div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Mobile</label>
                                <div class="controls align">${admin.mobile}</div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Email</label>
                                <div class="controls align">${admin.email}</div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Admin Role</label>
                                <div class="controls align">${admin.roleNames}</div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Description</label>
                                <div class="controls align">${admin.description}</div>
                            </div>
                            <div class="form-actions form-foot">
                                <div class="btn-group">
                                    <shiro:hasPermission name="admin:change">
                                    <input type="button" id="edit" value="Edit" class="btn" onclick="redirect('${ctx}/admin/update', '${admin.name}')" />
                                    <input type="button" id="delete" value="Delete" class="btn" onclick="confirmSubmit(this.form, '${ctx}/admin/delete', 'post', 'Are you sure?')"/>
                                    </shiro:hasPermission>
                                    <input type="button" class="btn" value="Back" onclick="window.history.back()"/>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
<%@ include file="../inc/footer.inc.jsp" %>