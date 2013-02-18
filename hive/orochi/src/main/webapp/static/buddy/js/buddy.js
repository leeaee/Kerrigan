/**
 *  common methods need by web appllcation.
 */

$(document).ready(function()
{
    initTooltips();
    initPopovers();
    updateButtons();
    validateForms();
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
 * name: checkbox name.
 */
submitUri = function(id, uri, name)
{
    var value = $('#' + id + ' :checkbox[name="'+ name + '"]:checked:first').val();
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
    if ($('table.data :checkbox').length > 0)
    {
        var i = $('table.data :checked').length;

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
    }
};


initTooltips = function()
{
    var tooltips = $('[rel="tooltip"]');
    if (tooltips.length > 0)
    {
        tooltips.tooltip({placement:'top'});
    }
};

initPopovers = function()
{
    var popovers = $('input[rel="popover"]');
    if (popovers.length > 0)
    {
        popovers.popover(
        {
            title: 'Tips',
            trigger: 'focus'
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


validateForms = function()
{
    var forms = $('form');
    if (forms.length > 0)
    {
        forms.each(function()
        {
            $(this).submit(function()
            {
                if(typeof($(this).attr('validate-uri')) == "undefined")
                {
                    return true;
                }

                var submit = false;
                var req = $.ajax
                ({
                    type: 'POST',
                    async: false,
                    url: $(this).attr('validate-uri'),
                    dataType: 'text',
                    data: $(this).serialize()
                });

                req.success(function()
                {
                    submit = true;
                });

                req.error(function(xhr/*, status, exception*/)
                {
                    var div = $('.alert');
                    var res = $.parseJSON(xhr.responseText);
                    $('.alert > p').remove();
                    div.append('<p>');
                    $.each(res.message, function(index, value)
                    {
                        $('.alert > p').append(value + '<br>');
                    });
                    div.show();
                });

                return submit;
            });
        })
    }
};

changeLang = function(newLang)
{
    var search = location.search;
    var url;

    if (search.length == 0)
    {
        url = "?lang=" + newLang;
    }
    else
    {
        if (search.indexOf("lang=") > -1)
        {
            var origin = search.substring(0, search.lastIndexOf("lang="));
            url = origin + "lang=" + newLang;
        }
        else
        {
            url = search + "&" + "lang=" + newLang;
        }
    }

    location.href = url;
};

