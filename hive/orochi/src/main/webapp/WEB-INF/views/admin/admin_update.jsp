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
                        <li class="active"><fmt:message key="breadcrumb.edit"/></li>
                    </ul>

                    <form id="form-update" action="${ctx}/admin/update" method="post" class="form-horizontal">
                        <input type="hidden" name="id" value="${admin.id}"/>
                        <fieldset>
                            <legend><small class="form-head"><fmt:message key="entity.admin"/></small></legend>
                            <div class="control-group">
                                <label class="control-label text-bold" for="name"><fmt:message key="prop.admin.name"/></label>
                                <div class="controls">
                                    <input type="text" id="name" name="name" value="${admin.name}" placeholder="" maxlength="63" readonly="true"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label text-bold" for="trueName"><fmt:message key="prop.truename"/></label>
                                <div class="controls">
                                    <input type="text" id="trueName" name="trueName" value="${admin.trueName}" placeholder="" maxlength="63"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label text-bold" for="password"><fmt:message key="prop.password"/></label>
                                <div class="controls">
                                    <input type="password" id="password" name="password" placeholder="" maxlength="20" rel="popover" data-content="<fmt:message key="para.password.strength"/>"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label text-bold" for="pwdCfm"><fmt:message key="prop.pwdCfm"/></label>
                                <div class="controls">
                                    <input type="password" id="pwdCfm" name="pwdCfm" placeholder="" maxlength="20"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label text-bold" for="phone"><fmt:message key="prop.phone"/></label>
                                <div class="controls">
                                    <input type="text" id="phone" name="phone" value="${admin.phone}" placeholder="" maxlength="20"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label text-bold" for="mobile"><fmt:message key="prop.mobile"/></label>
                                <div class="controls">
                                    <input type="text" id="mobile" name="mobile" value="${admin.mobile}" placeholder="" maxlength="20"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label text-bold" for="email"><fmt:message key="prop.email"/></label>
                                <div class="controls">
                                    <input type="text" id="email" name="email" value="${admin.email}" placeholder="" maxlength="20"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label text-bold" for="roleIds"><fmt:message key="prop.admin.role"/></label>
                                <div class="controls">
                                    <html:select id="roleIds" name="roleIds" items="${roles}" itemValue="id" itemLabel="name" selected="${admin.roleIds}"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label text-bold" for="description"><fmt:message key="prop.description"/></label>
                                <div class="controls">
                                    <textarea id="description" name="description" rows="5">${admin.description}</textarea>
                                </div>
                            </div>
                            <div class="form-actions form-foot">
                                <div class="btn-group">
                                    <input type="submit" class="btn" value="<fmt:message key="act.save"/>"/>
                                    <input type="reset" class="btn" value="<fmt:message key="act.reset"/>"/>
                                    <input type="button" class="btn" value="<fmt:message key="act.back"/>" onclick="back()"/>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
<%@ include file="../inc/footer.inc.jsp" %>