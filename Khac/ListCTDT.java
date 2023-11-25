package OOP;

import java.util.Arrays;
import java.util.Scanner;

public class ListCTDT {
    private ChiTietDienThoai[] list;
    private int n;

    public ListCTDT() {
        list = null;
        n = 0;
    }
    public ListCTDT(int n) {
        list = new ChiTietDienThoai[n];
        this.n = n;
    }

    public void nhap() {
        for(int i = 0; i < n; i++) {
            System.out.println("Nhap san pham thu " + i +": ");
            list[i] = new ChiTietDienThoai();
            list[i].nhap();
        }
    }
    public void xuat() {
        for(int i = 0; i < n; i++) {
            System.out.println("San pham thu " + i +": ");
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
       ChiTietDienThoai newSP = new ChiTietDienThoai();
        System.out.println("Nhap san pham can them");
        newSP.nhap();
        list = Arrays.copyOf(list, n + 1);
        list[n++] = newSP;
    }
    public void xoa(String maSP) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaSP() == null ? maSP == null : list[i].getMaSP().equals(maSP)) {
                for(int j = i; j < n - 1; j++) {
                    list[i] = list[i+1];
                }
                list = Arrays.copyOf(list, i - 1);
                break;
            }
        }
    }
    public void sua(String maSP) {
        int foundIndex = -1;
        for(int i = 0; i < n; i++) {
            if(list[i].getMaSP() == maSP) {
                foundIndex = i;
                break;
            }
        }
        if(foundIndex == -1) {
            System.out.println("Khong co ma san pham do " + maSP);
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chon truong muon sua: \n\t1. Ma san pham\n\t2. Ma nha san xuat\n\t3. Ma Nha cung cap\n\t4. Ngay san xuat"); 
        int select = scanner.nextInt();
        scanner.nextLine();
        switch(select) {
            case 1: {
                System.out.println("Nhap ma san pham : ");
                list[foundIndex].setMaSP(scanner.nextLine());
                break;
            }
            case 2: {
                System.out.println("Nhap ma nha san xuat: ");
                list[foundIndex].setMaNhaSX(scanner.nextLine());
                break;
            }
            case 3: {
                System.out.println("Nhap ma nha cung cap: ");
                list[foundIndex].setMaNCC(scanner.nextLine());
                break;
            }
            case 4: {
                System.out.println("Nhap ngay san xuat : ");
                list[foundIndex].setNgaySX(scanner.nextLine());
                break;
            }
            default: {
                System.out.println("Lua chon khong dung, dung sua");
                return;
            }
        }
    }
    public ChiTietDienThoai find(String maSP) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaSP() == maSP) {
                return list[i];
            }
        }
        return null;
    }
}
