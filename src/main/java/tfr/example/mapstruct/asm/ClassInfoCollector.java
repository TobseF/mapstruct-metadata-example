package tfr.example.mapstruct.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.List;

import static tfr.example.mapstruct.asm.ASMVersion.ASMversion;

public class ClassInfoCollector extends ClassVisitor {

	private String className;
	private final List<MethodAnnotationInfo> mapStructParser;

	public ClassInfoCollector(List<MethodAnnotationInfo> mapStructParser) {
		super(ASMversion);
		this.mapStructParser = mapStructParser;
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		className = name;
		super.visit(version, access, name, signature, superName, interfaces);
	}

	@Override
	public MethodVisitor visitMethod(int access, String methodName, String descriptor, String signature, String[] exceptions) {
		return new MethodInfoCollector(methodName, className, mapStructParser);
	}

}
