<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="inc/header.simple.inc.jsp" %>

        <div id="content-login" class="container">
            <form class="form-signin" action="${ctx}/login" method="post">
                <legend><h3>Sign-in</h3></legend>
                <div class="control-group">
                    <label for="username" class="required">Username</label>
                    <div class="controls">
                        <input type="text" id="username" name="username" value="${username}" class="input-block-level" placeholder="Username"/>
                    </div>
                </div>
                <div class="control-group">
                    <label for="password" class="required">Password</label>
                    <div class="controls">
                        <input type="password" id="password" name="password" class="input-block-level" placeholder="Password"/>
                    </div>
                </div>
                <div class="control-group">
                    <button id="submit" class="btn btn-primary" type="submit">Sign in</button>
                    <label class="checkbox inline" for="rememberMe">
                        <input type="checkbox" id="rememberMe" name="rememberMe"/> Remember me
                    </label>
                </div>
            </form>
        </div>

<%@ include file="inc/footer.simple.inc.jsp" %>