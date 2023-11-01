import java.util.Scanner;

public class NhaCungCap {
    private int maNCC;
    private String tenNCC;
    private String diaChi;
    private String soDT;

    public NhaCungCap() {
        maNCC = 0;
        tenNCC = "";
        diaChi = "";
        soDT = "";
    }
    public NhaCungCap(int maNCC, String tenNCC, String diaChi, String soDT) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.soDT = soDT;
    }
    
    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ma Nha cung cap:");
        maNCC = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhap ten Nha cung cap:");
        tenNCC = scanner.nextLine();
        System.out.println("Nhap dia chi:");
        diaChi = scanner.nextLine();
        System.out.println("Nhap so dien thoai:");
        soDT = scanner.nextLine();
    }
    public void xuat() {
        System.out.println("Ma NCC: " + maNCC);
        System.out.println("Ten NCC: " + tenNCC);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("So dien thoai: " + soDT);
    }
    
    public int getMaNCC() {
        return maNCC;
    }
    public void setMaNCC(int maNCC) {
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
}
