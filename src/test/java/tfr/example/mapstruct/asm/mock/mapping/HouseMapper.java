package tfr.example.mapstruct.asm.mock.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import tfr.example.mapstruct.asm.mock.model.House;
import tfr.example.mapstruct.asm.mock.uimodel.HouseUiModel;
import tfr.example.mapstruct.asm.BridgeMapping;
import tfr.example.mapstruct.asm.util.SerialMapper;

@Mapper(uses = SerialMapper.class)
public interface HouseMapper extends BridgeMapping<House, HouseUiModel> {

	@Override
	default House mapToBusinessModel(HouseUiModel businessModel) {
		return mapToBusinessModel(businessModel, new House());
	}

	@Override
	@InheritInverseConfiguration
	House mapToBusinessModel(HouseUiModel viewModel, @MappingTarget House model);

	@Override
	default HouseUiModel mapToUIModel(House House) {
		return modelToViewModel(House, new HouseUiModel());
	}

	@Mapping(source = "serial", target = "uniqueId")
	@Mapping(source = "housekeeperCompany.housekeeper.name", target = "keeperName")
	HouseUiModel modelToViewModel(House model, @MappingTarget HouseUiModel viewModel);

}
