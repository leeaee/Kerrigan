<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="html" uri="http://www.modoop.com/html/tags" %>
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
                        <li class="active"><fmt:message key="breadcrumb.detail"/></li>
                    </ul>

                    <form id="form-detail" action="" method="post" class="form-horizontal">
                        <input type="hidden" name="name" value="${admin.name}" />
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
                                <label class="control-label">Last Modify</label>
                                <div class="controls align"><html:joda pattern="yyyy-MM-dd hh:mm" locale="${loc}" value="${admin.lastModify}"/></div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Create Time</label>
                                <div class="controls align"><html:joda pattern="yyyy-MM-dd hh:mm" locale="${loc}" value="${admin.createTime}"/></div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Description</label>
                                <div class="controls align">${admin.description}</div>
                            </div>
                            <div class="form-actions form-foot">
                                <div class="btn-group">
                                    <shiro:hasPermission name="admin:change">
                                    <input type="button" id="edit" value="Edit" class="btn" onclick="redirect('${ctx}/admin/update/${admin.name}')" />
                                    <input type="button" id="delete" value="Delete" class="btn" data-toggle="modal" data-target="#confirmModal"/>
                                    </shiro:hasPermission>
                                    <input type="button" class="btn" value="Back" onclick="back()"/>
                                </div>
                            </div>
                        </fieldset>
                    </form>
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
                            <input type="button" onclick="submitForm('form-detail', '${ctx}/admin/delete', 'post')" value="OK" class="btn btn-primary"/>
                            <input type="button" class="btn" data-dismiss="modal" aria-hidden="true" value="Close"/>
                        </div>
                    </div>
                    </shiro:hasPermission>
                </div>
<%@ include file="../inc/footer.inc.jsp" %>