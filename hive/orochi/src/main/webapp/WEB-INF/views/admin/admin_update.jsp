<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="html" uri="http://www.modoop.com/html/tags" %>
<%@ include file="../inc/header.inc.jsp" %>
<%@ include file="../inc/sider.inc.jsp" %>

                <div class="span10">
                    <ul class="breadcrumb">
                        <li><a href="${ctx}">System</a> <span class="divider">/</span></li>
                        <li><a href="${ctx}/admin">Admin</a> <span class="divider">/</span></li>
                        <li class="active">Edit</li>
                    </ul>
                    <form id="adminCreateForm" action="${ctx}/admin/update" method="post" class="form-horizontal">
                        <input type="hidden" name="id" value="${admin.id}"/>
                        <fieldset>
                            <legend><small class="form-head">Administrator</small></legend>
                            <div class="control-group">
                                <label class="control-label" for="name">Admin Name</label>
                                <div class="controls">
                                    <input type="text" id="name" name="name" value="${admin.name}" placeholder="" maxlength="63" readonly="true"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="trueName">True Name</label>
                                <div class="controls">
                                    <input type="text" id="trueName" name="trueName" value="${admin.trueName}" placeholder="" maxlength="63"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="password">Password</label>
                                <div class="controls">
                                    <input type="password" id="password" name="password" placeholder="" maxlength="20"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="pwdCfm">Confirm Password</label>
                                <div class="controls">
                                    <input type="password" id="pwdCfm" name="pwdCfm" placeholder="" maxlength="20"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="phone">Phone</label>
                                <div class="controls">
                                    <input type="text" id="phone" name="phone" value="${admin.phone}" placeholder="" maxlength="20"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="mobile">Mobile</label>
                                <div class="controls">
                                    <input type="text" id="mobile" name="mobile" value="${admin.mobile}" placeholder="" maxlength="20"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="email">Email</label>
                                <div class="controls">
                                    <input type="text" id="email" name="email" value="${admin.email}" placeholder="" maxlength="20"/>
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
                                    <button type="submit" class="btn">Save</button>
                                    <input type="reset" class="btn" value="Reset"/>
                                    <input type="button" class="btn" value="Back" onclick="history.back()"/>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
<%@ include file="../inc/footer.inc.jsp" %>