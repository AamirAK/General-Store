package com.java.storeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java.dataConnect.storeConnect;
import com.java.model.storeModel;

public class storeDaolayer {
    Connection con;
    PreparedStatement stat;
    
    public storeDaolayer() {
        con=storeConnect.getConnection();
    }

    public boolean validateItem(int c){
        boolean v=false;
        try{
            stat=con.prepareStatement("select itemcode from store");
            ResultSet rs=stat.executeQuery();
            while(rs.next()){
                if(rs.getInt(1)==c){
                    v=true;
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return v;
    } 

    public void insertItem(storeModel s){
        try{
            stat=con.prepareStatement("insert into store values(?,?,?,?)");
            stat.setInt(1, s.getCode());
            stat.setString(2,s.getName());
            stat.setInt(3, s.getQty());
            stat.setDouble(4, s.getPrice());
            stat.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void deletetItem(int c){
        try{
            stat=con.prepareStatement("delete from store where itemcode=?");
            stat.setInt(1, c);
            stat.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void updateQty(int c,int q){
        try{
            stat=con.prepareStatement("select qty from store where itemcode=?");
            stat.setInt(1, c);
            ResultSet rs=stat.executeQuery();
            int qty=0;
            while(rs.next()){
                qty=rs.getInt(1);
            }
            qty=qty+q;
            stat=con.prepareStatement("update store set qty=? where itemcode=?");
            stat.setInt(1, qty);
            stat.setInt(2,c);
            stat.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void updateItem(int c,double p){
        try{
            stat=con.prepareStatement("update store set price=? where itemcode=?");
            stat.setDouble(1, p);
            stat.setInt(2, c);
            stat.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public List<storeModel> displayItems(){
        List<storeModel> sm=new ArrayList<storeModel>();
        try{
            stat=con.prepareStatement("select *from store");
            ResultSet rs=stat.executeQuery();
            while(rs.next()){
                storeModel s=new storeModel();
                s.setCode(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setQty(rs.getInt(3));
                s.setPrice(rs.getDouble(4));
                sm.add(s);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return sm;
    }
    public List<storeModel> identifyItems(int c){
        List<storeModel> sm=new ArrayList<storeModel>();
        try{
            stat=con.prepareStatement("select *from store where itemcode=?");
            stat.setInt(1, c);
            ResultSet rs=stat.executeQuery();
            while(rs.next()){
                storeModel s=new storeModel();
                s.setCode(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setQty(rs.getInt(3));
                s.setPrice(rs.getDouble(4));
                sm.add(s);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return sm;
    }
    public void sellingItems(int c,int qty){
        try{
            stat=con.prepareStatement("select qty,price from store where itemcode=?");
            stat.setInt(1, c);
            ResultSet rs=stat.executeQuery();
            boolean f=false;
            int q=0;
            while(rs.next()){
                if(qty>rs.getInt(1)){
                    System.out.println("Quantity not available");
                    //break;
                }
                else{
                    q=rs.getInt(1)-qty;
                    System.out.println("Your Total Bill is- "+rs.getDouble(2)*qty);
                    f=true;
                }
            }
            if(f==true){
                stat=con.prepareStatement("update store set qty=? where itemcode=?");
                stat.setInt(1, q);
                stat.setInt(2, c);
                stat.executeUpdate();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
