//package com.kdu;
//
//
//import java.text.ParseException;
//
//import jdk.jfr.StackTrace;
////import org.json.simple.parser.ParseException;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//public class testMain {
//    public static StartApp instance;
//
//    @Before
//    public void setUPtestCase(){
//        System.out.println("Before each test");
//    }
//
//    @BeforeClass
//    public static void setUp(){
//        System.out.println("Before all test");
//        instance = new StartApp();
//    }
//
//    @Test
//    public void testApiCache(){
//        try{
//            String result = StartApp.appQuery("KFC,Bangalore");
//            Assert.assertEquals("Latitude : 13° 1' 27.90228'' N;  Longitude : 77° 32' 56.31108'' E;  Address : KFC, 9/36, Tumkur Road, HMT Ward, Bengaluru -",result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testApiGeoApi() throws SQLException, IOException, ParseException, ClassNotFoundException, InterruptedException {
//        try{
//            String result = StartApp.appQuery("spain");
//            Assert.assertEquals("Latitude : 39° 19' 33.84660'' N;  Longitude : 4° 50' 16.72476'' W;  Address : Spain",result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testApiDb() {
//        try{
//            String result = StartApp.appQuery("spain");
//            Assert.assertEquals("Latitude : 39° 19' 33.84660'' N;  Longitude : 4° 50' 16.72476'' W;  Address : Spain",result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//    @Test
//    public void testApiExit() throws SQLException, IOException, ParseException, ClassNotFoundException, InterruptedException {
//        try{
//            String result = StartApp.appQuery("EXIT");
//            Assert.assertEquals("Thank you for visiting Geocoder!!",result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
