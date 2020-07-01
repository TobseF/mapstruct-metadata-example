package tfr.example.mapstruct.asm;

import org.objectweb.asm.AnnotationVisitor;

import java.util.List;

import static tfr.example.mapstruct.asm.ASMVersion.ASMversion;

class MethodArrayAnnotationInfoCollector extends AnnotationVisitor {

	private final String method;
	private final String descriptor;
	private final String className;
	private final List<MethodAnnotationInfo> mapStructParser;

	public MethodArrayAnnotationInfoCollector(String method, String descriptor, String className, List<MethodAnnotationInfo> mapStructParser) {
		super(ASMversion);
		this.method = method;
		this.descriptor = descriptor;
		this.className = className;
		this.mapStructParser = mapStructParser;
	}

	@Override
	public void visit(String name, Object value) {
		System.out.println("Annotation value [array]: name=" + name + " ,value=" + value);
		super.visit(name, value);
	}

	@Override
	public AnnotationVisitor visitAnnotation(String name, String descriptor) {
		System.out.println("Annotation: " + descriptor);
		return new MethodAnnotationInfoCollector(method, descriptor, className, mapStructParser);
	}

	@Override
	public AnnotationVisitor visitArray(String name) {
		System.out.println("Annotation [array]: name=" + name);
		return super.visitArray(name);
	}

	@Override
	public void visitEnum(String name, String descriptor, String value) {
		super.visitEnum(name, descriptor, value);
	}
}
