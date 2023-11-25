package OOP;

import java.util.Scanner;

public class Hoadon{
    String maSP;
    String tenSP;
    String soluong;
    String dongia;
    String ngayxuat;
    
    //------------------------Ham thiet lap----------------------
    public Hoadon(){}
    public Hoadon(String m, String t, String g, String pn, String ad){maSP = m; tenSP = t; soluong = g; dongia = pn; ngayxuat = ad;}
    public Hoadon(Hoadon a1){maSP = a1.maSP; tenSP = a1.tenSP; soluong = a1.soluong; dongia = a1.dongia; ngayxuat = a1.ngayxuat;}
    
    //-----------------------get, set-----------------------
    public void setMa(String m){maSP = m;}
    public String getMa(){return maSP;}
    
    public void setTen(String t){tenSP = t;}
    public String getTen(){return tenSP;}
    
    public void setSl(String g){soluong = g;}
    public String getSl(){return soluong;}
    
    public void setDongia(String s){dongia = s;}
    public String getDongia(){return dongia;}
    
    public void setNgayxuat(String d){ngayxuat = d;}
    public String getNgayxuat(){return ngayxuat;}
    
    //------------------------Nhap, xuat--------------------------
    public void nhap(){
        Scanner ip = new Scanner(System.in);
        System.out.print("Ma : ");
        maSP = ip.next();
        System.out.print("Ten : ");
        tenSP = ip.next();
        System.out.print("So luong : ");
        soluong = ip.next();
        System.out.print("Don gia : ");
        dongia = ip.next();
        System.out.print("Ngay xuat : ");
        ngayxuat = ip.next();
    }
    
    public void xuat(){
        System.out.print("Ma : " + maSP); 
        System.out.print("Ten : " + tenSP);  
        System.out.print("So luong : " + soluong);  
        System.out.print("Don gia : " + dongia); 
        System.out.print("Ngay xuat : " + ngayxuat);
        
    }
}