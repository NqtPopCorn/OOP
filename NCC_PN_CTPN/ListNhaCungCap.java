import java.util.Arrays;
import java.util.Scanner;

public class ListNhaCungCap {
    private NhaCungCap[] list;
    private int n;

    public ListNhaCungCap() {
        list = null;
        n = 0;
    }
    public ListNhaCungCap(int n) {
        list = new NhaCungCap[n];
        this.n = n;
    }

    public void nhap() {
        for(int i = 0; i < n; i++) {
            System.out.println("Nhap NCC thu " + i +": ");
            list[i] = new NhaCungCap();
            list[i].nhap();
        }
    }
    public void xuat() {
        for(int i = 0; i < n; i++) {
            System.out.println("Nha cung cap thu " + i +": ");
            list[i] = new NhaCungCap();
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
        NhaCungCap newNCC = new NhaCungCap();
        System.out.println("Nhap NCC can them");
        newNCC.nhap();
        list = Arrays.copyOf(list, n + 1);
        list[n++] = newNCC;
    }
    public void xoa(int maPN) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNCC() == maPN) {
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
            if(list[i].getMaNCC() == maPN) {
                foundIndex = i;
                break;
            }
        }
        if(foundIndex == -1) {
            System.out.println("Khong co phieu nhap co ma " + maPN);
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chon truong muon sua: \n\t1. Ma NCC\n\t2. Ten NCC\n\t3. Dia chi"); 
        System.out.println("\t4. So dien thoai"); 
        int select = scanner.nextInt();
        scanner.nextLine();
        switch(select) {
            case 1: {
                System.out.println("Nhap ma NCC: ");
                list[foundIndex].setMaNCC(scanner.nextInt());
                break;
            }
            case 2: {
                System.out.println("Nhap ten NCC: ");
                list[foundIndex].setTenNCC(scanner.nextLine());
                break;
            }
            case 3: {
                System.out.println("Nhap dia chi: ");
                list[foundIndex].setDiaChi(scanner.nextLine());
                break;
            }
            case 4: {
                System.out.println("Nhap so dien thoai: ");
                list[foundIndex].setSoDT(scanner.nextLine());
                break;
            }
            default: {
                System.out.println("Lua chon khong dung, dung sua");
                return;
            }
        }
    }
    public NhaCungCap find(int maPN) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNCC() == maPN) {
                return list[i];
            }
        }
        return null;
    }
}
