package PN_CTPN_NCC;
import java.util.Scanner;
import sanpham.*;

//chi ghi vao file khi dung format va khong de trong
public class ChiTietPhieuNhap {
    private String maPN;//PN001
    private String maSP;//SP001
    private int soLuong;
    private double donGia;//* */
    private double thanhTien;
    

    public ChiTietPhieuNhap() {
        maPN = "";
        maSP = "";
        soLuong = 0;
        donGia = 0;
        thanhTien = 0;
    }
    public ChiTietPhieuNhap(String maPN, String maSP, int soLuong, double donGia) {
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }

    public String getMaPN() { return maPN; }
    public void setMaPN(String maPN) { this.maPN = maPN;}
    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
    public double getThanhTien() { return thanhTien; }
    public void setThanhTien(double thanhTien) { this.thanhTien = thanhTien; }

    public void nhap() {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Nhap ma phieu nhap: ");
        maPN = scanner.next();
        System.out.println("Nhap ma san pham: ");
        maSP = scanner.next();
        System.out.println("Nhap so luong: ");
        soLuong = scanner.nextInt();
        //chua kiem tra don gia dung nhu trong ds san pham k
        System.out.println("Nhap don gia: ");
        donGia = scanner.nextDouble();
        scanner.nextLine();
        thanhTien = donGia * soLuong;
    }
    public void nhapSP(double donGia) {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Nhap ma san pham: ");
        maSP = scanner.next();
        System.out.println("Nhap so luong: ");
        soLuong = scanner.nextInt();
        this.donGia = donGia;
        scanner.nextLine();
        thanhTien = donGia * soLuong;
    }
    public void khoiTaoTuString(String data) {
        Scanner scanner = new Scanner(data);
        scanner.useDelimiter(";\s{0,}|\n");
        maPN = scanner.next().trim();
        maSP = scanner.next().trim();
        soLuong = Integer.parseInt(scanner.next().trim());
        donGia = Double.parseDouble(scanner.next().trim());
        thanhTien = Double.parseDouble(scanner.next().trim());
        scanner.close();
    }

    public void xuat() {
        System.out.println("Ma phieu nhap: " + maPN);
        System.out.println("Ma san pham: " + maSP);
        System.out.println("So luong: " + soLuong);
        System.out.println("Don gia: " + donGia);
        System.out.format("Thanh tien: %.3f\n", thanhTien);
    }
    public void tinhThanhTien() {
        thanhTien = donGia * soLuong;
    }
    public void tinhThanhTien(int salePercent) {
        thanhTien = donGia * soLuong * (1 - salePercent*1.0/100);
    }

    public void xemPhieuNhap(ListPhieuNhap l) {
        PhieuNhap found = l.findByMaPN(maPN);
        if(found != null) found.xuat();
        else System.out.println("Khong tim thay phieu nhap voi ma " + maPN);
    }

    public String toString() {
        return String.format("%s; %s; %d; %.3f; %.3f",
            maPN, maSP, soLuong, donGia, thanhTien
        );
    }

    public String toStringFormat(String formatter) {
        return String.format(formatter, maPN, maSP, soLuong, donGia, thanhTien);
    }
}