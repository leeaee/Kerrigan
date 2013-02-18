package com.modoop.zerg.taipan.core.web.controller;

import com.google.common.collect.Lists;
import com.modoop.zerg.taipan.core.constant.Constants;
import com.modoop.zerg.taipan.core.entity.ajax.AjaxResponse;
import com.modoop.zerg.taipan.core.i18n.I18NMessage;
import com.modoop.zerg.taipan.core.mapper.JsonMapper;
import com.modoop.zerg.taipan.core.validator.BeanValidators;
import com.modoop.zerg.taipan.core.web.exception.WebException;
import com.modoop.zerg.taipan.core.web.view.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Locale;

/**
 * @author: Genkyo Lee
 */
public abstract class AbstractController
{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ServletContext context;

    protected static JsonMapper jsonMapper = JsonMapper.buildNonNullMapper();


    /**
     * 处理国际化消息的显示.
     *
     * @param msg 需要显示的国际化消息
     */
    protected void handleMessage(RedirectAttributes redirectAttributes, I18NMessage msg)
    {
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, msg);
    }

    /**
     * 处理国际化消息的显示，并可指定一个在消息框中显示的按钮<code>button</code>.
     *
     * @param button 在消息框中显示的按钮
     */
    protected void handleMessage(RedirectAttributes redirectAttributes, I18NMessage msg, Button button)
    {
        List<Button> buttons = Lists.newArrayList();
        buttons.add(button);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE, msg);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_BUTTONS, buttons);
    }

    /**
     * 处理国际化消息的显示，并可指定一个在消息框中显示的按钮<code>button</code>.
     *
     * @param button 在消息框中显示的按钮
     */
    protected void handleMessage(RedirectAttributes redirectAttributes, I18NMessage msg, List<Button> buttons)
    {
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, msg);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE_BUTTONS, buttons);
    }


    @ExceptionHandler(WebException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleException(WebException exception, Locale locale)
    {
        String error = exception.getMessage(locale);
        AjaxResponse res = new AjaxResponse(false);
        res.getMessage().add(error);
        logger.warn(jsonMapper.toJson(res));
        return jsonMapper.toJson(res);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleException(ConstraintViolationException exception)
    {
        List<String> messages = BeanValidators.extractMessage(exception);
        AjaxResponse res = new AjaxResponse(false);
        res.setMessage(messages);
        logger.warn(jsonMapper.toJson(res));
        return jsonMapper.toJson(res);
    }
}
