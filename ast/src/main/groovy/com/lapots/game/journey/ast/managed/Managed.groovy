package com.lapots.game.journey.ast.managed

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Transformation for spring-like injection for managed beans.
 *
 * Basically replaces
 *      def field = CorePlatform.managed["components"]
 * with
 *      @Managed("components")
 *      def field
 *
 * Potentially planning to extend it for support of custom
 * managed pools. For example
 *
 *      @Managed(context="CorePlatform", bean="components")
 *      def fieldA
 *
 *      @Managed(context="UiPlatform", bean="textInput")
 *      def fieldB
 */
@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.FIELD])
@GroovyASTTransformationClass(["com.lapots.game.journey.ast.managed.ManagedASTTransformation"])
public @interface Managed {
    String value()
}
