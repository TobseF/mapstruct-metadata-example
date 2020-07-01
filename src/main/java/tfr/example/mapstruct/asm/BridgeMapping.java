package tfr.example.mapstruct.asm;


/**
 * Maps a business-model with ui-model in a bidirectional way. With the help of
 * <a href="https://mapstruct.org/">MapStruct.org/</a> mapping implementations can be generated.
 */
public interface BridgeMapping<BusinessModel, UiModel> {

	BusinessModel mapToBusinessModel(UiModel businessModel);

	BusinessModel mapToBusinessModel(UiModel uiModel, BusinessModel instance);

	UiModel mapToUIModel(BusinessModel businessModel);

}
