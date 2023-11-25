package OOP;

import java.util.Arrays;
import java.util.Scanner;

public class ListCTHD {
    public ChiTietHoaDon[] list;
    public int n;

    public ListCTHD() {
        list = null;
        n = 0;
    }
    public ListCTHD(int n) {
        list = new ChiTietHoaDon[n];
        this.n = n;
    }

    public void nhap() {
        for(int i = 0; i < n; i++) {
            System.out.println("Nhap hoa don thu " + i +": ");
            list[i] = new ChiTietHoaDon();
            list[i].nhap();
        }
    }
    public void xuat() {
        for(int i = 0; i < n; i++) {
            System.out.println("Hoa don thu " + i +": ");
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
       ChiTietHoaDon newHD = new ChiTietHoaDon();
        System.out.println("Nhap hoa don can them");
        newHD.nhap();
        list = Arrays.copyOf(list, n + 1);
        list[n++] = newHD;
    }
    public void xoa(String maHD) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaHD() == null ? maHD == null : list[i].getMaHD().equals(maHD)) {
                for(int j = i; j < n - 1; j++) {
                    list[i] = list[i+1];
                }
                list = Arrays.copyOf(list, i - 1);
                break;
            }
        }
    }
    public void sua(String maHD) {
        int foundIndex = -1;
        for(int i = 0; i < n; i++) {
            if(list[i].getMaHD() == maHD) {
                foundIndex = i;
                break;
            }
        }
        if(foundIndex == -1) {
            System.out.println("Khong co ma hoa don do " + maHD);
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chon truong muon sua: \n\t1. Ma hoa don\n\t2. Ma san pham\n\t3. So luong\n\t4. Don gia. "); 
        int select = scanner.nextInt();
        scanner.nextLine();
        switch(select) {
            case 1: {
                System.out.println("Nhap ma hoa don : ");
                list[foundIndex].setMaHD(scanner.nextLine());
                break;
            }
            case 2: {
                System.out.println("Nhap ma san pham : ");
                list[foundIndex].setMaSP(scanner.nextLine());
                break;
            }
            case 3: {
                System.out.println("Nhap so luong : ");
                list[foundIndex].setSoLuong(scanner.nextInt());
                break;
            }
            case 4: {
                System.out.println("Nhap don gia : ");
                list[foundIndex].setDonGia(scanner.nextInt());
                break;
            }
            default: {
                System.out.println("Lua chon khong dung, dung sua");
                return;
            }
        }
    }
    public ChiTietHoaDon find(String maHD) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaHD() == maHD) {
                return list[i];
            }
        }
        return null;
    }
}
