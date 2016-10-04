package com.lapots.game.journey.ast.managed

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotatedNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import static org.codehaus.groovy.ast.tools.GeneralUtils.callX
import static org.codehaus.groovy.ast.tools.GeneralUtils.args

import java.beans.Expression

import static org.codehaus.groovy.ast.tools.GeneralUtils.varX

/**
 * Implementation of {@link Managed} transformation.
 */
@CompileStatic
@GroovyASTTransformation(phase=CompilePhase.SEMANTIC_ANALYSIS)
class ManagedASTTransformation implements ASTTransformation {

    private static final String DEFAULT_MEMBER = "value"
    private static final String MANAGED_STORAGE = "com.lapots.game.journey.core.platform.ManagedPlatform"

    @Override
    void visit(ASTNode[] nodes, SourceUnit source) {
        AnnotatedNode parent = (AnnotatedNode) nodes[1]
        AnnotationNode node = (AnnotationNode) nodes[0]

        if (parent in FieldNode) {
            def beanName = node.getMember(DEFAULT_MEMBER).text
            final FieldNode fieldNode = (FieldNode) parent
            fieldNode.setInitialValueExpression(createInvocationExpression(beanName))
        }
    }

    private StaticMethodCallExpression createInvocationExpression(String bean) {
        def classNode = new ClassNode(Class.forName(MANAGED_STORAGE))
        return callX(classNode, "getAt", args(bean))
    }
}
