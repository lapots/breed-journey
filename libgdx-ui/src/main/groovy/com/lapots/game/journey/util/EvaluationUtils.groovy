package com.lapots.game.journey.util

/**
 * Temporary class as I separating UI from implementation.
 */
class EvaluationUtils {

    static evaluateWithBinding(code, variables) {
        Binding binding = new Binding()
        variables.each { k, v -> binding.setVariable(k, v) }
        new GroovyShell(binding).evaluate(code)
    }

    // evaluated against root
    static evaluateWithoutBinding(code, root) {
        if (null == code) return
        code = "{ -> $code }"
        def evaluated = new GroovyShell().evaluate(code)
        evaluated.delegate = root
        evaluated()
    }
}
