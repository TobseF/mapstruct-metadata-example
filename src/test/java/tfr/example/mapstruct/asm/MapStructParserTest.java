package tfr.example.mapstruct.asm;

import org.junit.jupiter.api.Test;
import tfr.example.mapstruct.asm.mock.mapping.HouseMapper;
import tfr.example.mapstruct.asm.mock.model.House;
import tfr.example.mapstruct.asm.mock.uimodel.HouseUiModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapStructParserTest {

	@Test
	public void testBridgeMappingTypes() {
		MapStructParser mapStructParser = new MapStructParser();
		mapStructParser.parseMappingInterface(HouseMapper.class);
		assertEquals(House.class, mapStructParser.getSourceModel());
		assertEquals(HouseUiModel.class, mapStructParser.getTargetModel());
	}

	@Test
	public void testMapStructParser() {
		MapStructParser mapStructParser = new MapStructParser();
		mapStructParser.parseMappingInterface(HouseMapper.class);

		assertEquals("serial", mapStructParser.mapToSourceField("uniqueId"));
		assertEquals("housekeeperCompany.housekeeper.name", mapStructParser.mapToSourceField("keeperName"));

		assertEquals("uniqueId", mapStructParser.mapToTargetField("serial"));
		assertEquals("keeperName", mapStructParser.mapToTargetField("housekeeperCompany.housekeeper.name"));
	}

}
