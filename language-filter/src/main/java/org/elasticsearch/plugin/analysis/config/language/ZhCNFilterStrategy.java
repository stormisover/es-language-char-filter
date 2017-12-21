package org.elasticsearch.plugin.analysis.config.language;

public class ZhCNFilterStrategy implements FilterStrategy {

    @Override
    public boolean filter(Character ch) {
        if(Character.UnicodeScript.HAN.equals(Character.UnicodeScript.of(ch))) {
            return true;
        }
        return false;
    }
}
