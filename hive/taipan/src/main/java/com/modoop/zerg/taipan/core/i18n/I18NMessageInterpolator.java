package com.modoop.zerg.taipan.core.i18n;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.validation.MessageInterpolator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class I18NMessageInterpolator implements MessageInterpolator, MessageSourceAware, InitializingBean
{
    private MessageSource messageSource;

    private static final Pattern MESSAGE_PARAMETER_PATTERN = Pattern.compile("(\\{[^\\}]+?\\})");

    private final ConcurrentMap<LocalisedMessage, String> resolvedMessages = new ConcurrentHashMap<LocalisedMessage, String>();

    private final boolean cacheMessages;

    public I18NMessageInterpolator()
    {
        // Not cache the message to keep same message-key with different rules can display correctly.
        this(false);
    }

    public I18NMessageInterpolator(boolean cacheMessages)
    {
        this.cacheMessages = cacheMessages;
    }


    @Override
    public String interpolate(String message, Context context)
    {
        return interpolateMessage(message, context.getConstraintDescriptor().getAttributes(), getLocale());
    }

    @Override
    public String interpolate(String message, Context context, Locale locale)
    {
        return interpolateMessage(message, context.getConstraintDescriptor().getAttributes(), locale);
    }

    @Override
    public void setMessageSource(MessageSource messageSource)
    {
        this.messageSource = messageSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        if (messageSource == null)
        {
            throw new IllegalStateException("MessageSource was not injected, could not initialize " + this.getClass().getSimpleName());
        }
    }

    private String interpolateMessage(String message, Map<String, Object> annotationParameters, Locale locale)
    {
        LocalisedMessage localisedMessage = new LocalisedMessage(message, locale);
        String resolvedMessage = null;

        if (cacheMessages)
        {
            resolvedMessage = resolvedMessages.get(localisedMessage);
        }

        // if the message is not already in the cache we have to run step 1-3 of the message resolution
        if (resolvedMessage == null)
        {
            resolvedMessage = I18NDictionary.getProtptypeMessage(message, locale);
            resolvedMessage = replaceAnnotationAttributes(resolvedMessage, annotationParameters);
        }

        // cache resolved message
        if (cacheMessages)
        {
            String cachedResolvedMessage = resolvedMessages.putIfAbsent(localisedMessage, resolvedMessage);
            if (cachedResolvedMessage != null)
            {
                resolvedMessage = cachedResolvedMessage;
            }
        }

        return resolvedMessage;
    }

    private String replaceAnnotationAttributes(String message, Map<String, Object> annotationParameters)
    {
        Matcher matcher = MESSAGE_PARAMETER_PATTERN.matcher(message);
        StringBuffer sb = new StringBuffer();
        while (matcher.find())
        {
            String resolvedParameterValue;
            String parameter = matcher.group(1);
            Object variable = annotationParameters.get(removeCurlyBrace(parameter));
            if (variable != null)
            {
                resolvedParameterValue = variable.toString();
            }
            else
            {
                resolvedParameterValue = parameter;
            }
            resolvedParameterValue = Matcher.quoteReplacement(resolvedParameterValue);
            matcher.appendReplacement(sb, resolvedParameterValue);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private String removeCurlyBrace(String parameter)
    {
        return parameter.substring(1, parameter.length() - 1);
    }

    private static Locale getLocale()
    {
        return LocaleContextHolder.getLocale();
    }

    private static class LocalisedMessage
    {
        private final String message;
        private final Locale locale;

        LocalisedMessage(String message, Locale locale)
        {
            this.message = message;
            this.locale = locale;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }

            LocalisedMessage that = (LocalisedMessage) o;

            if (locale != null ? !locale.equals(that.locale) : that.locale != null)
            {
                return false;
            }
            if (message != null ? !message.equals(that.message) : that.message != null)
            {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode()
        {
            int result = message != null ? message.hashCode() : 0;
            result = 31 * result + (locale != null ? locale.hashCode() : 0);
            return result;
        }
    }

}
