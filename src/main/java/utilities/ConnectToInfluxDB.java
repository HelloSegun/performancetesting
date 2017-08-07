package utilities;

import java.io.IOException;
import java.math.BigDecimal;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.LogLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;



public class ConnectToInfluxDB {
	
	private InfluxDB influxDB;
	private String dbName;
	private String measure;
	private String ta_g;
	private String tag_i;
	private String fi_st;
	private String s_cond;
	private double first_i;
	private double second_i;
	
	/*
	public ConnectToInfluxDB(){
		
	}
	*/
	
	public ConnectToInfluxDB(String dbName, String measure, String ta_g, String tag_i, String fi_st, String s_cond, double first_i, double second_i) {
		this.dbName = dbName;
		this.measure = measure;
		this.ta_g = ta_g;
		this.tag_i = tag_i;
		this.fi_st = fi_st;
		this.s_cond = s_cond;
		this.first_i = first_i;
		this.second_i = second_i;
	}
	
	/*
	public static void main (String args[]) {
		try {
			ConnectToInfluxDB connect = new ConnectToInfluxDB();
			connect.connectToInfluxDB();
			//connect.writeIntoDatabase();
			connect.writeIntoDatabase2();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}
	*/
	
	
	public void connectToInfluxDB() throws InterruptedException, IOException {
		String ip = "localhost";
		this.influxDB = InfluxDBFactory.connect("http://" + ip + ":8086", "admin", "admin");
		boolean influxDBstarted = false;
		do {
			Pong response;
			try {
				response = this.influxDB.ping();
				System.out.println(response);
				if (!response.getVersion().equalsIgnoreCase("unknown")) {
					influxDBstarted = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(100L);
		} while (!influxDBstarted);
		this.influxDB.setLogLevel(LogLevel.FULL);
		System.out.println("#######################################################################");
		System.out.println("# Connected to InfluxDB Version: " + this.influxDB.version() + " #");
		System.out.println("#######################################################################");
	}
	
	
	public void writeIntoDatabase() throws InterruptedException {
		String dbName = "openhab_db";
		int decimalPlace = 2;
		
		int x = 1;
		while(x < 5000) {
			float min = 0.05f, max = 0.99f;
			float random_num = (float) (Math.random()*((max-min)+1)) + min;
			BigDecimal bd = new BigDecimal(Float.toString(random_num));
			bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
			try {
				BatchPoints batchPoints = BatchPoints.database(dbName).tag("async", " ").retentionPolicy("autogen").build();
				Point point1 = Point
						.measurement("cpu")
						.tag("host", "serverA")
						.addField("region", "us_west")
						.addField("value", bd)
						//.addField("value", 90L)
						.build();
				batchPoints.point(point1);
				this.influxDB.write(batchPoints);
				
				x++;
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			Thread.sleep(10000);
		}
	}
	
	
	public void writeIntoDatabase2() throws InterruptedException {
		//String dbName = "openhab_db";
		try {
			BatchPoints batchPoints = BatchPoints.database(dbName).tag("async", " ").retentionPolicy("autogen").build();
			Point point1 = Point
					.measurement(measure)
					.tag(ta_g, tag_i)
					.addField(fi_st, first_i)
					.addField(s_cond, second_i)
					//.addField("value", 90L)
					.build();
			batchPoints.point(point1);
			this.influxDB.write(batchPoints);
				
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
