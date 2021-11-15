import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Read_ReST_API {
	private static HttpURLConnection connection;

	public String read_document() throws IOException { // Function to read all documents from ReST API and return all
														// categories as string

		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();

		try {
			URL url = new URL(
					"https://content-us-1.content-cms.com/api/06b21b25-591a-4dd3-a189-197363ea3d1f/delivery/v1/search?q=classification:content&fl=document:%5bjson%5d&fl=type&rows=100");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			int status = connection.getResponseCode();
			System.out.println("Document Response Status: " + status);

			if (status > 290) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();

			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
		} finally {
			connection.disconnect();
		}
		String read_document = responseContent.toString();
		return read_document; // Return all documents as String variable

	}

	public String read_category() throws IOException { // Function to read all categories from ReST API and return all
														// categories as string

		BufferedReader reader2;
		String line2;
		StringBuffer responseContent2 = new StringBuffer();

		try {

			URL url2 = new URL(
					"https://content-us-1.content-cms.com/api/06b21b25-591a-4dd3-a189-197363ea3d1f/delivery/v1/search?q=classification:category&rows=100");
			connection = (HttpURLConnection) url2.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			int status2 = connection.getResponseCode();
			System.out.println("Category Response Status: " + status2);

			if (status2 > 290) {
				reader2 = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line2 = reader2.readLine()) != null) {
					responseContent2.append(line2);
				}
				reader2.close();

			} else {
				reader2 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line2 = reader2.readLine()) != null) {
					responseContent2.append(line2);
				}
				reader2.close();
			}
		} finally {
			connection.disconnect();
		}

		String read_category = responseContent2.toString();
		return read_category; // Return all categories as String variable

	}

}
