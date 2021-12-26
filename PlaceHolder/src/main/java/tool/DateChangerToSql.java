package tool;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateChangerToSql {
	
		public static Date changeSqlDate(String date) throws ParseException {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String time = date;
					
			long dateTime = sdf.parse((date)).getTime();
			System.out.println(dateTime);
			Date checkIn = new java.sql.Date(dateTime);
			
			return checkIn;
		}
		
		public static Date changeCurrentTime(long currentTimeMillis) throws Exception{
			Date revDay = new java.sql.Date(currentTimeMillis);
			return revDay;
		}

}
