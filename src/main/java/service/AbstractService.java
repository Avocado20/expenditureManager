package service;

import javax.inject.Inject;

/**
 * Service skeleton to provide error translations.
 *
 */
public abstract class AbstractService {

    @Inject
    protected Translations i18n;
}