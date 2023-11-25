
import java.util.Scanner;

public class ChiTietPhieuNhap {
    private String maPN;
    private String maSP;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public ChiTietPhieuNhap() {
        maPN = "null";
        maSP = "null";
        soLuong = 0;
        donGia = 0;
        thanhTien = 0;
    }
    public ChiTietPhieuNhap(String maSP, int soLuong, double donGia) {
        this.maPN = "null";
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
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
        maPN = scanner.nextLine();
        nhapSP();
    }
    public void nhapSP() {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Nhap ma san pham: ");
        maSP = scanner.nextLine();
        System.out.println("Nhap so luong: ");
        soLuong = scanner.nextInt();
        System.out.println("Nhap don gia: ");
        donGia = scanner.nextDouble();
        thanhTien = donGia * soLuong;
    }
    public void nhap(String data) {
        Scanner scanner = new Scanner(data);
        maPN = scanner.next();
        maSP = scanner.next();
        soLuong = scanner.nextInt();
        donGia = scanner.nextDouble();
        thanhTien = scanner.nextDouble();
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
        PhieuNhap found = l.find(maPN);
        if(found != null) found.xuat();
        else System.out.println("Khong tim thay phieu nhap voi ma " + maPN);
    }

    public String toString() {
        return String.format("%s, %s, %s, %s", 
            maPN, maSP, soLuong, donGia, thanhTien
        );
    }

    public String toStringFormat(String formatter) {
        return String.format(formatter, maPN, maSP, soLuong, donGia, thanhTien);
    }
}