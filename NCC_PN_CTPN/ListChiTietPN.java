
import java.util.Arrays;
import java.util.Scanner;

public class ListChiTietPN {
    private ChiTietPhieuNhap[] list;
    private int n;

    ListChiTietPN() {
        list = null;
        n = 0;
    }
    ListChiTietPN(int n) {
        list = new ChiTietPhieuNhap[n];
        this.n = n;
    }

    public void nhap() {
        for(int i = 0; i < n; i++) {
            System.out.println("Nhap phieu nhap thu " + i +": ");
            list[i] = new ChiTietPhieuNhap();
            list[i].nhap();
        }
    }
    public void xuat() {
        for(int i = 0; i < n; i++) {
            System.out.println("Phieu nhap thu " + i +": ");
            list[i].xuat();
        }
    }
    public void printTable() {
                           System.out.println("---------------------------------------------");
        System.out.println(String.format("| MAPN | MASP | SOLONG |  DONGIA  |  THANHTIEN  |"));
        for(int i = 0; i < n; i++) {
            System.out.println(String.format("| %4d | %4d | %6d | %8d | %11.3f |", 
            list[i].getMaPN(), list[i].getMaSP(), list[i].getSoLuong(),
            list[i].getDonGia(), list[i].getThanhTien()
            ));
        }
        System.out.println("----------------------------------------------");
    }

    public int size() {
        return n;
    }
    public void resize(int newSize) {
        list = Arrays.copyOf(list, newSize);
        n = newSize;
    }

    public void them() {
        ChiTietPhieuNhap newPn = new ChiTietPhieuNhap();
        System.out.println("Nhap chi tiet phieu nhap can them");
        newPn.nhap();
        list = Arrays.copyOf(list, n + 1);
        list[n++] = newPn;
    }
    public void them(ChiTietPhieuNhap a) {
        list = Arrays.copyOf(list, n + 1);
        list[n++] = a;
    }
    public void xoa(int maPN) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN() == maPN) {
                for(int j = i; j < n - 1; j++) {
                    list[i] = list[i+1];
                }
                list = Arrays.copyOf(list, i - 1);
                break;
            }
        }
    }
    public void sua(int maPN) {
        int foundIndex = -1;
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN() == maPN) {
                foundIndex = i;
                break;
            }
        }
        if(foundIndex == -1) {
            System.out.println("Khong co phieu nhap co ma " + maPN);
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chon truong muon sua: \n\t1. Ma phieu nhap\n\t2. Ma san pham\n\t3. So luong"); 
        System.out.println("\t4. Don gia\n\t5. Thanh tien"); 
        int select = scanner.nextInt();
        switch(select) {
            case 1: {
                System.out.println("Nhap ma phieu nhap: ");
                list[foundIndex].setMaPN(scanner.nextInt());
                break;
            }
            case 2: {
                System.out.println("Nhap ma san pham: ");
                list[foundIndex].setMaSP(scanner.nextInt());
                break;
            }
            case 3: {
                System.out.println("Nhap so luong: ");
                list[foundIndex].setSoLuong(scanner.nextInt());
                break;
            }
            case 4: {
                System.out.println("Nhap don gia: ");
                list[foundIndex].setDonGia(scanner.nextInt());
                break;
            }
            case 5: {
                System.out.println("Nhap thanh tien: ");
                list[foundIndex].setThanhTien(scanner.nextDouble());
                break;
            }
            default: {
                System.out.println("Lua chon khong dung, dung sua");
                return;
            }
        }
    }
    public ListChiTietPN selectAllByMaPN(int maPN) {
        ListChiTietPN select = new ListChiTietPN();
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN() == maPN) {
                select.them(list[i]);
            }
        }
        if(select.size() == 0) return null;
        return select;
    }
    public double tinhTongTien(int maPN) {
        double tongTien = 0;
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN() == maPN) {
                tongTien += list[i].getThanhTien();
            }
        }
        return tongTien;
    }
    
}
