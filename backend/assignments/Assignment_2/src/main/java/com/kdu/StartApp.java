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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StartApp {

    private static final Geocoder api = new Geocoder();
    private static final Database mysqlDatabase = new Database();
    private static final Cache cache_in_mem = new Cache();
    private static final Lock lock = new ReentrantLock();


    public static void appQuery(String gvnAddress) throws SQLException, IOException, ParseException, InterruptedException {

        lock.lock();
        try {

            String address = "";
            if (gvnAddress.equals("EXIT")) {
                address = "Thank you for visiting Geocoder!!";
                System.out.println(address);
            }
            LocDetail result = cache_in_mem.getLocationFromCache(gvnAddress);    // first it will search in cache

            if (result.isLocationFound()) {
                address = result.getLatLong();
            } else if (!result.isLocationFound()) {

                result = mysqlDatabase.getLocationFromDatabase(gvnAddress);    //then if not found, it will search in database


                if (result.isLocationFound()) {
                    address = result.getLatLong();
                } else if (!result.isLocationFound()) {

                    String url = "https://api.opencagedata.com/geocode/v1/json?key=81d33f6b153241c29298d33eedb45c05&q=" + gvnAddress;
                    result = api.getLocationFromGeocoder(url);               //still if not found, it will fetch from api



                    if (result.isLocationFound()) {
                        mysqlDatabase.putLocationInDatabase(gvnAddress, result);
                        address = result.getLatLong();
                    } else if (!result.isLocationFound())
                        address = "Cannot find any match of the address you entered.";
                }
                System.out.println(address);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {     //will ensure that lock is released even if some exception occurs
            if (lock != null) {
                lock.unlock();
            }
        }
    }







    
    public static void main(String[] args) throws SQLException, IOException, ParseException, InterruptedException {
        System.out.println("Come and search for your location in my Geocoder!!!");

    }

    }



