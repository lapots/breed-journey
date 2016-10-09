package com.lapots.game.journey.ast.resource

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotatedNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import static org.codehaus.groovy.ast.tools.GeneralUtils.args

/**
 * Implementation of {@link InjectResource} transformation.
 */
@CompileStatic
@GroovyASTTransformation(phase=CompilePhase.SEMANTIC_ANALYSIS)
class InjectResourceASTTransformation implements ASTTransformation {

    private static final String DEFAULT_MEMBER = "value"

    @Override
    void visit(ASTNode[] nodes, SourceUnit source) {
        AnnotatedNode parent = (AnnotatedNode) nodes[1]
        AnnotationNode node = (AnnotationNode) nodes[0]

        if (parent in FieldNode) {
            def resource = node.getMember(DEFAULT_MEMBER).text
            final FieldNode fieldNode = (FieldNode) parent
            final ClassNode classNode = fieldNode.getOwner()
        }
    }

    private static MethodNode createLoadResourceMethod() {

    }
}
