package tfr.example.mapstruct.asm;

import io.leangen.geantyref.GenericTypeReflector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mapstruct.Mapping;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Parses a {@link BridgeMapping} class and provides infor from the {@link Mapping} annotations.
 *
 * @see #mapToSourceField(String)
 * @see #mapToTargetField(String) (String)
 */
public class MapStructParser {

	private static final Log log = LogFactory.getLog(MapStructParser.class);
	private final static TypeVariable<? extends Class<?>> businessModelTypeVar = BridgeMapping.class.getTypeParameters()[0];
	private final static TypeVariable<? extends Class<?>> uiModelTypeVar = BridgeMapping.class.getTypeParameters()[1];
	@SuppressWarnings("rawtypes")
	private Class<? extends BridgeMapping> mapper;
	private Class<?> sourceModel;
	private Class<?> targetModel;
	private final AnnotationParser annotationParser = new AnnotationParser();

	private final Map<String, String> mapSourceToTarget = new HashMap<String, String>();
	private final Map<String, String> mapTargetToSource = new HashMap<String, String>();

	public List<MapStructMappingInfo> getMapStructMappingInfos() {
		return annotationParser.getAnnotationInfos()
							   .stream()
							   .collect(groupByClassNameAndMethod())
							   .entrySet()
							   .stream()
							   .flatMap(this::mapToMapStructMappingInfo)
							   .collect(toList());
	}

	@SuppressWarnings("rawtypes")
	public Class<? extends BridgeMapping> getMapper() {
		return mapper;
	}

	public Class<?> getSourceModel() {
		return sourceModel;
	}

	public Class<?> getTargetModel() {
		return targetModel;
	}

	/**
	 * @param targetField
	 * 		(UiModel) field name
	 * @return the field name of the source (BusinessModel) field
	 */
	public String mapToSourceField(String targetField) {
		return Optional.ofNullable(mapTargetToSource.get(targetField)).orElse(targetField);
	}

	/**
	 * @param sourceField
	 * 		(BusinessModel) field name
	 * @return the field name of the target (UiModel) field
	 */
	public String mapToTargetField(String sourceField) {
		return Optional.ofNullable(mapSourceToTarget.get(sourceField)).orElse(sourceField);
	}

	public void parseMappingImplementation(@SuppressWarnings("rawtypes") Class<? extends BridgeMapping> mapper) {
		//noinspection rawtypes,unchecked
		parseMappingInterface((Class<? extends BridgeMapping>) mapper.getInterfaces()[0]);
	}

	public void parseMappingInterface(@SuppressWarnings("rawtypes") Class<? extends BridgeMapping> mapper) {
		this.mapper = mapper;
		annotationParser.parse(mapper);
		readBridgeMappingTypeInfo(mapper);
		getMapStructMappingInfos().forEach(this::addMappingInfo);
	}

	private void addMappingInfo(MapStructMappingInfo mappingInfo) {
		mapSourceToTarget.put(mappingInfo.getSourceName(), mappingInfo.getTargetName());
		mapTargetToSource.put(mappingInfo.getTargetName(), mappingInfo.getSourceName());
	}

	private Collector<MethodAnnotationInfo, ?, Map<String, List<MethodAnnotationInfo>>> groupByClassNameAndMethod() {
		return groupingBy(it -> it.getClassName() + "#" + it.getMethod());
	}

	private Stream<MapStructMappingInfo> mapToMapStructMappingInfo(Map.Entry<String, List<MethodAnnotationInfo>> entry) {
		List<MethodAnnotationInfo> source = entry.getValue().stream().filter(it -> it.getAnnotationValueName().equals("source")).collect(toList());
		List<MethodAnnotationInfo> target = entry.getValue().stream().filter(it -> it.getAnnotationValueName().equals("target")).collect(toList());

		List<MapStructMappingInfo> mappingInfos = new ArrayList<>();
		if (source.size() != target.size()) {
			log.warn("Failed loading MapStructMappingInfo because not all sources are mapped to a target: " + entry.getValue());
			return Stream.empty();
		}
		for (int i = 0; i < source.size(); i++) {
			MethodAnnotationInfo sourceInfo = source.get(i);
			MethodAnnotationInfo targetInfo = target.get(i);
			mappingInfos.add(new MapStructMappingInfo(sourceInfo.getAnnotationValue(), targetInfo.getAnnotationValue()));
		}

		return mappingInfos.stream();
	}

	private void readBridgeMappingTypeInfo(@SuppressWarnings("rawtypes") Class<? extends BridgeMapping> mapper) {

		Type genericInterface = mapper.getGenericInterfaces()[0];
		Class<?> interfaceClass = GenericTypeReflector.erase(genericInterface);
		if (interfaceClass.equals(BridgeMapping.class)) {
			Type businessModelType = GenericTypeReflector.getTypeParameter(genericInterface, businessModelTypeVar);
			Type uiModelType = GenericTypeReflector.getTypeParameter(genericInterface, uiModelTypeVar);
			if (businessModelType != null) {
				sourceModel = GenericTypeReflector.erase(businessModelType);
			}
			if (uiModelType != null) {
				targetModel = GenericTypeReflector.erase(uiModelType);
			}
		}
		if (sourceModel == null || targetModel == null) {
			log.warn("Failed loading BridgeMapping generic types of: " + mapper.getCanonicalName());
		}
	}
}
