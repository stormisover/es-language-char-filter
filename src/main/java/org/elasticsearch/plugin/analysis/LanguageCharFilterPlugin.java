package org.elasticsearch.plugin.analysis;

import org.elasticsearch.index.analysis.CharFilterFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugin.analysis.filter.LanguageCharFilterFactory;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

public class LanguageCharFilterPlugin extends Plugin implements AnalysisPlugin {

    public static final String PLUGIN_NAME = "language-char-filter";

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<CharFilterFactory>> getCharFilters() {
        Map<String, AnalysisModule.AnalysisProvider<CharFilterFactory>> map = new HashMap<>();

        map.put("language_char_filter", LanguageCharFilterFactory::getInstance);
        return map;
    }

}
