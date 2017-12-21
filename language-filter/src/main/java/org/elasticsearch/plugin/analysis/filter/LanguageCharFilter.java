package org.elasticsearch.plugin.analysis.filter;

import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.charfilter.BaseCharFilter;
import org.elasticsearch.common.logging.ESLoggerFactory;
import org.elasticsearch.plugin.analysis.config.language.FilterStrategy;
import org.elasticsearch.plugin.analysis.config.language.FilterStrategyFactory;
import org.elasticsearch.plugin.analysis.config.Configuration;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class LanguageCharFilter extends BaseCharFilter {

    private static final Logger logger = ESLoggerFactory.getLogger(LanguageCharFilter.class.getName());

    private Reader transformedInput;
    private FilterStrategy filterStrategy;

    public LanguageCharFilter(Reader in, Configuration config) {
        super(in);
        filterStrategy = FilterStrategyFactory.getConcreteStrategy(config);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if (transformedInput == null) {
            fill();
        }

        return transformedInput.read(cbuf, off, len);
    }

    private void fill() throws IOException {
        StringBuilder buffered = new StringBuilder();
        char [] temp = new char [1024];
        for (int cnt = input.read(temp); cnt > 0; cnt = input.read(temp)) {
            buffered.append(temp, 0, cnt);
        }
        transformedInput = new StringReader(filterLanguage(buffered).toString());
    }

    private CharSequence filterLanguage(CharSequence input) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] charArray = input.toString().toCharArray();

        for(char ch : charArray) {
            if(filterStrategy.filter(ch)) {
                logger.debug("Filtered Character Found: {}", ch);
                continue;
            }
            stringBuilder.append(ch);
        }

        return stringBuilder;
    }
}