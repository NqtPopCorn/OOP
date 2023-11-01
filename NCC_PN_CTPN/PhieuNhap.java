import java.util.Scanner;

public class PhieuNhap {
    private int maPN;
    private String ngay;
    private int maNV;
    private int maNCC;
    private double tongTien;

    PhieuNhap() {
        maPN = 0;
        ngay = "";
        maNV = 0;
        maNCC = 0;
        tongTien = 0;
    }
    PhieuNhap(int maPN, int d, int m, int y, int maNV, int maNCC, ListChiTietPN listCTPN) {
        if(isValidDate(d, m, y)) ngay = String.format("%02d/%02d/%04d", d, m, y);
        else ngay = "";
        this.maPN = maPN;
        this.maNV = maNV;
        this.maNCC = maNCC;
        tongTien = listCTPN.tinhTongTien(maPN);
    }
    PhieuNhap(int maPN, int d, int m, int y, int maNV, int maNCC) {
        if(isValidDate(d, m, y)) ngay = String.format("%02d/%02d/%04d", d, m, y);
        else ngay = "";
        this.maPN = maPN;
        this.maNV = maNV;
        this.maNCC = maNCC;
        tongTien = -1;//bieu thi rang chua tinh tong tien
    }

    //---------------NHAP XUAT-----------
    public void nhap() {
        System.out.println("\nNHAP PHIEU NHAP");
        Scanner scanner = new Scanner(System.in); 
        System.out.print("Nhap ma phieu nhap: ");
        maPN = scanner.nextInt();
        int d, m, y;
        do {
            System.out.print("Nhap ngay, thang, nam: ");
            d = scanner.nextInt();
            m = scanner.nextInt();
            y = scanner.nextInt();
            if(!isValidDate(d, m, y)) System.out.println("Loi nhap ngay!!! Hay nhap lai: ");
        }
        while (!isValidDate(d, m, y));
        ngay = String.format("%02d/%02d/%04d", d, m, y);
        System.out.print("Nhap ma nhan vien: ");
        maNV = scanner.nextInt();
        System.out.print("Nhap ma nha cung cap: ");
        maNCC = scanner.nextInt();
    }
    public void xuat() {
        System.out.println("\nXUAT PHIEU NHAP");
        System.out.println("Ma phieu nhap: " + maPN);
        System.out.println("Ngay: " + (ngay.isEmpty() ? "undefined" : ngay));
        System.out.println("Ma nhan vien: " + maNV);
        System.out.println("Ma nha cung cap: " + maNCC);
    }

    //-------------PHUONG THUC CO LIEN KET---------------
    public void xemChiTiet(ListChiTietPN l) {
        ListChiTietPN found = l.selectAllByMaPN(maPN);
        if(found != null) found.printTable();
        else System.out.println("Khong tim thay chi tiet phieu nhap voi ma " + maPN);
    }
    public void xemNCC(ListNhaCungCap l) {
        NhaCungCap found = l.find(maNCC);
        if(found != null) found.xuat();
        else System.out.println("Khong tim thay nha cung cap voi ma " + maNCC);
    }

    //----------------GET, SET -------------
    public int getMaPN() { return maPN; }
    public void setMaPN(int maPN) { this.maPN = maPN;}

    public int getMaNV() { return maNV; }
    public void setMaNV(int maNV) { this.maNV = maNV; }

    public int getMaNCC() { return maNCC; }
    public void setMaNCC(int maNCC) { this.maNCC = maNCC; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
    
    public String getNgay() { return ngay; }
    public void setNgay(int d, int m, int y) {
        if(isValidDate(d, m, y)) ngay = String.format("%02d/%02d/%04d", d, m, y);
        else System.out.println("Invalid params in setNgay()");
    }

    //------------CHECK NGAY-------------
    public boolean isValidDate(int d, int m, int y) {
        if(d >= 1 && d <= maxDay(m, y) && m >= 1 && m <= 12 && y >= 1) return true;
        return false;
    }
    public int maxDay(int month, int year) {
        switch(month) {
            case 1, 3, 5, 7, 8, 10, 12: return 31;
            case 4, 6, 9, 11: return 30;
            case 2: {
                if(isLeapYear(year)) return 29;
                return 28;
            }
            default: return 0;
        }
    }
    public boolean isLeapYear(int year) {
        if(year%4 == 0 && year%100 != 0 || year%400 == 0) return true;
        return false;
    }

    //----------tinh toan-----------
    public void tinhTongTien(ListChiTietPN l) {
        tongTien = l.tinhTongTien(maPN);
    }
}
