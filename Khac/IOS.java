package OOP;

import java.util.Scanner;

public class IOS extends DienThoai{
    private String TimIphone;
    private String HeSinhThaiDL;

    IOS(){

    }
    IOS(String MaSP,String TenSP,int SoLuong,float DonGia,String DonViTinh,String TimIphone,String HeSinhThaiDL){
        super(MaSP, TenSP, SoLuong, DonGia, DonViTinh);
        this.TimIphone=TimIphone;
        this.HeSinhThaiDL=HeSinhThaiDL;
    }
    public void setHeSinhThaiDL(String heSinhThaiDL) {
        HeSinhThaiDL = heSinhThaiDL;
    }
    public void setTimIphone(String timIphone) {
        TimIphone = timIphone;
    }
    public String getHeSinhThaiDL() {
        return HeSinhThaiDL;
    }
    public String getTimIphone() {
        return TimIphone;
    }
    public void nhap(){
        super.nhap();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Tim Iphone bang :");
        TimIphone=scanner.nextLine();
        System.out.println("He sinh thai doc lap la:");
        HeSinhThaiDL=scanner.nextLine();
    }
    public void xuat(){
        super.xuat();
        System.out.println("Tim Iphone bang:"+TimIphone);
        System.out.println("He sinh thai doc lap san pham duoc tich hop la:"+HeSinhThaiDL);
    }

}
