<%@ page contentType="text/html;charset=UTF-8" language="java" %>
            </div>
        </div>

        <div id="push"></div>
    </div>

    <div id="footer">
        <p class="muted credit">&copy; 2012 Modoop</p>
    </div>

    <script type="text/javascript" src="${ctx}/static/jquery/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/buddy/js/buddy.js"></script>
    <script type="text/javascript">
        $(document).ready(function()
        {
            $(':input.sbtn, :input.mbtn, :button.sbtn, :button.mbtn').each(function()
            {
                $(this).attr('disabled', 'disabled');
            });
            updateButton();
        });
    </script>

</body>
</html>