package tfr.example.mapstruct.asm;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import tfr.example.mapstruct.asm.mock.mapping.HouseMapper;
import tfr.example.mapstruct.asm.mock.model.House;
import tfr.example.mapstruct.asm.mock.model.Housekeeper;
import tfr.example.mapstruct.asm.mock.model.HousekeeperCompany;
import tfr.example.mapstruct.asm.mock.uimodel.HouseUiModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RoomMapperTest {

	@Test
	public void testBusinessModelToUiModel() {
		HouseMapper mapper = Mappers.getMapper(HouseMapper.class);

		House house = new House();
		house.setName("Big House");
		house.setSerial("UniqueId-42");
		HousekeeperCompany keeper = new HousekeeperCompany();
		Housekeeper housekeeper = new Housekeeper();
		keeper.setHousekeeper(housekeeper);
		housekeeper.setName("John Doe");
		house.setHousekeeperCompany(keeper);

		HouseUiModel houseUiModel = mapper.mapToUIModel(house);

		assertThat(houseUiModel.getName(), is("Big House"));
		assertThat(houseUiModel.getKeeperName(), is("John Doe"));
		assertThat(houseUiModel.getUniqueId(), is("UniqueId-42"));
	}

	@Test
	public void testMapUiModelToBusinessModel() {
		HouseMapper mapper = Mappers.getMapper(HouseMapper.class);
		HouseUiModel uiModel = new HouseUiModel();
		uiModel.setName("Big House");
		uiModel.setUniqueId("UniqueId-42");
		uiModel.setKeeperName("John Doe");

		House house = mapper.mapToBusinessModel(uiModel);

		assertThat(house.getName(), is("Big House"));
		assertThat(house.getUniqueId(), is("UniqueId-42"));
		assertThat(house.getHousekeeperCompany().getHousekeeper().getName(), is("John Doe"));
	}
}
