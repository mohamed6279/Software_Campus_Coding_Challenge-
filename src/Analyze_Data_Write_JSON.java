import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Analyze_Data_Write_JSON {
	String unit_test = "";
	public void Analyze_Data_Write_Into_JSON(String document, String category) throws IOException { // Analyze and merge data from category ReST API to document Rest API and write the output in JSON file
		try (FileWriter filewriter = new FileWriter("My_Json")) {
			JSONObject obj_JSONObject = new JSONObject(document);
			JSONArray obj_JSoArray = obj_JSONObject.getJSONArray("documents");
			System.out.println("Document_NumFound :  " + obj_JSoArray.length()); // Print how many documents object in document ReST API
			JSONObject obj_JSONObject_Category = new JSONObject(category);
			JSONArray obj_JSoArray_Category = obj_JSONObject_Category.getJSONArray("documents");
			System.out.println("Category_NumFound :  " + obj_JSoArray_Category.length()); // Print how many documents object in document ReST API
			ArrayList<String> merge_data = new ArrayList<String>();
			for (int i = 0; i < obj_JSoArray.length(); i++) {
				JSONObject obj_JSONObject2 = obj_JSoArray.getJSONObject(i);
				JSONObject obj_JSONObject3 = obj_JSONObject2.getJSONObject("document");
				JSONObject obj_JSONObject4 = obj_JSONObject3.getJSONObject("elements");
				if (obj_JSONObject4.has("category")) { // Check if document object contains category
					JSONObject obj_JSONObject5 = obj_JSONObject4.getJSONObject("category");
					if (obj_JSONObject5.has("categoryIds")) { // Check if category inside document object contains category id
						JSONArray Categrory_ID = obj_JSONObject5.getJSONArray("categoryIds");
						String docuemnt_id = Categrory_ID.getString(0);// Get category id from category in document object
						for (int j = 0; j < obj_JSoArray_Category.length(); j++) {
							JSONObject obj_JSONObject_category2 = obj_JSoArray_Category.getJSONObject(j);
							String path = obj_JSONObject_category2.getString("path"); // Get category path from category ReST API 
							String category_id = obj_JSONObject_category2.getString("id"); // Get category id from category ReST API
							if (docuemnt_id.equalsIgnoreCase(category_id)) { // Check if category id in document Rest APi is equal category id in category ReST API
								merge_data.add(
										"'category':" + category_id + " " + "'path':" + path + " " + "'document':"); // Merge path in category from category ReST API into category in document ReST API
								obj_JSONObject5.put("Path", path);
								JSONObject merge = new JSONObject();
								merge.put("Category ID", category_id);
								merge.put("Path", path);
								break;
							} else {
								System.out.println("Docuemnt_Category_id is not equal Category_id");
							}
						}

					} else {
						System.out.println("No categoryIds");
					}
				} else {
					System.out.println("No category");
				}
			}

			Map<String, Integer> map = new HashMap<String, Integer>();
			for (int i = 0; i < merge_data.size(); i++) { // Remove duplicated category and count how many documents per category
				if (map.containsKey(merge_data.get(i))) {
					map.put(merge_data.get(i), map.get(merge_data.get(i)) + 1);
				} else {
					map.put(merge_data.get(i), 1);
				}
			}
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " " + entry.getValue()); // Print final list for category and path and how many documents per category
			}
			System.out.println("list size" + merge_data.size() + "  map size" + map.size());
			for (Map.Entry<String, Integer> entry : map.entrySet()) { // Write into JSON file category and path and how many documents per category
				filewriter.write("{");
				filewriter.write(entry.getKey() + " " + entry.getValue());
				filewriter.write("}");
				filewriter.write("\n");
				filewriter.flush();
			}
			unit_test = ("My Json File Generated Successfully");
			System.out.println(unit_test);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
