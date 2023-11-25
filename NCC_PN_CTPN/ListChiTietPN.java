//chua kiem tra ma duy nhat
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ListChiTietPN {
    private ChiTietPhieuNhap[] list;
    private int n;
    private File fileData;

    ListChiTietPN() {
        list = null;
        n = 0;
        fileData = null;
    }
    ListChiTietPN(int n) {
        list = new ChiTietPhieuNhap[n];
        this.n = n;
        fileData = null;
    }
    ListChiTietPN(int n, String file_path) {
        list = new ChiTietPhieuNhap[n];
        this.n = n;
        fileData = new File(file_path);
        khoiTaoTuFile();
    }
    ListChiTietPN(String file_path) {
        list = null;
        n = 0;
        fileData = new File(file_path);
        khoiTaoTuFile();
    }

    //-----------------NHAP XUAT-------------
    public void nhap() {
        for(int i = 0; i < n; i++) {
            System.out.println("Nhap phieu nhap thu " + i +": ");
            list[i] = new ChiTietPhieuNhap();
            list[i].nhap();
        }
        if(fileData != null) capNhatVaoFile();
    }
    public void xuat() {
        for(int i = 0; i < n; i++) {
            System.out.println("Phieu nhap thu " + i +": ");
            list[i].xuat();
        }
    }
    // public void printTable() {
    //     System.out.println("-----------------------------------------------------");
    //     System.out.format("|  MAPN  |  MASP  | SOLONG |  DONGIA  |  THANHTIEN  |\n");
    //     System.out.println("-----------------------------------------------------");
    //     for(int i = 0; i < n; i++) {
    //         // System.out.println(list[i].toRowString());
    //     }
    //     System.out.println("-----------------------------------------------------");
    // }
    public void printTable() {
        if(n == 0) {
            System.out.println("Danh sach nha cung cap rong\n");
            return;
        }
        String[] columnName = new String[] {
            "MAPN",
            "MASP",
            "SOLUONG",
            "DONGIA",
            "THANHTIEN"
        };
        int[] columnWidth = new int[columnName.length];
        for(int i=0;i<columnName.length;i++) columnWidth[i] = columnName[i].length();

        for(int i = 0; i < n; i++) {
            int soLuongLength = String.valueOf(list[i].getSoLuong()).length();
            int donGiaLength = String.format("%.03f", list[i].getDonGia()).length();
            int thanhTienLength = String.format("%.03f", list[i].getThanhTien()).length();
            if(columnWidth[0] < list[i].getMaPN().length()) columnWidth[0] = list[i].getMaPN().length();
            if(columnWidth[1] < list[i].getMaSP().length()) columnWidth[1] = list[i].getMaSP().length();
            if(columnWidth[2] < soLuongLength) columnWidth[2] = soLuongLength;
            if(columnWidth[3] < donGiaLength) columnWidth[3] = donGiaLength;
            if(columnWidth[4] < thanhTienLength) columnWidth[4] = thanhTienLength;
        }

        String formatter = String.format("| %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds |\n", 
            columnWidth[0], columnWidth[1], columnWidth[2], columnWidth[3], columnWidth[4]
        );
        int rowWidth = 16;
        for(int i=0;i<columnName.length;i++) rowWidth+=columnWidth[i];

        System.out.println(String.format("%" + rowWidth +"s", "-").replace(" ", "-"));
        System.out.format(formatter, columnName[0],columnName[1],columnName[2],columnName[3],columnName[4]);
        for(int i = 0; i < n; i++) {
            System.out.print(list[i].toStringFormat(formatter));
        }
        System.out.println(String.format("%" + rowWidth +"s", "-").replace(" ", "-"));
    }
    //------------THEM------------
    public void them() {
        if(list == null) list = new ChiTietPhieuNhap[1];
        ChiTietPhieuNhap newPn = new ChiTietPhieuNhap();
        System.out.println("Nhap chi tiet phieu nhap can them");
        newPn.nhap();
        list = Arrays.copyOf(list, n + 1);
        list[n++] = newPn;
        if(fileData != null) capNhatVaoFile();
    }
    public void them(ChiTietPhieuNhap a) {
        if(list == null) list = new ChiTietPhieuNhap[1];
        list = Arrays.copyOf(list, n + 1);
        list[n++] = a;
        if(fileData != null) capNhatVaoFile();
    }
    //----------------XOA--------------
    public void xoa(String maPN) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN().equals(maPN)) {
                for(int j = i; j < n - 1; j++) {
                    list[j] = list[j+1];
                }
                list = Arrays.copyOf(list, n -1);
                n--;
                if(fileData != null) capNhatVaoFile();
                break;
            }
        }
    }
    //-----------------SUA----------------
    public void sua(String maPN) {
        int foundIndex = -1;
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN().equals(maPN)) {
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
        scanner.nextLine();
        switch(select) {
            case 1: {
                System.out.println("Nhap ma phieu nhap: ");
                list[foundIndex].setMaPN(scanner.nextLine());
                break;
            }
            case 2: {
                System.out.println("Nhap ma san pham: ");
                list[foundIndex].setMaSP(scanner.nextLine());
                break;
            }
            case 3: {
                System.out.println("Nhap so luong: ");
                list[foundIndex].setSoLuong(scanner.nextInt());
                break;
            }
            case 4: {
                System.out.println("Nhap don gia: ");
                list[foundIndex].setDonGia(scanner.nextDouble());
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
        if(fileData != null) capNhatVaoFile();
    }
    //----------------LAY CHI TIET QUA MAPN-------------
    public ListChiTietPN selectAllByMaPN(String maPN) {
        ListChiTietPN select = new ListChiTietPN();//no fileData path
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN().equals(maPN)) {
                select.them(list[i]);
            }
        }
        if(select.size() == 0) return null;
        return select;
    }

    public double tinhTongTien() {
        double tongTien = 0;
        for(int i = 0; i < n; i++) {
            tongTien += list[i].getThanhTien();
        }
        return tongTien;
    }
    //-------------------DOC FILE-----------------
    public void khoiTaoTuFile() {
        try {
            Scanner scanner = new Scanner(fileData);
            String line = "";
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                if(line.isEmpty()) continue;
                ChiTietPhieuNhap newCTPN = new ChiTietPhieuNhap();
                newCTPN.nhap(line);
                if(list == null) list = new ChiTietPhieuNhap[1];
                list = Arrays.copyOf(list, n + 1);
                list[n++] = newCTPN;
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file fileData");
        } 
    }
    //-----------------GHI FILE-----------------------
    public void capNhatVaoFile() {
        if(fileData == null) {
            System.out.println("this list has null path name");
            return;
        }
        try {
            FileWriter writer = new FileWriter(fileData);
            String s = "";
            for(int i = 0; i < n; i++) {
                s += list[i].toString() + "\n";
            }
            writer.write(s);
            writer.close();
        }
        catch (IOException e) {
            System.out.println("File khong ton tai");
        }
    }

    //--------------------GET SET N--------------
    public int size() {
        return n;
    }
    public void reSize(int newSize) {
        list = Arrays.copyOf(list, newSize);
        n = newSize;
    }
}
