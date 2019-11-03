package config;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Locale;

/**
 * Translations provider.
 */
@Component
public class Translations {

    public String get(final String code) {
        try {
            return accessor.getMessage(code);
        } catch (NoSuchMessageException e) {
            return code;
        }
    }

    public String get(final String code, String param1) {
        try {
            return accessor.getMessage(code, new Object[]{param1});
        } catch (NoSuchMessageException e) {
            return code;
        }
    }

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, new Locale("pl"));
    }

    @Inject
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;
}