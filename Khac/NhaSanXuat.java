package OOP;

import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell Vostro 3580
 */
public class NhaSanXuat {
    public String maNhaSX;
    public String tenNhaSX;
    public String diaChi;
    public double SDT;

    public NhaSanXuat(String maNhaSX, String tenNhaSX, String diaChi, double SDT) {
        this.maNhaSX = maNhaSX;
        this.tenNhaSX = tenNhaSX;
        this.diaChi = diaChi;
        this.SDT = SDT;
    }

    public void setMaNhaSX(String maNhaSX) {
        this.maNhaSX = maNhaSX;
    }

    public void setTenNhaSX(String tenNhaSX) {
        this.tenNhaSX = tenNhaSX;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSDT(double SDT) {
        this.SDT = SDT;
    }

    public String getMaNhaSX() {
        return maNhaSX;
    }

    public String getTenNhaSX() {
        return tenNhaSX;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public double getSDT() {
        return SDT;
    }
    
    public void nhap(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ma nha san xuat  :");
        maNhaSX = scanner.nextLine();
        System.out.println("Nhap ten nha san xuat :");
        tenNhaSX = scanner.nextLine();
        System.out.println("Nhap dia chi  :");
        diaChi = scanner.nextLine();
        System.out.println("Nhap so dien thoai:");
        SDT = scanner.nextDouble();
    }
    
    public void xuat(){
        System.out.println("Ma nha san xuat :"+maNhaSX);
        System.out.println("Ten nha san xuat:"+tenNhaSX);
        System.out.println("Dia chi  :"+diaChi);
        System.out.println("So dien thoai :"+SDT);
    }
    
    
}
