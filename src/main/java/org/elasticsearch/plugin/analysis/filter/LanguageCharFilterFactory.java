package org.elasticsearch.plugin.analysis.filter;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractCharFilterFactory;
import org.elasticsearch.plugin.analysis.config.LanguageCharFilterConfiguration;

import java.io.Reader;

public class LanguageCharFilterFactory extends AbstractCharFilterFactory {

    private Environment environment;
    private Settings settings;
    public static final String NAME = "language-char-filter";

    public static LanguageCharFilterFactory languageCharFilterFactoryInstance;

    public LanguageCharFilterFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name);
        this.environment = env;
        this.settings = settings;
    }

    public static LanguageCharFilterFactory getInstance(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        if(languageCharFilterFactoryInstance == null) {
            languageCharFilterFactoryInstance = new LanguageCharFilterFactory(indexSettings, env, name, settings);
        }
        return languageCharFilterFactoryInstance;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public Reader create(Reader tokenStream) {
        return new LanguageCharFilter(tokenStream, new LanguageCharFilterConfiguration(this.environment, this.settings));
    }
}
