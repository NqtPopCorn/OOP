package OOP;

import java.util.Scanner;

public class Androi extends DienThoai{
    private String crack;

    Androi(){

    }
    Androi(String MaSP,String TenSP,int SoLuong,float DonGia,String DonViTinh,String crack){
        super(MaSP, TenSP, SoLuong, DonGia, DonViTinh);
        this.crack=crack;
    }
    public void setCrack(String crack) {
        this.crack = crack;
    }
    public String getCrack() {
        return crack;
    }
    public void nhap(){
        super.nhap();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Phan mem crack co the su dung:");
        crack=scanner.nextLine();
    }
    public void xuat(){
        super.xuat();
        System.out.println("Phan mem crack co the su dung la:"+crack);
    }
}
