package tfr.example.mapstruct.asm;

import org.objectweb.asm.AnnotationVisitor;

import static tfr.example.mapstruct.asm.ASMVersion.ASMversion;

public class MethodAnnotationValueScanner extends AnnotationVisitor {
	MethodAnnotationValueScanner() { super(ASMversion); }

	@Override
	public void visit(String name, Object value) {
		System.out.println("visitMethodAnnotationValue: " + name + " = " + value);
		super.visit(name, value);
	}
}