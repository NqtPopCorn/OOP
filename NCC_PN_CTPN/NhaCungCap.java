
import java.util.Scanner;
public class NhaCungCap {
    private String maNCC;//"[A-Z]{3}\\d{3}"
    private String tenNCC;
    private String diaChi;
    private String soDT;
    //ho tro khoi tao maNCC khong trung
    //gia tri cua id la max cua tat ca ca NCC da khoi tao

    NhaCungCap() {
        maNCC = "";
        tenNCC = "";
        diaChi = "";
        soDT = "";
    }
    NhaCungCap(String maNCC) {
        this.maNCC = maNCC;
        tenNCC = "";
        diaChi = "";
        soDT = "";
    }
    NhaCungCap(String maNCC, String tenNCC, String diaChi, String soDT) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.soDT = soDT;
    }
    
    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ma Nha cung cap:");
        maNCC = scanner.nextLine();
        System.out.println("Nhap ten Nha cung cap:");
        tenNCC = scanner.nextLine();
        System.out.println("Nhap dia chi:");
        diaChi = scanner.nextLine();
        System.out.println("Nhap so dien thoai:");
        soDT = scanner.nextLine();
    }
    public void nhap(String maNCC) {
        this.maNCC = maNCC;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ten Nha cung cap: ");
        tenNCC = scanner.nextLine();
        System.out.print("Nhap dia chi: ");
        diaChi = scanner.nextLine();
        System.out.print("Nhap so dien thoai: ");
        soDT = scanner.nextLine();
    }
    public void xuat() {
        System.out.println("Ma NCC: " + maNCC);
        System.out.println("Ten NCC: " + tenNCC);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("So dien thoai: " + soDT);
    }
    
    public String getMaNCC() {
        return maNCC;
    }
    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }
    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDT() {
        return soDT;
    }
    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String toString() {
        return String.format("%s, %s, %s, %s", 
            maNCC, tenNCC, diaChi, soDT
        );
    }

    public String toStringFormat(String formatter) {
        return String.format(formatter, maNCC, tenNCC, diaChi, soDT);
    }

    public void khoiTaoTuString(String data) {
        Scanner scanner = new Scanner(data);
        scanner.useDelimiter(",\s*|\n");
        maNCC = scanner.next().trim();
        tenNCC = scanner.next().trim();
        diaChi = scanner.next().trim();
        soDT = scanner.next().trim();
        scanner.close();
    }
}
