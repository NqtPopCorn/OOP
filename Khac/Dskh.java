package OOP;

import java.util.Scanner;
import java.util.Arrays;


public class Dskh extends Khachhang{
    int n;
    Khachhang[] pe = new Khachhang[1];

    //------------------------Ham thiet lap----------------------
    public Dskh(){}
    public Dskh(int a, Khachhang b[]){n = a; pe = b;}
    public Dskh(Dskh c){n = c.n; pe = c.pe;}
    
    
    //------------------------Nhap, xuat--------------------------
    public void nhap(){
        Scanner ip = new Scanner(System.in);
        System.out.print("Nhap n : ");
        n = ip.nextInt();
        pe = new Khachhang[n];
        
        for (int i = 0; i<n; i++){
            System.out.println("Khach hang "+ (i+1));
            pe[i] = new Khachhang();
            pe[i].nhap();
        }
    }
    
    public void xuat(){
        for (int i = 0; i<n; i++){
            System.out.println("Khach hang "+ (i+1));
            super.xuat();
        }
    }
    //-----------------------them---------------------------
    public void them(){
        Scanner ip = new Scanner(System.in);
        System.out.println("Them khach hang.");
        pe = Arrays.copyOf(pe, pe.length +1);
        pe[n].nhap();
        n++;
    }
    
    //-----------------------sua---------------------------
    public void sua(){
        Scanner ip = new Scanner(System.in);
        System.out.print("Nhap ma khach hang can sua : ");
        String m = ip.next();
        for (int i = 0; i<n; i++){
            if (m == pe[i].ma)
            {
                pe[i].nhap();
            }
        }
    }
    
    //-----------------------xoa---------------------------
    public void xoa(){
        Scanner ip = new Scanner(System.in);
        System.out.print("Nhap ma khach hang can xoa : ");
        String m = ip.next();
        for (int i = 0; i<n; i++){
            if (m == pe[i].ma)
            {
                for(int j = i; j<n-1; j++){
                    pe[j] = pe[j+1];
                }
                break;
            }
        }
        pe = Arrays.copyOf(pe, pe.length-1);
    }
    
    //-----------------------tim kiem---------------------------
    public void timkiem_Ma(){
        Scanner ip = new Scanner(System.in);
        System.out.print("Nhap ma cua khach hang can tim : ");
        String m = ip.next();
        int a = 0;
        for(int i = 0; i<n; i++){
            if (m == pe[i].ma){
                    System.out.println("Khach hang can tim.");
                    pe[i].xuat();
                    a = 1;
                    break;
            }
        }
        if(a == 0){System.out.println("Khong tim thay.");}
    }
    
    public void timkiem_Ten(){
        Scanner ip = new Scanner(System.in);
        System.out.print("Nhap ten cua khach hang can tim : ");
        String t = ip.next();
        int a = 0;
        for(int i = 0; i<n; i++){
            if (t == pe[i].ten){
                    System.out.println("Khach hang can tim.");
                    pe[i].xuat();
                    a = 1;
            }
        }
        if(a == 0){System.out.println("Khong tim thay.");}
    }
    
    public void timkiem_Sdt(){
        Scanner ip = new Scanner(System.in);
        System.out.print("Nhap sdt cua khach hang can tim : ");
        String s = ip.next();
        int a = 0;
        for(int i = 0; i<n; i++){
            if (s == pe[i].sdt){
                    System.out.println("Khach hang can tim.");
                    pe[i].xuat();
                    a = 1;
                    break;
            }
        }
        if(a == 0){System.out.println("Khong tim thay.");}
    }
    
    //-----------------------thong ke---------------------------
    public void thongke_Ten(){
        Scanner ip = new Scanner(System.in);
        System.out.print("Nhap ten : ");
        String t = ip.next();
        for (int i = 0; i<n; i++){
            if(t == pe[i].ten){
                pe[i].xuat();
            }
        }
    }
    
    public void thongke_Gt(){
        Scanner ip = new Scanner(System.in);
        System.out.print("Nhap gioi tinh : ");
        String g = ip.next();
        for (int i = 0; i<n; i++){
            if(g == pe[i].gt){
                pe[i].xuat();
            }
        }
    }
}
    
    
