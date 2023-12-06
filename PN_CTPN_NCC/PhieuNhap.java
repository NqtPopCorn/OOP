package PN_CTPN_NCC;

import java.util.Scanner;

//1 maPN co the co nhieu chi tiet phieu nhap
public class PhieuNhap {
    private String maPN;
    private String ngay;
    private String maNV;
    private String maNCC;
    private double tongTien;

    public PhieuNhap() {
        maPN = "";
        ngay = "";
        maNV = "";
        maNCC = "";
        tongTien = 0;
    }
    public PhieuNhap(String maPN, int d, int m, int y, String maNV, String maNCC, ListChiTietPN listCTPN) {
        if(isValidDate(d, m, y)) ngay = String.format("%04d-%02d-%02d", y, m, d);
        else ngay = "";
        this.maPN = maPN;
        this.maNV = maNV;
        this.maNCC = maNCC;
        ListChiTietPN found = listCTPN.locTheoMaPN(maPN);
        tongTien = found.tinhTongTien();
    }
    public PhieuNhap(String maPN, int d, int m, int y, String maNV, String maNCC) {
        if(isValidDate(d, m, y)) ngay = String.format("%04d-%02d-%02d", y, m, d);
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
        maPN = scanner.nextLine();
        nhapNgay();
        System.out.print("Nhap ma nhan vien: ");
        maNV = scanner.nextLine();
        System.out.print("Nhap ma nha cung cap: ");
        maNCC = scanner.nextLine();
    }
    public void nhapNgay() {
        Scanner scanner = new Scanner(System.in);
        int d, m, y;
        do {
            System.out.print("Nhap ngay, thang, nam: ");
            d = scanner.nextInt();
            m = scanner.nextInt();
            y = scanner.nextInt();
            if(!isValidDate(d, m, y)) System.out.println("Loi nhap ngay!!! Hay nhap lai: ");
        }
        while (!isValidDate(d, m, y));
        ngay = String.format("%04d-%02d-%02d", y, m, d);
    }
    public void nhap(String maPN) {
        this.maPN = maPN;
        Scanner scanner = new Scanner(System.in);
        do {
            nhapNgay();
            System.out.print("Nhap ma nhan vien: ");
            maNV = scanner.nextLine();
            System.out.print("Nhap ma nha cung cap: ");
            maNCC = scanner.nextLine();
            System.out.print("Nhap tong tien: ");
            tongTien = scanner.nextDouble();
            if(!isValid()) System.out.println("Cac truong khong duoc rong va so khong duoc am, nhap lai");
        } while(!isValid());
    }
    public void xuat() {
        System.out.println("\nXUAT PHIEU NHAP");
        System.out.println("Ma phieu nhap: " + maPN);
        System.out.println("Ngay: " + (ngay.isEmpty() ? "undefined" : ngay));
        System.out.println("Ma nhan vien: " + maNV);
        System.out.println("Ma nha cung cap: " + maNCC);
    }

    //-------------PHUONG THUC CO LIEN KET---------------
    public void xemChiTietPN(ListChiTietPN l) {
        ListChiTietPN found = l.locTheoMaPN(maPN);
        if(found != null) {
            found.printTable();
            System.out.format("Tong tien: %.3f", tongTien);
        }
        else System.out.println("Khong tim thay chi tiet phieu nhap voi ma " + maPN);
    }
    public void xemNCC(ListNhaCungCap l) {
        NhaCungCap found = l.findByMaNCC(maNCC);
        if(found != null) found.xuat();
        else System.out.println("Khong tim thay nha cung cap voi ma " + maNCC);
    }

    //----------------GET, SET -------------
    public String getMaPN() { return maPN; }
    public void setMaPN(String maPN) { this.maPN = maPN;}

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getMaNCC() { return maNCC; }
    public void setMaNCC(String maNCC) { this.maNCC = maNCC; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
    
    public String getNgay() { return ngay; }
    public void setNgay(int d, int m, int y) {
        if(isValidDate(d, m, y)) ngay = String.format("%04d-%02d-%02d", y, m, d);
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
    public static String createDate(int d, int m, int y) {
        return String.format("%04d-%02d-%02d", y, m, d);
    }

    public void khoiTaoTuString(String data) {
        Scanner scanner = new Scanner(data);
        scanner.useDelimiter(";\s{0,}|\n");
        maPN = scanner.next().trim();
        ngay = scanner.next().trim();
        maNV = scanner.next().trim();
        maNCC = scanner.next().trim();
        tongTien = Double.parseDouble(scanner.next());
        scanner.close();
    }
    public String toString() {
        return String.format("%s; %s; %s; %s; %.3f", maPN, ngay, maNV, maNCC, tongTien);
    }
    public boolean isValid() {
        return !maPN.isEmpty() && !ngay.isEmpty() && !maNV.isEmpty()
        && !maNCC.isEmpty() && tongTien >= 0;
    }
    public String toStringFormat(String formatter) {
        return String.format(formatter, maPN, ngay, maNV, maNCC, String.format("%.3f", tongTien));
    }
    
}
