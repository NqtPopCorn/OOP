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
public class ChiTietHoaDon {
    public String maHD;
    public String maSP;
    public int soLuong;
    public double donGia;
    public double thanhTien;

    public ChiTietHoaDon(String maHD, String maSP, int soLuong, double donGia, double thanhTien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public ChiTietHoaDon() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getThanhTien() {
        return thanhTien = soLuong*donGia;
    }
    
        
    public void nhap(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ma san pham  :");
        maSP = scanner.nextLine();
        System.out.println("Nhap ma hoa don :");
        maHD = scanner.nextLine();
        System.out.println("Nhap so luong :");
        soLuong = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhap don gia:");
        donGia = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Nhap thanh tien :");
        thanhTien = scanner.nextDouble();
    }
    public void xuat(){
        System.out.println("Ma san pham:"+maSP);
        System.out.println("Ma hoa don:"+maHD);
        System.out.println("So luong :"+soLuong);
        System.out.println("Don giat:"+donGia);
        System.out.println("Thanh tien:"+thanhTien);
    }
    

}
    

    
    