import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class Test_Analyze_Data_Write_JSON_File {

	@Test
	public void test() throws IOException {
		Read_ReST_API read_rest_api = new Read_ReST_API();
		Analyze_Data_Write_JSON analyze_data_write_JSON = new Analyze_Data_Write_JSON();
		String document = "";
		String category = "";
		document = read_rest_api.read_document();
		category = read_rest_api.read_category();
        analyze_data_write_JSON.Analyze_Data_Write_Into_JSON(document, category);
        String My_JSON = analyze_data_write_JSON.unit_test;
        assertEquals("My Json File Generated Successfully", My_JSON);
	}

}
