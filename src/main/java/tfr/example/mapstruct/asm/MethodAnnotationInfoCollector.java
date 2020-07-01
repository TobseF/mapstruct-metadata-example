package tfr.example.mapstruct.asm;

import org.mapstruct.Mapper;
import org.objectweb.asm.AnnotationVisitor;

import java.util.List;

import static tfr.example.mapstruct.asm.ASMVersion.ASMversion;

class MethodAnnotationInfoCollector extends AnnotationVisitor {

	private final String method;
	private final String annotationType;
	private final String className;
	private final List<MethodAnnotationInfo> mapStructParser;

	public MethodAnnotationInfoCollector(String method, String annotationType, String className, List<MethodAnnotationInfo> mapStructParser) {
		super(ASMversion);
		this.method = method;
		this.annotationType = annotationType;
		this.className = className;
		this.mapStructParser = mapStructParser;
	}

	@Override
	public void visit(String name, Object value) {
		System.out.println("visitMethodAnnotationValue: " + name + " = " + value);
		MethodAnnotationInfo annotationInfo = new MethodAnnotationInfo(method, className, annotationType, name, value.toString());
		System.out.println(annotationInfo);
		mapStructParser.add(annotationInfo);
		super.visit(name, value);
	}

	@Override
	public AnnotationVisitor visitAnnotation(String name, String descriptor) {
		if (Mapper.class.getSimpleName().equals(name)) {
			System.out.println(descriptor);
		}
		return new MethodAnnotationValueScanner();
	}

	@Override
	public AnnotationVisitor visitArray(String name) {
		return new MethodArrayAnnotationInfoCollector(method, annotationType, className, mapStructParser);
	}

	@Override
	public void visitEnum(String name, String descriptor, String value) {
		super.visitEnum(name, descriptor, value);
	}

}
