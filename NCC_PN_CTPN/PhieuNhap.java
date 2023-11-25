import java.util.Scanner;
//1 maPN co the co nhieu chi tiet phieu nhap
public class PhieuNhap {
    private String maPN;
    private String ngay;
    private String maNV;
    private String maNCC;
    private double tongTien;

    PhieuNhap() {
        maPN = "";
        ngay = "";
        maNV = "";
        maNCC = "";
        tongTien = 0;
    }
    PhieuNhap(String maPN, int d, int m, int y, String maNV, String maNCC, ListChiTietPN listCTPN) {
        if(isValidDate(d, m, y)) ngay = String.format("%02d/%02d/%04d", d, m, y);
        else ngay = "";
        this.maPN = maPN;
        this.maNV = maNV;
        this.maNCC = maNCC;
        ListChiTietPN found = listCTPN.selectAllByMaPN(maPN);
        tongTien = found.tinhTongTien();
    }
    PhieuNhap(String maPN, int d, int m, int y, String maNV, String maNCC) {
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
        maPN = scanner.nextLine();
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
        maNV = scanner.nextLine();
        System.out.print("Nhap ma nha cung cap: ");
        maNCC = scanner.nextLine();
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

}
