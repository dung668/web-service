package edu.ptit.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Common {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date stringToDate(String strDate) {
		try {
			return new Date(sdf.parse(strDate).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String dateToString(Date date) {
		return sdf.format(date);
	}
	
	public static void closeStmt(Connection conn, Statement stmt, ResultSet rs) {
		try {
			
			if(conn != null) conn.close();
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closePS(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if(conn != null) conn.close();
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
              sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            
        }
        return null;
    }
	
	public static void main(String[] args) {
		System.out.println(MD5("1"));
	}
	
}
