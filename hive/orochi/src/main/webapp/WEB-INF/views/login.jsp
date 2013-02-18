<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="inc/header.simple.inc.jsp" %>

        <div id="content-login" class="container">
            <form class="form-signin" action="${ctx}/login" method="post">
                <legend><h3><fmt:message key="label.signin"/></h3></legend>
                <div class="control-group">
                    <label for="username" class="required"><fmt:message key="label.username"/></label>
                    <div class="controls">
                        <input type="text" id="username" name="username" value="${username}" class="input-block-level" placeholder="<fmt:message key="hold.username"/>"/>
                    </div>
                </div>
                <div class="control-group">
                    <label for="password" class="required"><fmt:message key="label.password"/></label>
                    <div class="controls">
                        <input type="password" id="password" name="password" class="input-block-level" placeholder="<fmt:message key="hold.password"/>"/>
                    </div>
                </div>
                <div class="control-group">
                    <button id="submit" class="btn btn-primary" type="submit"><fmt:message key="act.signin"/></button>
                    <label class="checkbox inline" for="rememberMe">
                        <input type="checkbox" id="rememberMe" name="rememberMe"/> <fmt:message key="label.remember"/>
                    </label>
                </div>
            </form>
        </div>

<%@ include file="inc/footer.simple.inc.jsp" %>