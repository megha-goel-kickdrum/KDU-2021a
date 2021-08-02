package com.kdu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    private final Connection connection;

    public Database(){
        this.connection = this.connectToDatabase();
    }

    public Connection getConnection(){
        return this.connection;
    }

    private Connection connectToDatabase(){
        Connection conn = null;
        try {
            String dBConnectionUrl = "jdbc:mysql://localhost:3306/geodb";
            String dbUsername = "root";
            String dbPassword = "toor";
            conn = DriverManager.getConnection(dBConnectionUrl, dbUsername, dbPassword);
        }catch(SQLException throwable) {
            System.err.println(throwable);
        }
        return conn;
    }

    public LocDetail getLocationFromDatabase(String addressPoint) throws SQLException {
        LocDetail result = new LocDetail();
        var statement = connection.createStatement();
        var resultSet = statement.executeQuery("SELECT DISTINCT Latitude,Longitude,Address FROM geocoder WHERE AddressPoint='"+addressPoint+"';");
        while(resultSet.next()){
            String lat = resultSet.getString("Latitude");
            String lng = resultSet.getString("Longitude");
            String addr = resultSet.getString("Address");
            result.findLatLong(lat, lng, addr, true);
            break;
        }
        statement.close();
        return result;
    }

    public void putLocationInDatabase(String addressPoint, LocDetail location) throws SQLException {
        String sql = "INSERT IGNORE INTO geocoder (AddressPoint,Latitude,Longitude,Address,Frequency) VALUES(?,?,?,?,?);";
        String frequency = "1";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, addressPoint);
        pstmt.setString(2, location.getLatitude());
        pstmt.setString(3, location.getLongitude());
        pstmt.setString(4, location.getAddress());
        pstmt.setString(5, frequency);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }

//    public int getFrequency(String addressPoint) throws SQLException {
//        String sql = "SELECT DISTINCT Frequency FROM geocoder WHERE AddressPoint='"+addressPoint+"';";
//        var statement = connection.createStatement();
//        var resultSet = statement.executeQuery(sql);
//        String frequency = "0";
//        while(resultSet.next()){
//            frequency = resultSet.getString("Frequency");
//            break;
//        }
//        return Integer.parseInt(frequency);
//    }
//
//    public void setFrequency(String addressPoint, Integer frequency) throws SQLException {
//        String sql = "UPDATE geocoder SET Frequency = '"+frequency+"' WHERE AddressPoint='"+addressPoint+"';";
//        var statement = connection.createStatement();
//        statement.executeUpdate(sql);
//        statement.close();
//    }
}
