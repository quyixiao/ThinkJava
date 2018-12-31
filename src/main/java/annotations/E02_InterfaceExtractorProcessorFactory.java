//: annotations/E02_InterfaceExtractorProcessorFactory.java
// APT-based annotation processing.
/****************** Exercise 02 *****************
 * Add support for division to the interface
 * extractor.
 ***********************************************/
package annotations;

import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Annotated;

import java.util.*;

public class E02_InterfaceExtractorProcessorFactory
        implements AnnotationProcessorFactory {
    public AnnotationProcessor getProcessorFor(
            Set<AnnotationTypeDeclaration> atds,
            AnnotationProcessorEnvironment env) {
        return new InterfaceExtractorProcessor(env);
    }

    public Collection<String> supportedAnnotationTypes() {
        return
                Collections.singleton("annotations.ExtractInterface");
    }

    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }
} ///:~
//: annotations/Divisor.java
