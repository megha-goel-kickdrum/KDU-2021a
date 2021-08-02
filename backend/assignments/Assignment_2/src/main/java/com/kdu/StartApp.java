package com.kdu;

import com.kdu.Cache;
import com.kdu.Database;
import com.kdu.Geocoder;
import com.kdu.LocDetail;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
//import java.text.ParseException;



import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.ReentrantLock;

public class StartApp {

    private static final Geocoder api = new Geocoder();
    private static final Database mysqlDatabase = new Database();
    private static final Cache inMemoryCache = new Cache();
   // private static final Map<MysqlxDatatypes.Scalar.String, Vector<Integer>> frequencyTable = new HashMap<>();
    private static final ReentrantLock lock = new ReentrantLock();
    private static final ScheduledExecutorService executor =
            Executors.newSingleThreadScheduledExecutor();


    public static String appQuery(String gvnAddress) throws SQLException, IOException, ParseException, InterruptedException {
        String address = "";
        if(gvnAddress.equals("EXIT")){
            address = "Thank you for visiting Geocoder!!";
            return address;
        }
        LocDetail result = inMemoryCache.getLocationFromCache(gvnAddress);

        if(result.isLocationFound()){
            address = result.getLatLong();
        }
        else if(!result.isLocationFound()){

            result = mysqlDatabase.getLocationFromDatabase(gvnAddress);
            if(result.isLocationFound()){
                address =  result.getLatLong();
            }
            else if(!result.isLocationFound()){

                String url = "https://api.opencagedata.com/geocode/v1/json?key=81d33f6b153241c29298d33eedb45c05&q="+gvnAddress;
                result = api.getLocationFromGeocoder(url);
                if(result.isLocationFound()){
                    mysqlDatabase.putLocationInDatabase(gvnAddress,result);
                    address =  result.getLatLong();
                }
                else if(!result.isLocationFound())
                    address =  "Cannot find any match of the address you entered.";
            }
        }
        return address;
    }


    
    public static void main(String[] args) throws SQLException, IOException, ParseException, InterruptedException {
        System.out.println("Welcome to Geocoder!!!");

        //cache update thread
        //CacheUpdateThread();

        //reading task
        while (true){
            Scanner readInput = new Scanner(System.in);
            System.out.println("Please enter the address you wish to search. If you wish to exit the app, enter exit");
            String inputAddressPoint = readInput.nextLine();
            inputAddressPoint = inputAddressPoint.toUpperCase();
            String result = appQuery(inputAddressPoint);
            if(result.equals("Thank you for visiting Geocoder!!")){

                executor.shutdown();

                mysqlDatabase.closeConnection();
                System.out.println(result);
                return;
            }
//            if(!result.equals("Cannot find any match of the address you entered.")){
//                if(frequencyTable.containsKey(inputAddressPoint)){
//                    int temp = frequencyTable.get(inputAddressPoint).get(0);
//                    frequencyTable.get(inputAddressPoint).set(0,temp+1);
//                }else {
//                    Vector<Integer> freq = new Vector<>();
//                    freq.add(1);
//                    freq.add(0);
//                    frequencyTable.put(inputAddressPoint,freq);
//                }
//            }

            //printing result
            System.out.println("latitude and longitude of the address");
            System.out.println(result);
        }
    }



    }



