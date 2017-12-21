package org.elasticsearch.plugin.analysis.config.language;

import org.apache.logging.log4j.Logger;
import org.elasticsearch.common.logging.ESLoggerFactory;
import org.elasticsearch.plugin.analysis.config.FilterLocale;
import org.elasticsearch.plugin.analysis.config.Configuration;
import org.elasticsearch.plugin.analysis.exception.EmptyFilterLanguageException;

public class FilterStrategyFactory {

    private static final Logger logger = ESLoggerFactory.getLogger(FilterStrategyFactory.class.getName());

    public static FilterStrategy getConcreteStrategy(Configuration config) {
        if(FilterLocale.en.equals(config.getFilterLocale())) {
            logger.debug("EN filter!");
            return new ENFilterStrategy();
        }
        else if(FilterLocale.zh_cn.equals(config.getFilterLocale())) {
            logger.debug("Zh-CN filter!");
            return new ZhCNFilterStrategy();
        }

        throw new EmptyFilterLanguageException();
    }
}
