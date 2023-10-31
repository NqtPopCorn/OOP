import java.util.Scanner;

public class PhieuNhap {
    private int maPN;
    private Date ngay;
    private int maNV;
    private int maNCC;
    private int tongTien;

    PhieuNhap() {
        maPN = 0;
        ngay = new Date();
        maNV = 0;
        maNCC = 0;
        tongTien = 0;
    }
    PhieuNhap(int maPN, int d, int m, int y, int maNV, int maNCC, int tongTien) {
        this.maPN = maPN;
        ngay = new Date(d, m, y);
        this.maNV = maNV;
        this.maNCC = maNCC;
        this.tongTien = tongTien;
    }
    PhieuNhap(int d, int m, int y, int maNV, int maNCC, int tongTien) {
        ngay = new Date(d, m, y);
        this.maNV = maNV;
        this.maNCC = maNCC;
        this.tongTien = tongTien;
    }

    public int getMaPN() { return maPN; }
    public void setMaPN(int maPN) { this.maPN = maPN;}
    public int getMaNV() { return maNV; }
    public void setMaNV(int maNV) { this.maNV = maNV; }
    public int getMaNCC() { return maNCC; }
    public void setMaNCC(int maNCC) { this.maNCC = maNCC; }
    public int getTongTien() { return tongTien; }
    public void setTongTien(int tongTien) { this.tongTien = tongTien; }
    public Date getNgay() { return ngay; }
    public void setNgay(Date ngay) { this.ngay = ngay; }
    public void setNgay(int d, int m, int y) { ngay.set(d, m, y); }

    public void nhap() {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Nhap ma phieu nhap: ");
        maPN = scanner.nextInt();
        System.out.println("Nhap ngay: ");
        ngay.nhap();
        System.out.println("Nhap ma nhan vien: ");
        maNV = scanner.nextInt();
        System.out.println("Nhap ma nha cung cap: ");
        maNCC = scanner.nextInt();
    }

    public void xuat() {
        System.out.println("Ma phieu nhap: " + maPN);
        System.out.print("Ngay: "); ngay.xuat();
        System.out.println("Ma nhan vien: " + maNV);
        System.out.println("Ma nha cung cap: " + maNCC);
    }

    public void xemChiTiet(ListChiTietPN l) {
        ChiTietPhieuNhap found = l.find(maPN);
        if(found != null) found.xuat();
        else System.out.println("Khong tim thay chi tiet phieu nhap voi ma " + maPN);
    }
    public void xemNCC(ListNhaCungCap l) {
        NhaCungCap found = l.find(maNCC);
        if(found != null) found.xuat();
        else System.out.println("Khong tim thay nha cung cap voi ma " + maNCC);
    }
}
