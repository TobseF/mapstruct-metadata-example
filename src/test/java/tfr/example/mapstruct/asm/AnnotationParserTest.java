package tfr.example.mapstruct.asm;

import org.junit.jupiter.api.Test;
import tfr.example.mapstruct.asm.mock.mapping.HouseMapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnnotationParserTest {

	@Test
	public void testAnnotationParser() {
		AnnotationParser mapStructParser = new AnnotationParser();
		mapStructParser.parse(HouseMapper.class);
		assertThat(mapStructParser.getMethodDescriptions(), hasItem("HouseMapper.modelToViewModel"));
	}


}
