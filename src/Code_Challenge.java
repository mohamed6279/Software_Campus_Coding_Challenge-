import java.io.IOException;
import org.json.JSONException;
import org.json.simple.parser.ParseException;

public class Code_Challenge {
	public static void main(String[] args) throws IOException, ParseException, JSONException {
		Read_ReST_API read_rest_api = new Read_ReST_API(); // Open object from Read_ReST_API Class
		Analyze_Data_Write_JSON analyze_data_write_JSON = new Analyze_Data_Write_JSON(); // oben object from Analyze_Data_Write_JSON class
		String document = "";
		String category = "";
		document = read_rest_api.read_document(); // Read document ReST API
		category = read_rest_api.read_category(); // Read category ReST API
		analyze_data_write_JSON.Analyze_Data_Write_Into_JSON(document, category); // Call function to analyze data and write the output in JSON file
	}
}
