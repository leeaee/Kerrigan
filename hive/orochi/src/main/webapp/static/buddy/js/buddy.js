/**
 *  common methods need by web appllcation.
 */

$(document).ready(function()
{
    if ($('[rel=tooltip]').length > 0)
    {
        $('[rel="tooltip"]').tooltip({placement:'top'});
    }

    updateButtons();
});


submitForm = function(id, uri, method)
{
    var form = $('#' + id);
    form.attr('action', uri);
    form.attr('method', method);
    form.submit();
};

/**
 * This method only allow submit one value to redirect url.
 * if more values are select, return first value to uri.
 *
 * id: target form id;
 * uri: submit uri;
 * name: element name.
 */
submitUri = function(id, uri, name)
{
    var value = $('#' + id + ' :checkbox[name="'+ name + '"][checked]:first').val();
    window.location.href = uri + '/' + value;
};

redirect = function(url)
{
    window.location.href = url;
};

back = function ()
{
    window.history.back();
};

updateButtons = function()
{
    var i = 0;

    $('table.data :checkbox').each(function()
    {
        if($(this).attr('checked')) i++;
    });

    if(i > 0)
    {
        $('.mbtn').each(function()
        {
            $(this).removeAttr('disabled');
        });
    }
    else
    {
        $('.mbtn').each(function()
        {
            $(this).attr('disabled', 'disabled');
        });
    }

    if(i == 1)
    {
        $('.sbtn').each(function()
        {
            $(this).removeAttr('disabled');
        });
    }
    else
    {
        $('.sbtn').each(function()
        {
            $(this).attr('disabled', 'disabled');
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
    return values;
};

validateForm = function(id, url)
{
    var req = $.ajax
    ({
        type: 'POST',
        async: false,
        url:  url,
        dataType: 'text',
        data: $('#' + id).serialize()
    });

    req.success(function()
    {
        $('#' + id).submit();
    });

    req.error(function (xhr/*, status, exception*/) {
        var res = $.parseJSON(xhr.responseText);
        $('.alert > p > span').html(res.message);
        $('.alert').show();
    });
};