# MapStruct Meta Data Example
![BuildTool](https://img.shields.io/badge/BuildTool-Maven-blue.svg?style=flat&&logoColor=white)
![Java-1.8](https://img.shields.io/badge/Java-1.8-red.svg?style=flat&logo=Java&logoColor=white)
[![MapStruct](https://img.shields.io/badge/MapStruct-1.3.0-orange.svg?style=flat&logoColor=white)](https://mapstruct.org/)

Example setup that provides [MapStruct](https://mapstruct.org/) mapping info on runtime.
> What's the target field of a mapped source field?

### Why?
Because we use MapStruct for mappings between a _business-model_ and our _ui-model_. 
When a UI client want's to get sorted data, it can specify a field from the _ui-model_. 
Our `MapStructParser` can get the corresponding _business-model_ field-name and create the needed Criteria to sort it.

**Example**
``` java
class BusinessModel{
   String zip; 
}

class UiModel{
   String plz; 
}

public interface ModelMapping extends BridgeMapping<BusinessModel, UiModel> {
   @Mapping(source = "zip", target = "plz")
   UiModel modelToUiModel(BusinessModel model, @MappingTarget UiModel uiModel);
}

@Test
public testMappingInfo(){
   MapStructParser mappingInfo = new MapStructParser();
   mappingInfo.parseMappingInterface(ModelMapping.class);
   assertEquals("zip", mappingInfo.mapToTargetField("plz"));
}
```
The `mappingInfo.mapToTargetField("plz")` returns the mapped field of if the `BusinessModel` (`zip`).

👉 Full example: `tfr.example.mapstruct.asm.MapStructParserTest` 

### How?
The `Mapping` annotation from MapStruct uses `RetentionPolicy.CLASS`, so it's not possible to access
 it with reflections. This is why we use [ASM](https://asm.ow2.io/) (bytecode manipulation and analysis framework)
 to make them available at runtime.   
 The `AnnotationParser` is a general purpose annotation parser which provide a list of `MethodAnnotationInfo`.  
 The `MapStructParser` uses the `AnnotationParser` model to build a `MapStructMappingInfo` by collecting the `Mapping`-Annotations. 
