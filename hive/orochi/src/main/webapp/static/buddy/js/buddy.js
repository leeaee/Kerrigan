/**
 *  common methods need by web appllcation.
 */

$(document).ready(function()
{
    if ($('[rel=tooltip]').length > 0)
    {
        $('[rel="tooltip"]').tooltip({placement:'top'});
    }

    $(':input.sbtn, :input.mbtn, :button.sbtn, :button.mbtn').each(function()
    {
        $(this).attr('disabled', 'disabled');
    });
    updateButton();
});


submitForm = function (obj, uri)
{
    obj.action = uri;
    obj.submit();
};

confirmSubmit = function (obj, uri, method, msg)
{
    if (window.confirm(msg))
    {
        if (uri != null) obj.action = uri;
        obj.method = method;
        obj.submit();
    }
};

redirect = function(url)
{
    window.location.href = url;
};

submitUrl = function(url, value)
{
    window.location.href = url + '/' + value;
};

deleteSubmit = function(uri, obj, msg, value)
{
    if (window.confirm(msg))
    {
        obj.method = 'post';
        obj.action = uri;
        $('#deletes').val(value);
        obj.submit();
    }
};

postSubmit = function()
{

};

back = function ()
{
    window.history.back();
};

updateButton = function()
{
    var i = 0;

    $('table.data :checkbox').each(function()
    {
        if($(this).attr('checked')) i++;
    });

    $(':input.sbtn, :input.mbtn').each(function()
    {
        $(this).attr('disabled', 'disabled');
    });

    if(i > 0)
    {
        $(':input.mbtn').each(function()
        {
            $(this).removeAttr('disabled');
        });
    }

    if(i == 1)
    {
        $(':input.sbtn').each(function()
        {
            $(this).removeAttr('disabled');
        });
    }
};

getCheckedValues = function(name)
{
    var values = [];
    $(':checkbox[name="'+ name + '"][checked]').each(function(index)
    {
        values[index] = $(this).val();
    });
    return  values;
};
