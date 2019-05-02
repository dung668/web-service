package edu.ptit.util;

public class Constants {
	
	// ---------- Role ----------------
	public static int ADMIN_ROLE = 1;
	public static int USER_ROLE = 0;
	
	// ---------- Pagination ----------------
	public static int NUMBER_PER_PAGES = 6;
	
	
	// ---------- Order status ---------------
	public static final int ORDER_PENDING_PAYMENT = 0;
	public static final int ORDER_COMPLETED = 1;
	
	// ---------- Error Message --------------
	public static final String WRONG_USERNAME_OR_PASSWORD = "Sai thông tin hoặc tài khoản đang bị khóa!";
	public static final String LOGIN_FAIL = "Đăng nhập Facebook thất bại, thử lại!";
	public static final String DUPLICATE_USERNAME = "Tài khoản bị trùng, thử lại!";
	
	// ---------- Facebook config -------------
	
	public static String FACEBOOK_APP_ID = "357862431521823";
	public static String FACEBOOK_APP_SECRET = "4626cee1c208b72b1e7f9eccf843c931";
	public static String FACEBOOK_REDIRECT_URL = "https://localhost:8443/eshop/login";
	public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
}
