package global.ksh.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import global.ksh.util.Httpclient;
import global.ksh.util.HttpclientSendMsg;

/**
 * Handles requests for the application home page.
 */
@Controller
@CrossOrigin("*")
public class HomeController {
	
	@CrossOrigin("*")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		
		return "index";
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "result", method = RequestMethod.GET)
	public String result(String code) {	    
	    
		String url = "";
		String result = "";
		
		url += "https://notify-bot.line.me/oauth/token?";
		url += "grant_type=authorization_code&";
		url += "code=" + code + "&";
		url += "redirect_uri=http://localhost:8081/namiya/result&";
		url += "client_id=j1pv2YPVmD4wAI5oJhXxBp&";
		url += "client_secret=9hQo5dXlluRl3d2e7fZL2Az9h9W6XUacIepEWDOkkeZ";
		
		try {
			Httpclient.url = url;
			
			result = Httpclient.sendGet();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(result);
		String access_token = element.getAsJsonObject().get("access_token").getAsString();
		
		System.out.println("인증키: " + access_token);
		
		return "result";
	}
	
	@RequestMapping(value="sendMsg", method=RequestMethod.POST)
	public @ResponseBody String sendMsg(String accessToken, String message) {
		
		String urlSetting = "https://notify-api.line.me/api/notify?message=" + message;
		String json = "";
		
		try {
			HttpclientSendMsg.url = urlSetting;
			json = HttpclientSendMsg.sendGet(accessToken);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}
}
