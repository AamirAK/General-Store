package com.java.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.java.model.storeModel;
import com.java.storeDao.storeDaolayer;

public class storeService {
    private Scanner sc;
    private Scanner ss;
    storeDaolayer sDao;
    public storeService(){
        sc=new Scanner(System.in);
        ss=new Scanner(System.in);
        sDao=new storeDaolayer();
    }
    public void addItem(){
        System.out.println("Enter Item COde");
        int c=sc.nextInt();
        boolean f=sDao.validateItem(c);
        if(f==true){
            System.out.println("Enter Quantity to be added");
            int qty=sc.nextInt();
            sDao.updateQty(c, qty);
        }else{
            System.out.println("Enter Item Name");
            String nm=ss.nextLine();
            System.out.println("Enter Quantity");
            int qty=sc.nextInt();
            System.out.println("Enter price");
            double p=sc.nextDouble();
            storeModel sm=new storeModel();
            sm.setCode(c);
            sm.setName(nm);
            sm.setQty(qty);
            sm.setPrice(p);
            sDao.insertItem(sm);
        }
        
    }
    public void removeItem(){
        System.out.println("Enter Item Code");
        int c=sc.nextInt();
        sDao.deletetItem(c);
        System.out.println("Item Deleted");
    }

    public void changePrice(){
        System.out.println("Enter item Code");
        int c=sc.nextInt();
        System.out.println("Enter new Price");
        double p=sc.nextDouble();
        sDao.updateItem(c, p);
    }
    public void showItem(){
        List<storeModel> sm=new ArrayList<storeModel>();
        sm=sDao.displayItems();
        for(storeModel s:sm){
            System.out.println(s.toString());
        }
    }
    public void searchItem(){
        List<storeModel> sm=new ArrayList<storeModel>();
        System.out.println("Enter Item code");
        int c=sc.nextInt();
        sm=sDao.identifyItems(c);
        for(storeModel s:sm){
            System.out.println(s.toString());
        }
    }
    public void sellItem(){
        System.out.println("Enter Item code");
        int c=sc.nextInt();
        System.out.println("Enter Quantity");
        int q=sc.nextInt();
        sDao.sellingItems(c, q);
    }
}
