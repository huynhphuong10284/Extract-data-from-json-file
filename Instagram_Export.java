package instagram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONObject;

public class Instagram_Export {

	public void JSON_Extract(String[] args) throws IOException, ParseException,
			Exception {
		String date;
		String relative_path;
		String publish_date;
		String url_hash;
		String avatar;
		int comment_no = 0;
		String content;
		String full_url;
		String image;
		int like_no = 0;
		int post_no = 0;
		String title;
		//Reach
		int follower_no = 0;
		int follows_no = 0;

		BufferedReader br = new BufferedReader(new FileReader(
				"D:\\magma\\magma\\dubai_instagram.json"));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			// System.out.println(everything);
			JSONObject obj = new JSONObject(everything).getJSONObject("hits");
			JSONArray post_data = obj.getJSONArray("hits");

			// System.out.println(post_data);

			for (int i = 0; i < post_data.length(); i++) {
				JSONObject c = post_data.getJSONObject(i);
				//For instagram_raw_daily_engagement
				full_url = c.getJSONObject("_source").getString("resource");
				url_hash = c.getJSONObject("_source").getString("hashcode");
				publish_date  = c.getJSONObject("_source").getString("last_updated").substring(0, 10);
				relative_path = c.getJSONObject("_source").getString("source_link");
				avatar = c.getJSONObject("_source").getString("source_image_src");
				content = c.getJSONObject("_source").getString("main");
				image = c.getJSONObject("_source").getString("image_src");
				like_no = Integer.parseInt(c.getJSONObject("_source").getString("likes"));
				comment_no = Integer.parseInt(c.getJSONObject("_source").getString("comments"));
				title = content;
				date = publish_date;
				System.out.println(publish_date);
				
				// For instagram_raw_daily_reach
				
				follower_no = Integer.parseInt(c.getJSONObject("_source").getString("source_followers"));
				follows_no = Integer.parseInt(c.getJSONObject("_source").getString("source_following"));
			}
		} finally {
			br.close();
		}
	}
}
