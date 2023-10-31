import java.util.Scanner;

public class Nhanvien extends Khachhang{
    String chucvu;
    
    //------------------------Ham thiet lap----------------------
    public Nhanvien(){} 
    public Nhanvien(String m, String t, String g, String pn, String ad, String cv)
    {
        super(m,t,g,pn,ad);
        chucvu = cv;
    }
    public Nhanvien(Nhanvien a1)
    {
        super((Khachhang)a1);
        chucvu = a1.chucvu;
    }
    
    
    //-----------------------get, set-----------------------
    public void setCV(String c){chucvu = c;}
    public String getCV(){return chucvu;}
    
    
    //------------------------Nhap, xuat--------------------------
    public void nhap(){
        Scanner ip = new Scanner(System.in);
        super.nhap();
        System.out.print("Chuc vu : ");
        chucvu = ip.next();
    }
    
    public void xuat(){
        super.xuat();  
        System.out.print("Chuc vu : " + chucvu);  
    }
}