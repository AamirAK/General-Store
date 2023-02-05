package com.java.client;

import java.util.Scanner;

import com.java.service.storeService;

public class main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        storeService ss=new storeService();
        while(true){
            System.out.println("1...Add item");
            System.out.println("2...remove item");
            System.out.println("3...update price");
            System.out.println("4...sale item");
            System.out.println("5...show all items");
            System.out.println("6...search item by id");
            int op=sc.nextInt();
            switch(op){
                case 1:
                    ss.addItem();
                    break;
                case 2:
                    ss.removeItem();
                    break;
                case 3:
                    ss.changePrice();
                    break;
                case 4:
                    ss.sellItem();
                    break;
                case 5:
                    ss.showItem();
                    break;
                case 6:
                    ss.searchItem();
                    break;
                default:
                    System.out.println("wrong input");
                    break;
            }
        }
    }
}
