package org.elasticsearch.plugin.analysis.config.language;

public interface FilterStrategy {
    boolean filter(Character ch);
}
