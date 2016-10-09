package com.lapots.game.journey.ast.resource

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Annotation that allows resource injection.
 *
 * Basically it replaces code like this
 *
 *      def resource = loadResource("simplefile.txt")
 *      private static loadResource(String resource) {
 *          def classLoader = Thread.currentThread().getContextClassLoader()
 *          return new File(classLoader.getResource(resource).getFile())
 *      }
 *  with
 *      @InjectResponse("simplefile.txt")
 *      def resource
 *
 *  Also I aim to extend it for json and xml resource usage, that would allow me to replace
 *  facilities like
 *
 *      def jsonResource = new groovy.json.JsonSlurper().parse(loadResource("file.json"))
 *      def xmlResource = new XmlSlurper().parse(loadResource("file.xml"))
 *  with
 *      @JsonResource("file.json")
 *      def jsonResource
 *
 *      @XmlResource("file.xml")
 *      def xmlResource
 */
@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.FIELD])
@GroovyASTTransformationClass(["com.lapots.game.journey.ast.resource.ManagedASTTransformation"])
public @interface InjectResource {
    String value()
}
