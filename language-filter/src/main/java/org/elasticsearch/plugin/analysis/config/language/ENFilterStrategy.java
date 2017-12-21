package org.elasticsearch.plugin.analysis.config.language;

public class ENFilterStrategy implements FilterStrategy {

    @Override
    public boolean filter(Character ch) {
        if(Character.UnicodeScript.LATIN.equals(Character.UnicodeScript.of(ch))) {
            return true;
        }
        return false;
    }
}
