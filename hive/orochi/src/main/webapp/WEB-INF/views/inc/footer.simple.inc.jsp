<%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <div id="push"></div>
    </div>

    <div id="footer">
        <div class="container">
            <p class="muted credit">
                <span style="width: 800px" class="pull-left left">&copy; 2013 Modoop</span>
                <span class="pull-right right">
                    <select name="lang" style="font-size: 12px; height: 27px" onchange="changeLang(this.options[this.selectedIndex].value)">
                        <option value="en_US"<c:if test="${loc == 'en_US'}"> selected="selected"</c:if>>English (United States)</option>
                        <option value="zh_CN"<c:if test="${loc == 'zh_CN'}"> selected="selected"</c:if>>简体中文</option>
                        <option value="zh_TW"<c:if test="${loc == 'zh_TW'}"> selected="selected"</c:if>>繁體中文</option>
                    </select>
                </span>
            </p>
        </div>
    </div>

    <script type="text/javascript" src="${ctx}/static/jquery/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/buddy/js/buddy.js"></script>

</body>
</html>