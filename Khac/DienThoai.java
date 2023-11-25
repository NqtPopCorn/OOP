package OOP;

import java.util.Scanner;

public class DienThoai {
    private String MaSP;
    private String TenSP;
    private int SoLuong;
    private float DonGia;
    private String DonViTinh;

    DienThoai(){

    }
    DienThoai(String MaSP,String TenSP,int SoLuong,float DonGia,String DonViTinh){
        this.MaSP=MaSP;
        this.TenSP=TenSP;
        this.SoLuong=SoLuong;
        this.DonGia=DonGia;
        this.DonViTinh=DonViTinh;
    }
    DienThoai(DienThoai x){
        MaSP=x.MaSP;
        TenSP=x.TenSP;
        SoLuong=x.SoLuong;
        DonGia=x.DonGia;
        DonViTinh=x.DonViTinh;
    }
    public String getMaSP() {
        return MaSP;
    }
    public String getTenSP() {
        return TenSP;
    }
    public int getSoLuong() {
        return SoLuong;
    }
    public float getDonGia() {
        return DonGia;
    }
    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonGia(float donGia) {
        DonGia = donGia;
    }
    public void setDonViTinh(String donViTinh) {
        DonViTinh = donViTinh;
    }
    public void setMaSP(String maSP) {
        MaSP = maSP;
    }
    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public void nhap(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Nhap MaSP:");
        MaSP=scanner.nextLine();
        System.out.println("Nhap Ten SP:");
        TenSP=scanner.nextLine();
        System.out.println("Nhap So luong:");
        SoLuong=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhap Don gia:");
        DonGia=scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Nhap Don vi tinh:");
        DonViTinh=scanner.nextLine();
    }
    public void xuat(){
        System.out.println("Ma san pham la:"+MaSP);
        System.out.println("Ten san pham la:"+TenSP);
        System.out.println("So dien thoai la:"+SoLuong);
        System.out.println("Don gia san pham la:"+DonGia);
        System.out.println("Don vi tinh san pham la:"+DonViTinh);
    }
}
