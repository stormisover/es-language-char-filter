package org.elasticsearch.plugin.analysis.config;

import org.apache.logging.log4j.Logger;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.logging.ESLoggerFactory;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.plugin.analysis.exception.EmptyFilterLanguageException;
import org.elasticsearch.plugin.analysis.exception.NoSuchFilterLanguageException;
import org.elasticsearch.plugin.analysis.filter.LanguageCharFilter;

public class LanguageCharFilterConfiguration {

    private static final Logger logger = ESLoggerFactory.getLogger(LanguageCharFilter.class.getName());

    private FilterLocale filterLocale;

    @Inject
    public LanguageCharFilterConfiguration(Environment environment, Settings settings) {
        try {
            this.filterLocale = FilterLocale.valueOf(settings.get("lang"));
        }
        catch (NullPointerException ex) {
            logger.error("No filter language assigned.", ex);
            throw new EmptyFilterLanguageException();
        }
        catch (IllegalArgumentException ex) {
            logger.error("This language is not supported {}.", settings.get("lang"));
            throw new NoSuchFilterLanguageException();
        }
    }

    public FilterLocale getFilterLocale() {
        return filterLocale;
    }

    public void setFilterLocale(FilterLocale filterLocale) {
        this.filterLocale = filterLocale;
    }
}
