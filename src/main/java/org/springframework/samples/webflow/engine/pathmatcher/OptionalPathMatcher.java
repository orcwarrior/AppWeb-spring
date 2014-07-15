package org.springframework.samples.webflow.engine.pathmatcher;

/**
 * Created by orcwarrior on 2014-07-12.
 */

import org.springframework.util.AntPathMatcher;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.AntPathMatcher;

/**
 * Extends {@link AntPathMatcher} to introduce the feature of optional path
 * variables. It's supports request mappings like:
 * <p/>
 * <pre>
 *  @RequestMapping("/houses/[preview/][small/]{id}")
 * 	public String handlePreview(@PathVariable long id, @PathVariable("preview/") boolean preview, @PathVariable("small/") boolean small) {
 * 		...
 * 	}
 * </pre>
 */
public class OptionalPathMatcher extends AntPathMatcher {

    public static final String ESCAPE_BEGIN = "[";
    public static final String ESCAPE_END = "]";

    /**
     * stores a request mapping pattern and corresponding variable
     * configuration.
     */
    protected static class PatternVariant {

        private final String pattern;
        private Map variables;

        public Map getVariables() {
            return variables;
        }

        public PatternVariant(String pattern) {
            super();
            this.pattern = pattern;
        }

        public PatternVariant(PatternVariant parent, int startPos, int endPos, boolean include) {
            final String p = parent.getPattern();
            final String varName = p.substring(startPos + 1, endPos);
            this.pattern = p.substring(0, startPos) + (include ? varName : "") + p.substring(endPos + 1);

            this.variables = new HashMap();
            if (parent.getVariables() != null) {
                this.variables.putAll(parent.getVariables());
            }
            this.variables.put(varName, Boolean.toString(include));
        }

        public String getPattern() {
            return pattern;
        }
    }

    /**
     * here we use {@link AntPathMatcher#doMatch(String, String, boolean, Map)}
     * to do the real match against the
     * {@link #getPatternVariants(PatternVariant) calculated patters}. If
     * needed, template variables are set.
     */
    @Override
    protected boolean doMatch(String pattern, String path, boolean fullMatch, Map uriTemplateVariables) {
        for (PatternVariant patternVariant : getPatternVariants(new PatternVariant(pattern))) {
            if (super.doMatch(patternVariant.getPattern(), path, fullMatch, uriTemplateVariables)) {
                if (uriTemplateVariables != null && patternVariant.getVariables() != null) {
                    uriTemplateVariables.putAll(patternVariant.getVariables());
                }
                return true;
            }
        }

        return false;
    }

    /**
     * build recursicly all possible request pattern for the given request
     * pattern. For pattern: /houses/[preview/][small/]{id}, it
     * generates all combinations: /houses/preview/small/{id},
     * /houses/preview/{id} /houses/small/{id}
     * /houses/{id}
     */
    protected PatternVariant[] getPatternVariants(PatternVariant variant) {
        final String pattern = variant.getPattern();
        if (!pattern.contains(ESCAPE_BEGIN)) {
            return new PatternVariant[]{variant};
        } else {
            int startPos = pattern.indexOf(ESCAPE_BEGIN);
            int endPos = pattern.indexOf(ESCAPE_END, startPos + 1);
            PatternVariant[] withOptionalParam = getPatternVariants(new PatternVariant(variant, startPos, endPos, true));
            PatternVariant[] withOutOptionalParam = getPatternVariants(new PatternVariant(variant, startPos, endPos, false));
            return concat(withOptionalParam, withOutOptionalParam);
        }
    }

    /**
     * utility function for array concatenation
     */
    private static PatternVariant[] concat(PatternVariant[] A, PatternVariant[] B) {
        PatternVariant[] C = new PatternVariant[A.length + B.length];
        System.arraycopy(A, 0, C, 0, A.length);
        System.arraycopy(B, 0, C, A.length, B.length);
        return C;
    }
}