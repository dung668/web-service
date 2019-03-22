/*
package edu.ptit.controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.ptit.dao.NotificationDAO;

import edu.ptit.dao.impl.NotificationDAOImpl;

import edu.ptit.model.Notification;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/saveNotification")
public class NotificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NotificationDAO notificationDao;
    
    private final String USER_AGENT = "Mozilla/5.0";
    
    public NotificationController() {
        super();
        notificationDao = new NotificationDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		int id_post = Integer.parseInt(request.getParameter("id_post"));
		String id_cmt = request.getParameter("id_cmt");
		
		String url = "https://graph.facebook.com/v3.2/" + id_cmt + "?access_token=EAAEAVaWDkZCcBAMZCIqJXsHAVcczJT1AjIE2HMZAS3zePLLMrlUqn0nUe18ZBi300sjyhGYZAAUlqc98dAhvl0JEbl45j5qXK75NVfi5uJC07kZCGQ6aAZAcP3KzWMsvHWcMxHelh7yXQvZA50PEjULFkwT7f94MtWzJ51NgNZBrcMAZDZD";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		if(responseCode != 200) {
			return;
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer respon = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			respon.append(inputLine);
		}
		in.close();
		//System.out.println(respon.toString());
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject ob = (JSONObject) parser.parse(respon.toString());
			JSONObject ob2 = (JSONObject) ob.get("from");
			String username = (String) ob2.get("name");
			String message = (String) ob.get("message");
			String created_at = (String) ob.get("created_time");
			
			Notification noti = new Notification(0, username, message, id_post, created_at);
//			NotificationDAO notiDAO = new NotificationDAOImpl();
			notificationDao.addNotification(noti);
//			if(notiDAO.addNotification(noti) != null) {
//				response.getWriter().write("Success Data");
//			}else {
//				response.getWriter().write("Error Data");
//			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
*/