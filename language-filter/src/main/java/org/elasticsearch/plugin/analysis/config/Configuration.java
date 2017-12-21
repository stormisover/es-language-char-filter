package org.elasticsearch.plugin.analysis.config;

import org.apache.logging.log4j.Logger;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.logging.ESLoggerFactory;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.plugin.analysis.exception.EmptyFilterLanguageException;
import org.elasticsearch.plugin.analysis.exception.NoSuchFilterLanguageException;

public class Configuration {

    private static final Logger logger = ESLoggerFactory.getLogger(Configuration.class.getName());

    private FilterLocale filterLocale;

    @Inject
    public Configuration(Environment environment, Settings settings) {
        try {
            logger.debug("language settings: " + settings.get("lang"));
            this.filterLocale = FilterLocale.valueOf(settings.get("lang").toLowerCase());
        }
        catch (NullPointerException ex) {
            logger.error("No filter language assigned.", ex);
            throw new EmptyFilterLanguageException("No filter language assigned.");
        }
        catch (IllegalArgumentException ex) {
            logger.error("This language is not supported {}.", settings.get("lang"), ex);
            throw new NoSuchFilterLanguageException("Not support language " + settings.get("lang"));
        }
    }

    public FilterLocale getFilterLocale() {
        return filterLocale;
    }

    public void setFilterLocale(FilterLocale filterLocale) {
        this.filterLocale = filterLocale;
    }
}
