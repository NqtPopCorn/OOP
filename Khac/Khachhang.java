package OOP;

import java.util.Scanner;

public class Khachhang{
    String ma;
    String ten;
    String gt;
    String sdt;
    String dc;
    
    //------------------------Ham thiet lap----------------------
    public Khachhang(){}
    public Khachhang(String m, String t, String g, String pn, String ad){ma = m; ten = t; gt = g; sdt = pn; dc = ad;}
    public Khachhang(Khachhang a1){ma = a1.ma; ten = a1.ten; gt = a1.gt; sdt = a1.sdt; dc = a1.dc;}
    
    //-----------------------get, set-----------------------
    public void setMa(String m){ma = m;}
    public String getMa(){return ma;}
    
    public void setTen(String t){ten = t;}
    public String getTen(){return ten;}
    
    public void setGt(String g){gt = g;}
    public String getGt(){return gt;}
    
    public void setSdt(String s){sdt = s;}
    public String getSdt(){return sdt;}
    
    public void setDc(String d){dc = d;}
    public String getDc(){return dc;}
    
    //------------------------Nhap, xuat--------------------------
    public void nhap(){
        Scanner ip = new Scanner(System.in);
        System.out.print("Ma : ");
        ma = ip.next();
        System.out.print("Ten : ");
        ten = ip.next();
        System.out.print("Chuc vu : ");
        gt = ip.next();
        System.out.print("So dien thoai : ");
        sdt = ip.next();
        System.out.print("Dia chi : ");
        dc = ip.next();
    }
    
    public void xuat(){
        System.out.print("Ma : " + ma); 
        System.out.print("Ten : " + ten);  
        System.out.print("Chuc vu : " + gt);  
        System.out.print("So dien thoai : " + sdt); 
        System.out.print("Dia chi : " + dc);
        
    }
}