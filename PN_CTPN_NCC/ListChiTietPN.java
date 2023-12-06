package PN_CTPN_NCC;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import sanpham.*;

public class ListChiTietPN {
    private ChiTietPhieuNhap[] list;
    private int n;
    private File fileData;

    public ListChiTietPN() {
        list = new ChiTietPhieuNhap[0];
        n = 0;
        fileData = null;
    }
    public ListChiTietPN(int n) {
        list = new ChiTietPhieuNhap[n];
        for(int i = 0; i < n; i++) list[i] = new ChiTietPhieuNhap();
        this.n = n;
        fileData = null;
    }
    public ListChiTietPN(String fileData_path) {
        list = new ChiTietPhieuNhap[0];
        n = 0;
        fileData = new File(fileData_path);
        khoiTaoTuFileData();
    }

    //-----------------NHAP XUAT-------------
    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Nhap so phan tu: ");
            n = scanner.nextInt();
            scanner.nextLine();
            if(n < 0) System.out.println("Vui long nhap so khong am!!!");
        } while(n < 0);
        list = new ChiTietPhieuNhap[n];
        for(int i = 0; i < n; i++) list[i] = new ChiTietPhieuNhap();

        for(int i = 0; i < n; i++) {
            list[i].nhap();
        }
        //kt ma phieu nhap co trong list thi moi tao chi tiet, khong thi thoi
    }
    public void xuat() {
        for(int i = 0; i < n; i++) {
            System.out.println("Phieu nhap thu " + i +": ");
            list[i].xuat();
        }
    }
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
    public void them(DSDT lDT) {
        String maPN, maSP;
        double donGia;
        int soLuong;
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Nhap chi tiet phieu nhap can them");
        System.out.println("Nhap ma phieu nhap: ");
        maPN = scanner.next();
        System.out.println("Nhap ma san pham: ");
        maSP = scanner.next();
        System.out.println("Nhap so luong: ");
        soLuong = scanner.nextInt();
        //chua kiem tra don gia dung nhu trong ds san pham k
        DienThoai found = lDT.timkiemDT(maSP);
        if(found != null) donGia = found.getDonGia();
        else {
            System.out.println("Nhap don gia: ");
            donGia = scanner.nextDouble();
            scanner.nextLine();
        }
        ChiTietPhieuNhap newPn = new ChiTietPhieuNhap(maPN, maSP, soLuong, donGia);
        list = Arrays.copyOf(list, n + 1);
        list[n++] = newPn;
    }
    public void them(ChiTietPhieuNhap a, DSDT lDT) {
        DienThoai found = lDT.timkiemDT(a.getMaSP());
        if(found != null) a.setDonGia(found.getDonGia());
        list = Arrays.copyOf(list, n + 1);
        list[n++] = a;
    }
    //----------------XOA--------------
    public void xoa(String maPN, String maSP) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN().equals(maPN) && list[i].getMaSP().equals(maSP)) {
                for(int j = i; j < n - 1; j++) {
                    list[j] = list[j+1];
                }
                list = Arrays.copyOf(list, n -1);
                n--;
                System.out.println("Xoa thanh cong");
                return;
            }
        }
        System.out.println("Xoa that bai");
    }
    public void xoa() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap maPN: ");
        String maPN = scanner.nextLine();
        System.out.print("Nhap maSP: ");
        String maSP = scanner.nextLine();
        xoa(maPN, maSP);
    }
    //-----------------SUA----------------
    public void sua() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap maPN: ");
        String maPN = scanner.nextLine();
        System.out.print("Nhap maSP: ");
        String maSP = scanner.nextLine();
        sua(maPN, maSP);
    }
    public void sua(String maPN, String maSP) {
        int foundIndex = -1;
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN().equals(maPN) && list[i].getMaSP().equals(maSP)) {
                foundIndex = i;
                break;
            }
        }
        if(foundIndex == -1) {
            System.out.format("Khong co phieu nhap co maPN %s va maSP %s\n", maPN, maSP);
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
    }
    
    //----------------TIM KIEM, FILTER-------------
    public ChiTietPhieuNhap findByMaPNAndMaSP() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap maPN: ");
        String maPN = scanner.nextLine();
        System.out.print("Nhap maSP: ");
        String maSP = scanner.nextLine();
        return findByMaPNAndMaSP(maPN, maSP);
    }
    public ChiTietPhieuNhap findByMaPNAndMaSP(String maPN, String maSP) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN().equals(maPN) && list[i].getMaSP().equals(maSP)) {
                return list[i];
            }
        }
        return null;
    }
    public ListChiTietPN locTheoMaPN(String maPN) {
        ListChiTietPN select = new ListChiTietPN();//no fileData path
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN().equals(maPN)) {
                select.them(list[i]);
            }
        }
        if(select.size() == 0) return null;
        return select;
    }
    public ListChiTietPN locTheoMaSP(String maSP) {
        ListChiTietPN select = new ListChiTietPN();//no fileData path
        for(int i = 0; i < n; i++) {
            if(list[i].getMaSP().equals(maSP)) {
                select.them(list[i]);
            }
        }
        if(select.size() == 0) return null;
        return select;
    }
    //loc theo khoang thanh tien
    //........
    public ListChiTietPN locTheoThanhTien(double a, double b) {
        ListChiTietPN select = new ListChiTietPN();//no fileData path
        for(int i = 0; i < n; i++) {
            if(list[i].getThanhTien() >= a && list[i].getThanhTien() <= b) {
                select.them(list[i]);
            }
        }
        if(select.size() == 0) return null;
        return select;
    }

    //-------------THONG KE-------------
    public void thongKeTheoMaPN() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        double sum = 0;
        System.out.print("Nhap maPN chinh xac: ");
        String maPN = scanner.nextLine().trim();
        for(int i = 0; i < n; i++) {
            if(list[i].getMaPN().equals(maPN)) {
                count++;
                sum += list[i].getThanhTien();
            }
        }
        System.out.format("Co %d Chi tiet PN cua Phieu nhap %s\n", count, maPN);
        System.out.format("Voi tong tien la %.3f\n", sum);
    }
    public void thongKeTheoMaSP() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        double sum = 0;
        System.out.print("Nhap maSP chinh xac: ");
        String maSP = scanner.nextLine().trim();
        for(int i = 0; i < n; i++) {
            if(list[i].getMaSP().equals(maSP)) {
                count += list[i].getSoLuong();
                sum += list[i].getThanhTien();
            }
        }
        System.out.format("Co %d San Pham ma %s duoc nhap vao\n", count, maSP);
        System.out.format("Voi tong tien la %.3f\n", sum);
    }
    public void thongKeTheoSoLuong() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        System.out.print("Nhap khoang so luong a, b: ");
        int a, b;
        a = scanner.nextInt();
        b = scanner.nextInt();
        for(int i = 0; i < n; i++) {
            if(list[i].getSoLuong() >= a && list[i].getSoLuong() <= b) count++;
        }
        System.out.format("Co %d Chi tiet PN co so san pham ban ra tu %d den %d\n", count, a, b);
    }
    public void thongKeTheoThanhTien() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        System.out.print("Nhap khoang thanh tien a, b: ");
        double a, b;
        a = scanner.nextDouble();
        b = scanner.nextDouble();
        for(int i = 0; i < n; i++) {
            if(list[i].getThanhTien() >= a && list[i].getThanhTien() <= b) count++;
        }
        System.out.format("Co %d Chi tiet PN co thanh tien tu %.3f den %.3f\n", count, a, b);
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
    public void ghiThemVaoFile(NhaCungCap a) {
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
                ChiTietPhieuNhap newCTPN = new ChiTietPhieuNhap();
                newCTPN.khoiTaoTuString(line);
                them(newCTPN);//kt rong neu can
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Khong tim thay fileData hoac fileData = null");
        }
    }

    //--------------------GET SET N--------------
    public int size() {
        return n;
    }
    public void reSize(int newSize) {
        if(newSize == 0) list = new ChiTietPhieuNhap[0];
        else list = Arrays.copyOf(list, newSize);
        n = newSize;
    }
    //---------KHAC-----
    public double tinhTongTien() {
        double tongTien = 0;
        for(int i = 0; i < n; i++) {
            tongTien += list[i].getThanhTien();
        }
        return tongTien;
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
                    ChiTietPhieuNhap temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
    }

    //--------------------------------MENU-------------------
    public void menu(ListPhieuNhap lPN, DSDT lDT) {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nMENU List Chi Tiet Phieu Nhap");
            System.out.println("\t0. THOAT");
            System.out.println("\t1. Nhap lai danh sach nay");
            System.out.println("\t2. Xuat bang danh sach");
            System.out.println("\t3. Them mot Chi Tiet Phieu Nhap");
            System.out.println("\t4. Xoa Chi Tiet Phieu Nhap");
            System.out.println("\t5. Sua thong tin Chi Tiet Phieu Nhap");
            System.out.println("\t6. Tim kiem Chi Tiet Phieu Nhap");
            System.out.println("\t7. Loc CTPN thoa yeu cau");//** */
            System.out.println("\t8. LUU danh sach vao file data hien co");
            System.out.println("\t9. KHOI TAO LAI tu file data hien co");
            System.out.println("\t10. Thong ke Chi Tiet Phieu Nhap");//cung ten, cung dia chi, sdt
            System.out.println("\t11. Xoa danh sach hien tai");
            System.out.println("\t12. Sap xep danh sach hien tai tang dan theo maPN");
            System.out.println("\t13. Xem menu Chi Tiet Phieu Nhap");
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
                    them(lDT);
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
                    ChiTietPhieuNhap found = findByMaPNAndMaSP();
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
                    menuChiTietPN(lPN);
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
        System.out.println("1. Thong ke theo ma phieu nhap");
        System.out.println("2. Thong ke theo ma san pham");
        System.out.println("3. Thong ke theo so luong");
        System.out.println("4. Thong ke theo thanh tien");
        select = scanner.nextInt();
        scanner.nextLine();
        switch(select) {
            case 1: {
                thongKeTheoMaPN();
                break;
            }
            case 2: {
                thongKeTheoMaSP();
                break;
            }
            case 3: {
                thongKeTheoSoLuong();
                break;
            }
            case 4: {
                thongKeTheoThanhTien();
                break;
            }
            default: return;
        }
    }
    public void menuFilter() {
        int select;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\tFILTER");
        System.out.println("1. Loc theo ma phieu nhap");
        System.out.println("2. Loc theo ma san pham");
        System.out.println("3. Loc theo thanh tien");
        System.out.print("Lua chon cua ban: ");
        select = scanner.nextInt();
        scanner.nextLine();
        switch(select) {
            case 1: {
                System.out.print("Nhap ma phieu nhap: ");
                String maPN = scanner.nextLine();
                System.out.println("Danh sach da loc: ");
                locTheoMaPN(maPN).printTable();
                break;
            }
            case 2: {
                System.out.print("Nhap ma san pham: ");
                String maSP = scanner.nextLine();
                System.out.println("Danh sach da loc: ");
                locTheoMaSP(maSP).printTable();
                break;
            }
            case 3: {
                System.out.print("Nhap khoang thanh tien: ");
                double a = scanner.nextDouble();
                double b = scanner.nextDouble();
                System.out.println("Danh sach da loc: ");
                locTheoThanhTien(a, b).printTable();
                break;
            }
            case 4: {
                System.out.println("Nhap khoang a, b: ");
                double a = scanner.nextDouble();
                double b = scanner.nextDouble();
                locTheoThanhTien(a, b).printTable();
                break;
            }

            default: {
                System.out.println("Lua chon khong dung, thoat");
                break;
            }
        }
    }
    public void menuChiTietPN(ListPhieuNhap lPN) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap maPN, maSP can xem menu");
        String maPN = scanner.next();
        String maSP = scanner.next();
        ChiTietPhieuNhap found = findByMaPNAndMaSP(maPN, maSP);
        if(found != null) {
            System.out.format("Menu Chi Tiet Phieu Nhap co maPN %s, maSP %s\n", found.getMaPN(), found.getMaSP());
            System.out.println("1. Xem Phieu Nhap");
            System.out.println("2. Xem San Pham");
            int opt = scanner.nextInt();
            scanner.nextLine();
            switch(opt) {
                case 1: {
                    PhieuNhap pn = lPN.findByMaPN(maPN);
                    if(pn != null) pn.xuat();
                    else System.out.println("Phieu nhap khong ton tai!!!");
                    return;
                }
                case 2: {
                    System.out.println("Day la chi tiet san pham");
                    return;
                }
                default: return;
            }
        }
        else System.out.format("Khong tim thay CTPN %s, cua san pham %s\n", maPN, maSP);
        
        

    }
}
