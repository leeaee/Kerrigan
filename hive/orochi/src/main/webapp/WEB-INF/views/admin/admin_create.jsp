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
                        <li class="active"><fmt:message key="breadcrumb.create"/></li>
                    </ul>

                    <div class="alert alert-block alert-error fade in" style="display: none">
                        <p><strong>Oh snap!</strong> <span></span></p>
                    </div>

                    <form id="form-create" action="${ctx}/admin/create" method="post" class="form-horizontal">
                        <fieldset>
                            <legend><small class="form-head">Administrator</small></legend>
                            <div class="control-group">
                                <label class="control-label" for="name">Admin Name</label>
                                <div class="controls">
                                    <input type="text" id="name" name="name" value="${admin.name}" maxlength="63"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="trueName">True Name</label>
                                <div class="controls">
                                    <input type="text" id="trueName" name="trueName" value="${admin.trueName}" maxlength="63"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="password">Password</label>
                                <div class="controls">
                                    <input type="password" id="password" name="password" value="" maxlength="20"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="pwdCfm">Confirm Password</label>
                                <div class="controls">
                                    <input type="password" id="pwdCfm" name="pwdCfm" value="" maxlength="20"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="phone">Phone</label>
                                <div class="controls">
                                    <input type="text" id="phone" name="phone" value="${admin.phone}" maxlength="20"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="mobile">Mobile</label>
                                <div class="controls">
                                    <input type="text" id="mobile" name="mobile" value="${admin.mobile}" maxlength="20"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="email">Email</label>
                                <div class="controls">
                                    <input type="text" id="email" name="email" value="${admin.email}" maxlength="20"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="roleIds">Admin Role</label>
                                <div class="controls">
                                    <html:select id="roleIds" name="roleIds" items="${roles}" itemValue="id" itemLabel="name" selected="${admin.roleIds}"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="description">Description</label>
                                <div class="controls">
                                    <textarea id="description" name="description" rows="5">${admin.description}</textarea>
                                </div>
                            </div>
                            <div class="form-actions form-foot">
                                <div class="btn-group">
                                    <input type="button" id="save" value="save" class="btn" onclick="validateForm('form-create', '${ctx}/admin/validate')"/>
                                    <input type="reset" class="btn" value="Reset"/>
                                    <input type="button" class="btn" value="Back" onclick="window.history.back()"/>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
<%@ include file="../inc/footer.inc.jsp" %>