import java.util.Arrays;
import java.util.Scanner;

public class ListPhieuNhap {
    private PhieuNhap[] list;
    private int n;

    public ListPhieuNhap() {
        list = null;
        n = 0;
    }
    public ListPhieuNhap(int n) {
        list = new PhieuNhap[n];
        this.n = n;
    }

    public void nhap() {
        for(int i = 0; i < n; i++) {
            System.out.println("Nhap PN thu " + i +": ");
            list[i] = new PhieuNhap();
            list[i].nhap();
        }
    }
    public void xuat() {
        for(int i = 0; i < n; i++) {
            System.out.println("Phieu nhap thu " + i +": ");
            list[i].xuat();
        }
    }

    public int size() {
        return n;
    }
    public void reSize(int newSize) {
        list = Arrays.copyOf(list, newSize);
        n = newSize;
    }

    public void them() {
        PhieuNhap newPN = new PhieuNhap();
        System.out.println("Nhap phieu nhap can them");
        newPN.nhap();
        list = Arrays.copyOf(list, n + 1);
        list[n++] = newPN;
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
        System.out.println("Chon truong muon sua: \n\t1. Ma Phieu nhap\n\t2. Ma Nhan vien\n\t3. Ma Nha cung cap"); 
        System.out.println("\t4. Ngay lap phieu\n\t5. Tong tien"); 
        int select = scanner.nextInt();
        scanner.nextLine();
        switch(select) {
            case 1: {
                System.out.println("Nhap ma nhieu nhap: ");
                list[foundIndex].setMaPN(scanner.nextInt());
                break;
            }
            case 2: {
                System.out.println("Nhap ma nhan vien: ");
                list[foundIndex].setMaNV(scanner.nextInt());
                break;
            }
            case 3: {
                System.out.println("Nhap ma nha cung cap: ");
                list[foundIndex].setMaNCC(scanner.nextInt());
                break;
            }
            case 4: {
                int d, m, y;
                do {
                    System.out.println("Nhap ngay lap phieu: ");
                    d = scanner.nextInt();
                    m = scanner.nextInt();
                    y = scanner.nextInt();
                    if(list[foundIndex].isValidDate(d, m, y) == false) 
                        System.out.println("Loi nhap ngay!!!, nhap lai");
                }
                while (list[foundIndex].isValidDate(d, m, y) == false);
                list[foundIndex].setNgay(d, m, y);
                break;
            }
            case 5: {
                System.out.println("Nhap Tong tien: ");
                list[foundIndex].setTongTien(scanner.nextInt());
                break;
            }
            default: {
                System.out.println("Lua chon khong dung, dung sua");
                return;
            }
        }
    }
    public PhieuNhap find(int maPN) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN() == maPN) {
                return list[i];
            }
        }
        return null;
    }
}
