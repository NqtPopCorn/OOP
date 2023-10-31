import java.util.Scanner;

public class ChiTietPhieuNhap {
    private int maPN;
    private int maSP;
    private int soLuong;
    private int donGia;
    private double thanhTien;

    public ChiTietPhieuNhap() {
        maPN = 0;
        maSP = 0;
        soLuong = 0;
        donGia = 0;
        thanhTien = 0;
    }
    public ChiTietPhieuNhap(int maSP, int soLuong, int donGia) {
        this.maPN = 0;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }
    public ChiTietPhieuNhap(int maPN, int maSP, int soLuong, int donGia) {
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }
    public ChiTietPhieuNhap(int maPN, int maSP, int soLuong, int donGia, double thanhTien) {
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaPN() { return maPN; }
    public void setMaPN(int maPN) { this.maPN = maPN;}
    public int getMaSP() { return maSP; }
    public void setMaSP(int maSP) { this.maSP = maSP; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public int getDonGia() { return donGia; }
    public void setDonGia(int donGia) { this.donGia = donGia; }
    public double getThanhTien() { return thanhTien; }
    public void setThanhTien(double thanhTien) { this.thanhTien = thanhTien; }

    public void nhap() {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Nhap ma phieu nhap: ");
        maPN = scanner.nextInt();
        nhapSP();
    }
    public void nhapSP() {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Nhap ma san pham: ");
        maSP = scanner.nextInt();
        System.out.println("Nhap so luong: ");
        soLuong = scanner.nextInt();
        System.out.println("Nhap don gia: ");
        donGia = scanner.nextInt();
        System.out.println("Nhap thanh tien: ");
        thanhTien = scanner.nextDouble();
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
}