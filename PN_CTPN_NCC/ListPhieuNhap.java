package PN_CTPN_NCC;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ListPhieuNhap implements Validator{
    private PhieuNhap[] list;
    private int n;
    private File fileData;
    private String maPN_pattern;
    
    public ListPhieuNhap() {
        list = new PhieuNhap[0];
        n = 0;
        fileData = null;
        maPN_pattern = "PN\\d{3,}";
    }
    public ListPhieuNhap(int n) {
        list = new PhieuNhap[n];
        this.n = n;
        fileData = null;
        maPN_pattern = "PN\\d{3,}";
    }
    public ListPhieuNhap(String fileData_path) {
        list = new PhieuNhap[0];
        n = 0;
        maPN_pattern = "PN\\d{3,}";
        fileData = new File(fileData_path);
        khoiTaoTuFileData();
    }

    //------------------NHAP XUAT-------------
    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Nhap so phan tu: ");
            n = scanner.nextInt();
            scanner.nextLine();
            if(n < 0) System.out.println("Vui long nhap so khong am!!!");
        } while(n < 0);
        list = new PhieuNhap[n];
        for(int i = 0; i < n; i++) list[i] = new PhieuNhap();

        for(int i = 0; i < n; i++) {
            System.out.print("Nhap PN thu " + (i+1) +": ");
            String ma = "";
            do {
                System.out.print("Nhap ma PN: ");
                ma = scanner.nextLine();
                if(!isValid(ma)) {
                    System.out.println("Ma sai dinh dang hoac da ton tai!!");
                    continue;
                }
            } while(!isValid(ma));
            list[i].nhap(ma);
        }
    }
    public void xuat() {
        if(n == 0) {System.out.println("Danh sach rong"); return;}
        for(int i = 0; i < n; i++) {
            System.out.println("Phieu nhap thu " + i +": ");
            list[i].xuat();
        }
    }
    public void printTable() {
        if(n == 0) {
            System.out.println("Danh sach PN rong\n");
            return;
        }
        String[] columnName = new String[] {
            "MaPhieuNhap",
            "Ngay",
            "MaNV",
            "MANCC",
            "TongTien"
        };
        int[] columnWidth = new int[columnName.length];
        for(int i=0;i<columnName.length;i++) columnWidth[i] = columnName[i].length();
        for(int i = 0; i < n; i++) {
            if(columnWidth[0] < list[i].getMaPN().length()) columnWidth[0] = list[i].getMaPN().length();
            if(columnWidth[1] < list[i].getNgay().length()) columnWidth[1] = list[i].getNgay().length();
            if(columnWidth[2] < list[i].getMaNV().length()) columnWidth[2] = list[i].getMaNV().length();
            if(columnWidth[3] < list[i].getMaNCC().length()) columnWidth[3] = list[i].getMaNCC().length();
            int lengthTongTien = String.format("%.3f",list[i].getTongTien()).length();
            if(columnWidth[4] < lengthTongTien) columnWidth[4] = lengthTongTien;
        }
        String formatter = String.format("| %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds |\n", 
            columnWidth[0], columnWidth[1], columnWidth[2], columnWidth[3], columnWidth[4]
        );
        int rowWidth = 16;
        for(int i=0;i<columnName.length;i++) rowWidth+=columnWidth[i];
        System.out.println(String.format("%" + rowWidth +"s", "-").replace(" ", "-"));
        System.out.format(formatter, columnName[0], columnName[1], columnName[2], columnName[3], columnName[4]);
        for(int i = 0; i < n; i++) {
            System.out.print(list[i].toStringFormat(formatter));
        }
        System.out.println(String.format("%" + rowWidth +"s", "-").replace(" ", "-"));
    }
    //---------------GET SET-----------------
    public int size() {
        return n;
    }
    public void reSize(int newSize) {
        if(newSize == 0) list = new PhieuNhap[0];
        else list = Arrays.copyOf(list, newSize);
        n = newSize;
    }
    //--------------THEM-------------
    public void them() {
        String ma = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Nhap ma PN: ");
            ma = scanner.nextLine();
            if(!isValid(ma)) System.out.println("Ma sai format hoac da duoc su dung!, nhap lai");
        } while(!isValid(ma));
        PhieuNhap newPN = new PhieuNhap();
        newPN.nhap(ma);
        list = Arrays.copyOf(list, n + 1);
        list[n++] = newPN;
    }
    public void them(PhieuNhap a) {
        if(isValid(a.getMaPN())) {
            list = Arrays.copyOf(list, n + 1);
            list[n] = a;
            n++;
        }
        else System.out.println("Ma sai format hoac da duoc su dung!");
    }
    //---------------XOA-------------
    public void xoa(String maPN) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN().equals(maPN)) {
                for(int j = i; j < n - 1; j++) {
                    list[j] = list[j+1];
                }
                list = Arrays.copyOf(list, n - 1);
                n--;
                System.out.println("Xoa thanh cong");
                return;
            }
        }
        System.out.println("Xoa that bai");
    }
    public void xoa() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ma PN can xoa: ");
        String ma = scanner.nextLine();
        xoa(ma);
    }
    //--------------SUA-------------
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
        System.out.println("Chon truong muon sua: \n\t1. Ma Phieu nhap\n\t2. Ma Nhan vien\n\t3. Ma Nha cung cap"); 
        System.out.println("\t4. Ngay lap phieu\n\t5. Tong tien"); 
        int select = scanner.nextInt();
        scanner.nextLine();
        switch(select) {
            case 1: {
                System.out.println("Nhap ma nhieu nhap: ");
                list[foundIndex].setMaPN(scanner.nextLine());
                break;
            }
            case 2: {
                System.out.println("Nhap ma nhan vien: ");
                list[foundIndex].setMaNV(scanner.nextLine());
                break;
            }
            case 3: {
                System.out.println("Nhap ma nha cung cap: ");
                list[foundIndex].setMaNCC(scanner.nextLine());
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
    public void sua() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ma PN can sua: ");
        String ma = scanner.nextLine();
        sua(ma);
    }
    //------------ TIM KIEM, FILTER -----------
    public PhieuNhap findByMaPN(String maPN) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN().equals(maPN)) {
                return list[i];
            }
        }
        return null;
    }
    public PhieuNhap findByMaPN() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ma PN: ");
        String ma = scanner.nextLine().trim();
        return findByMaPN(ma);
    }
    public ListPhieuNhap locTheoNgay(String ngay) {
        ListPhieuNhap result = new ListPhieuNhap();
        for(int i = 0; i < n; i++) {
            if(list[i].getNgay().indexOf(ngay) >= 0) {
                result.them(list[i]);
            }
        }
        return result;
    }
    public ListPhieuNhap locTheoNgay(int d, int m, int y) {
        String ngay = String.format("%02d/%02d/%04d", d, m, y);
        ListPhieuNhap result = new ListPhieuNhap();
        for(int i = 0; i < n; i++) {
            if(list[i].getNgay().indexOf(ngay) >= 0) {
                result.them(list[i]);
            }
        }
        return result;
    }
    public ListPhieuNhap locTheoMaNV(String maNV) {
        ListPhieuNhap result = new ListPhieuNhap();
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNV().indexOf(maNV) >= 0) {
                result.them(list[i]);
            }
        }
        return result;
    }
    public ListPhieuNhap locTheoMaNCC(String maNCC) {
        ListPhieuNhap result = new ListPhieuNhap();
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNCC().indexOf(maNCC) >= 0) {
                result.them(list[i]);
            }
        }
        return result;
    }
    public ListPhieuNhap locTheoTongTien(double a, double b) {
        ListPhieuNhap result = new ListPhieuNhap();
        for(int i = 0; i < n; i++) {
            if(list[i].getTongTien() >= a && list[i].getTongTien() <= b) {
                result.them(list[i]);
            }
        }
        return result;
    }
    //-----------THONG KE------------
    public void thongKeTheoNgay(String ngay) {
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNV().equals(ngay)) {
                count++;
            }
        }
        System.out.format("Co %d phieu nhap thuc hien trong ngay %s\n", count, ngay);
    }
    public void thongKeTheoNgay(int d, int m, int y) {
        String ngay = String.format("%02d/%02d/%04d", d, m, y);
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNV().equals(ngay)) {
                count++;
            }
        }
        System.out.format("Co %d phieu nhap thuc hien trong ngay %s\n", count, ngay);
    }
    public void thongKeTheoMaNV(String maNV) {
        int count = 0;
        double sum = 0;
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNV().equals(maNV)) {
                count++;
                sum += list[i].getTongTien();
            }
        }
        System.out.format("Nhan vien %s thuc hien %d phieu nhap voi tong tien: %.3f\n", maNV, count, sum);
    }
    public void thongKeTheoMaNCC(String maNCC) {
        int count = 0;
        double sum = 0;
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNCC().equals(maNCC)) {
                count++;
                sum += list[i].getTongTien();
            }
        }
        System.out.format("Co %d phieu nhap tu NCC %s voi tong tien %.3f\n", maNCC, count, sum);
    }
    public void thongKeTheoTongTien(double a, double b) {
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(list[i].getTongTien() >= a && list[i].getTongTien() <= b) {
                count++;
            }
        }
        System.out.format("Co %d phieu nhap co tong tien tu %.3f den %.3f\n", count, a, b);
    }

    //------------------GHI FILE-------------------
    public void capNhatFileData() {
        if(fileData == null) {
            System.out.println("danh sach nay khong co file data");
            return;
        }
        try {
            FileWriter writer = new FileWriter(fileData);
            String s = "";
            for(int i = 0; i < n; i++) {
                if(list[i] != null) s += list[i].toString() + "\n";
            }
            writer.write(s);
            writer.close();
        }
        catch (IOException e) {
            System.out.println("File khong ton tai");
        }
    }
    public void ghiThemVaoFile(PhieuNhap a) {
        try {
            FileWriter writer = new FileWriter(fileData, true);
            if(a != null) writer.write("\n" + a.toString()+ "\n");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("File khong ton tai");
        }
    }
    //  ---------------------DOC FILE----------------------
    public void khoiTaoTuFileData() {
        //GIA DINH FILE DUNG DINH DANG VA MOI MA XUAT HIEN 1 LAN
        //NEU THAY MA DA XUAT HIEN TREN 1 LAN THI BO QUA DONG DO
        try {
            if(fileData == null) throw new FileNotFoundException();
            Scanner scanner = new Scanner(fileData);
            String line = "";
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                if(line.isEmpty()) continue;
                PhieuNhap newPN = new PhieuNhap();
                newPN.khoiTaoTuString(line);
                if(isValid(newPN.getMaPN())) {
                    them(newPN);
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Khong tim thay fileData hoac fileData = null");
        }
    }
    //-----------------KIEM TRA---------------
    public boolean isValid(String ma) {
        return isMatchedFormat(ma) && isUnique(ma);
    }
    public boolean isMatchedFormat(String ma) {
        return ma.matches(maPN_pattern);
    }
    public boolean isUnique(String ma) {
        return findByMaPN(ma) == null;
    }

    //--------------sort theo ma neu can----------------
    public void sortAscMaPN() {
        for(int i = 0; i < n-1; i++) {
            Scanner intScan = new Scanner(list[i].getMaPN());
            int vi = Integer.parseInt(intScan.findInLine("\\d{1,}"));
            intScan.close();
            for(int j = i+1; j <n; j++) {
                intScan = new Scanner(list[j].getMaPN());
                int vj = Integer.parseInt(intScan.findInLine("\\d{1,}"));
                intScan.close();
                if(vi > vj) {
                    PhieuNhap temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
    }
    //--------------------------MENU-----------------------
    public void menu(ListNhaCungCap lNCC, ListChiTietPN lCTPN) {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nMENU List Phieu Nhap");
            System.out.println("\t0. THOAT");
            System.out.println("\t1. Nhap lai danh sach nay");
            System.out.println("\t2. Xuat bang danh sach");
            System.out.println("\t3. Them mot phieu nhap");
            System.out.println("\t4. Xoa phieu nhap");
            System.out.println("\t5. Sua thong tin phieu nhap");
            System.out.println("\t6. Tim kiem phieu nhap theo ma");
            System.out.println("\t7. Loc PN thoa yeu cau");//** */
            System.out.println("\t8. Cap nhat danh sach hien tai vao file data hien co");
            System.out.println("\t9. Khoi tao lai tu file data hien co");
            System.out.println("\t10. Thong ke phieu nhap");//cung ten, cung dia chi, sdt
            System.out.println("\t11. Xoa danh sach hien tai");
            System.out.println("\t12. Sap xep danh sach hien tai tang dan theo maPN");
            System.out.println("\t13. Xem menu phieu nhap");
            System.out.println("So PN danh sach hien tai la: " + n + "\n");
            System.out.print("Lua chon cua ban: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch(option) {
                case 1: {
                    nhap();
                    break;
                } 
                case 2: {
                    printTable();
                    break;
                } 
                case 3: {
                    them();
                    break;
                } 
                case 4: {
                    xoa();
                    break;
                } 
                case 5: {
                    sua();
                    break;
                } 
                case 6: {
                    PhieuNhap found = findByMaPN();
                    System.out.println("\n----Ket qua tim kiem----");
                    if(found != null) found.xuat();
                    else System.out.println("khong tim thay!!!");
                    break;
                }
                case 7: {
                    menuFilter();
                    break;
                }
                case 8: {
                    capNhatFileData();
                    break;
                } 
                case 9: {
                    khoiTaoTuFileData();
                    break;
                }
                case 10: {
                    menuThongKe();
                    break;
                }
                case 11: {
                    reSize(0);
                    break;
                }
                case 12: {
                    sortAscMaPN();
                    break;
                }
                case 13: {
                    menuPhieuNhap(lNCC, lCTPN);
                    break;
                }
                default: break;
            }
        } while (option > 0 && option <= 13);
    }
    public void menuThongKe() {
        Scanner scanner = new Scanner(System.in);
        int select;
        System.out.println("------Thong ke-----");
        System.out.println("1. Thong ke theo ngay");
        System.out.println("2. Thong ke theo ma nhan vien");
        System.out.println("3. Thong ke theo ma nha cung cap");
        System.out.println("4. Thong ke theo tong tien");
        select = scanner.nextInt();
        scanner.nextLine();
        switch(select) {
            case 1: {
                System.out.print("Nhap ngay, thang nam: ");
                int d = scanner.nextInt();
                int m = scanner.nextInt();
                int y = scanner.nextInt();
                thongKeTheoNgay(d, m, y);
                break;
            }
            case 2: {
                System.out.print("Nhap ma nhan vien: ");
                String ma = scanner.nextLine();
                thongKeTheoMaNV(ma);
                break;
            }
            case 3: {
                System.out.print("Nhap ma nha cung cap: ");
                String ma = scanner.nextLine();
                thongKeTheoMaNCC(ma);
                break;
            }
            case 4: {
                System.out.println("Nhap khoang a, b: ");
                double a = scanner.nextDouble();
                double b = scanner.nextDouble();
                thongKeTheoTongTien(a, b);
                break;
            }
            default: return;
        }
    }
    public void menuFilter() {
        int select;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\tFILTER");
        System.out.println("1. Loc theo ngay");
        System.out.println("2. Loc theo ma nhan vien");
        System.out.println("3. Loc theo ma nha cung cap");
        System.out.println("4. Loc theo tong tien");
        System.out.print("Lua chon cua ban: ");
        select = scanner.nextInt();
        scanner.nextLine();
        switch(select) {
            case 1: {
                System.out.print("Nhap ngay, thang, nam can loc: ");
                int d = scanner.nextInt();
                int m = scanner.nextInt();
                int y = scanner.nextInt();
                System.out.println("Danh sach da loc: ");
                locTheoNgay(d, m, y).printTable();
                break;
            }
            case 2: {
                System.out.print("Nhap ma Nhan Vien: ");
                String ma = scanner.nextLine();
                System.out.println("Danh sach da loc: ");
                locTheoMaNV(ma).printTable();
                break;
            }
            case 3: {
                System.out.print("Nhap ma Nha Cung Cap: ");
                String ma = scanner.nextLine();
                System.out.println("Danh sach da loc: ");
                locTheoMaNCC(ma).printTable();
                break;
            }
            case 4: {
                System.out.println("Nhap khoang a, b: ");
                double a = scanner.nextDouble();
                double b = scanner.nextDouble();
                locTheoTongTien(a, b).printTable();
                break;
            }

            default: {
                System.out.println("Lua chon khong dung, thoat");
                break;
            }
        }
    }
    public void menuPhieuNhap(ListNhaCungCap lNCC, ListChiTietPN lCTPN) {
        System.out.println("Nhap ma PN can xem menu");
        String ma = "";
        Scanner scanner = new Scanner(System.in);
        ma = scanner.nextLine();
        PhieuNhap found = findByMaPN(ma);
        if(found != null) {
            System.out.println("Menu phieu nhap " + ma);
            System.out.println("1. Xem Nha cung cap");
            System.out.println("2. Xem Chi Tiet Phieu Nhap");
            int opt = scanner.nextInt();
            scanner.nextLine();
            switch(opt) {
                case 1: {
                    found.xemNCC(lNCC);
                    return;
                }
                case 2: {
                    found.xemChiTietPN(lCTPN);
                    return;
                }
                default: return;
            }
        }
    }
}
