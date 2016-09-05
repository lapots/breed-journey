package com.lapots.game.journey.util

import com.lapots.game.journey.ui.dsl.RootDSL

class EvaluationUtils {

    static evaluateWithBinding(code, variables) {
        Binding binding = new Binding()
        variables.each { k, v -> binding.setVariable(k, v) }
        new GroovyShell(binding).evaluate(code)
    }

    // evaluated against root
    static evaluateWithoutBinding(code) {
        code = "{ -> $code }"
        def evaluated = new GroovyShell().evaluate(code)
        evaluated.delegate = new RootDSL()
        evaluated()
    }
}
