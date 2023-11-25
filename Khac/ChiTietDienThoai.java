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
public class ChiTietDienThoai {
    public String maSP;
    public String maNhaSX;
    public String maNCC;
    public String ngaySX;

    public ChiTietDienThoai(String maSP, String maNhaSX, String maNCC, String ngaySX) {
        this.maSP = maSP;
        this.maNhaSX = maNhaSX;
        this.maNCC = maNCC;
        this.ngaySX = ngaySX;
    }

    public ChiTietDienThoai() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setMaNhaSX(String maNhaSX) {
        this.maNhaSX = maNhaSX;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setNgaySX(String ngaySX) {
        this.ngaySX = ngaySX;
    }

    
    
    public String getMaSP() {
        return maSP;
    }

    public String getMaNhaSX() {
        return maNhaSX;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public String getNgaySX() {
        return ngaySX;
    }

    public void nhap(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ma san pham :");
        maSP = scanner.nextLine();
        System.out.println("Nhap ma nha san xuat :");
        maNhaSX = scanner.nextLine();
        System.out.println("Nhap nha cung cap :");
        maNCC = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Nhap ngay san xuat:");
        ngaySX = scanner.nextLine();
        scanner.nextLine();
    }
    public void xuat(){
        System.out.println("Ma san pham:"+maSP);
        System.out.println("Ma nha san xuat:"+maSP);
        System.out.println("Nha cung cap:"+maNCC);
        System.out.println("Ngay san xuat:"+ngaySX);
    }
    
}
