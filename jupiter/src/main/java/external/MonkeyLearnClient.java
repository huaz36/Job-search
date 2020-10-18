package external;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.monkeylearn.ExtraParam;
import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;

public class MonkeyLearnClient {
	private static final String API_KEY = "dd72e9066d0ede348d246edba4084f05715cbe43";
	
	public static void main(String[] args) {
		
		String[] textList = {
				"<p>Humanizing Digital Experiences®</p>\n<p>Kasisto’s Digital Experience Platform, KAI, is designed for financial institutions to deliver the industry’s most amazing Conversational AI powered intelligent virtual assistants to their customers. KAI is open and extensible, and also fluent in the language of banking and finance. From simple retail transactions to the complex demands of corporate banks and wealth management, financial institutions can deliver meaningful digital interactions with KAI that help build their digital brand.</p>\n<p>Financial institutions around the world use KAI, including DBS Bank, J.P. Morgan, Mastercard, Standard Chartered, TD Bank, and Manulife Bank among others. They chose KAI for its proven track record to drive business results while improving customer experiences. The platform is used by millions of consumers around the world, all the time, across multiple channels, in different languages, and is optimized for performance, scalability, security, and compliance.</p>\n<p>This position</p>\n<p>We are looking for a Full-Stack, client side software engineer to help build and integrate responsive chat interfaces, analytics dashboards and reporting tools.</p>\n<p>What you’ll be doing</p>\n<p>Working closely with clients and internal engineering, product and design teams to gather requirements\nBuilding and integrating front-end applications with CSS, HTML, Javascript, jQuery, Vue.js, Webpack, Handlebars.js, LESS, Backbone, Python, Django and Java\nWorking to improve user experience and functionality for tools\nWriting testable code utilizing common front-end unit and BDD testing frameworks\nWhat you need for this position</p>\n<p>3+ years in client-side web development with CSS, HTML, Javascript and jQuery\nProven, full-stack front-end experience using Python and Django\nOther Modern Web Framework(s) experience is a plus (React, Vue, Angular, Ember)\nExperience working collaboratively to build scalable, modular, production software in an Agile environment\nExperience using RESTful json services\nNode.js and API development familiarity is plus\nD3.js is a plus</p>\n<p>What we offer:</p>\n<p>Competitive compensation package\nGround floor opportunity within rapidly growing tech startup\nGreat collaborative team environment\nFull Benefits\nFun perks</p>\n<p>Location - NYC, Flatiron District</p>\n<p>We welcome your cover letter with a description of your previous complete experience and your resume.  Applicants must be authorized to work in the US as we are unable to sponsor.  Kasisto is an equal opportunity employer.</p>\n"};
		List<List<String>> words = extractKeywords(textList);
		for (List<String> ws : words) {
			for (String w : ws) {
				System.out.println(w);
			}
			System.out.println();
		}
	}

	
	
	public static List<List<String>> extractKeywords(String[] text) {
		if (text == null || text.length == 0) {
			return new ArrayList<>();
		}

		// Use the API key from your account
		MonkeyLearn ml = new MonkeyLearn(API_KEY);

		// Use the keyword extractor
		ExtraParam[] extraParams = { new ExtraParam("max_keywords", "3") };
		MonkeyLearnResponse response;
		try {
			response = ml.extractors.extract("ex_YCya9nrn", text, extraParams);//change to your model id
			JSONArray resultArray = response.arrayResult;
			return getKeywords(resultArray);
		} catch (MonkeyLearnException e) {// it’s likely to have an exception
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	private static List<List<String>> getKeywords(JSONArray mlResultArray) {
		// TODO Auto-generated method stub
		List<List<String>> topKeywords = new ArrayList<>();
		// Iterate the result array and convert it to our format.
		for (int i = 0; i < mlResultArray.size(); ++i) {
			List<String> keywords = new ArrayList<>();
			JSONArray keywordsArray = (JSONArray) mlResultArray.get(i);
			for (int j = 0; j < keywordsArray.size(); ++j) {
				JSONObject keywordObject = (JSONObject) keywordsArray.get(j);
				// We just need the keyword, excluding other fields.
				String keyword = (String) keywordObject.get("keyword");
				keywords.add(keyword);

			}
			topKeywords.add(keywords);
		}
		return topKeywords;
	}
	
	

}
