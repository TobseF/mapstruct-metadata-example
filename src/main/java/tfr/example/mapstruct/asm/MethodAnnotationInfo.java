package tfr.example.mapstruct.asm;

import org.apache.commons.lang3.StringUtils;

public class MethodAnnotationInfo {
	private final String method;
	private final String className;
	private final String annotationValueName;
	private final String annotationType;
	private final String annotationValue;

	MethodAnnotationInfo(String method, String className, String annotationType, String annotationValueName, String annotationValue) {
		this.method = method;
		this.className = className;
		this.annotationValueName = annotationValueName;
		this.annotationType = annotationType;
		this.annotationValue = annotationValue;
	}

	public String getAnnotationType() {
		return annotationType;
	}

	public String getAnnotationTypeSimple() {
		return StringUtils.removeEnd(StringUtils.substringAfterLast(annotationType, "/"), ";");
	}

	public String getAnnotationValue() {
		return annotationValue;
	}

	public String getAnnotationValueName() {
		return annotationValueName;
	}

	public String getClassName() {
		return StringUtils.substringAfterLast(className, "/");
	}

	public String getMethod() {
		return method;
	}

	@Override
	public String toString() {
		return "MethodAnnotationInfo{" + "method='" + method + '\'' + ", className='" + className + '\'' + ", annotationValueName='" + annotationValueName
				+ '\'' + ", annotationType='" + annotationType + '\'' + ", annotationValue='" + annotationValue + '\'' + '}';
	}
}
