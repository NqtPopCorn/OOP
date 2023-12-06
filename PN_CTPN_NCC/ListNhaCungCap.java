package PN_CTPN_NCC;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;


interface Validator {
    public boolean isUnique(String ma);
    public boolean isMatchedFormat(String ma);
    public boolean isValid(String ma);
}
//xoa mot nha cung cap xong thi ma da xoa con dung lai duoc khong?, giai phap la gan bien static cho NhaCungCap
//cach doc data phan cach boi dau phay bang scanner? tl: dung scanner.useDelimiter(String pattern)
public class ListNhaCungCap implements Validator {
    private NhaCungCap[] list;
    private int n;
    private File fileData;
    private String pattern;

    public ListNhaCungCap() {
        list = new NhaCungCap[0];
        n = 0;
        fileData = null;
        pattern = "NCC\\d{3,}";
    }
    public ListNhaCungCap(int n) {
        list = new NhaCungCap[n];
        this.n = n;
        pattern = "NCC\\d{3,}";
        for(int i = 0; i < n; i++) list[i] = new NhaCungCap();
        fileData = null;
        
    }
    public ListNhaCungCap(String fileData_path) {
        list = new NhaCungCap[0];
        n = 0;
        pattern = "NCC\\d{3,}";
        fileData = new File(fileData_path);
        khoiTaoTuFileData();
    }

    //---------------------NHAP XUAT----------------------
    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Nhap so phan tu: ");
            n = scanner.nextInt();
            scanner.nextLine();
            if(n < 0) System.out.println("Vui long nhap so khong am!!!");
        } while(n < 0);
        list = new NhaCungCap[n];
        for(int i = 0; i < n; i++) list[i] = new NhaCungCap();

        for(int i = 0; i < n; i++) {
            System.out.print("Nhap NCC thu " + (i+1) +": ");
            String ma = "";
            do {
                System.out.print("Nhap ma NCC: ");
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
        if(n == 0) {
            System.out.println("Danh sach nha cung cap rong\n");
            return;
        }
        String[] columnName = new String[] {
            "MA NCC",
            "TEN NCC",
            "DIA CHI",
            "SDT"
        };
        int[] columnWidth = new int[columnName.length];
        for(int i=0;i<columnName.length;i++) columnWidth[i] = columnName[i].length();
        for(int i = 0; i < n; i++) {
            if(columnWidth[0] < list[i].getMaNCC().length()) columnWidth[0] = list[i].getMaNCC().length();
            if(columnWidth[1] < list[i].getTenNCC().length()) columnWidth[1] = list[i].getTenNCC().length();
            if(columnWidth[2] < list[i].getDiaChi().length()) columnWidth[2] = list[i].getDiaChi().length();
            if(columnWidth[3] < list[i].getSoDT().length()) columnWidth[3] = list[i].getSoDT().length();
        }
        String formatter = String.format("| %%-%ds | %%-%ds | %%-%ds | %%-%ds |\n", 
            columnWidth[0], columnWidth[1], columnWidth[2], columnWidth[3]
        );
        int rowWidth = 13;
        for(int i=0;i<columnName.length;i++) rowWidth+=columnWidth[i];
        System.out.println(String.format("%" + rowWidth +"s", "-").replace(" ", "-"));
        System.out.format(formatter, columnName[0], columnName[1], columnName[2], columnName[3]);
        for(int i = 0; i < n; i++) {
            System.out.print(list[i].toStringFormat(formatter));
        }
        System.out.println(String.format("%" + rowWidth +"s", "-").replace(" ", "-"));
    }

    //----------------------GET SET N--------------------------
    public int size() {
        return n;
    }
    public void reSize(int newSize) {
        if(newSize == 0) list = new NhaCungCap[0];
        else list = Arrays.copyOf(list, newSize);
        n = newSize;
    }
    public void setFileData(String path) {
        try {
            fileData = new File(path);
        }
        catch(NullPointerException e) {
            System.out.println("Path is null or empty");
        }
    }
    public File getFileData() {
        return fileData;
    }
    public String getPatternMaNCC() {
        return pattern;
    }

    //----------------------THEM----------------------
    public void them() {
        Scanner scanner = new Scanner(System.in);
        NhaCungCap newNCC = null;
        String ma = "";
        do {
            System.out.print("Nhap ma NCC: ");
            ma = scanner.nextLine();
            if(!isValid(ma)) {
                System.out.println("Ma sai dinh dang hoac da ton tai!!");
                continue;
            }
        } while(!isValid(ma));
        newNCC = new NhaCungCap();
        newNCC.nhap(ma);
        list = Arrays.copyOf(list, n + 1);
        list[n++] = newNCC;
    }
    public void them(NhaCungCap a) {
        if(isValid(a.getMaNCC())) {
            list = Arrays.copyOf(list, n + 1);
            list[n] = a;
            n++;
        }
        else System.out.println("Ma sai format hoac da duoc su dung!");
    }

    //---------------------XOA----------------------
    public void xoa(String maNCC) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNCC().equals(maNCC)) {
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
        System.out.println("Nhap ma NCC can xoa: ");
        String ma = scanner.nextLine();
        xoa(ma);
    }
    //-------------------SUA-------------------
    //chua sua maNCC
    public void sua(String maNCC) {
        int foundIndex = -1;
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNCC().equals(maNCC)) {
                foundIndex = i;
                break;
            }
        }
        if(foundIndex == -1) {
            System.out.println("Khong co phieu nhap co ma " + maNCC);
            return;
        }
        else {
            System.out.println("NCC can sua: ");
            list[foundIndex].xuat();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chon truong muon sua: \n\t1. Ma NCC\n\t2. Ten NCC\n\t3. Dia chi"); 
        System.out.println("\t4. So dien thoai"); 
        int select = scanner.nextInt();
        scanner.nextLine();
        switch(select) {
            case 1: {
                //kiem tra ma
                String ma = "";
                do {
                    System.out.print("Nhap ma NCC moi: ");
                    ma = scanner.nextLine();
                    if(!isValid(ma)) {
                        System.out.println("Ma sai dinh dang hoac khong co thay doi!!");
                        continue;
                    }
                } while(!isValid(ma));
                list[foundIndex].setMaNCC(ma);
                break;
            }
            case 2: {
                System.out.print("Nhap ten moi: ");
                list[foundIndex].setTenNCC(scanner.nextLine());
                break;
            }
            case 3: {
                System.out.print("Nhap dia chi moi: ");
                list[foundIndex].setDiaChi(scanner.nextLine());
                break;
            }
            case 4: {
                System.out.print("Nhap so dien thoai moi: ");
                list[foundIndex].setSoDT(scanner.nextLine());
                break;
            }
            default: {
                System.out.println("Lua chon khong dung, dung sua");
                return;
            }
        }
        System.out.println("Da thay doi thanh cong");
    }
    public void sua() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ma NCC can sua: ");
        String ma = scanner.nextLine();
        sua(ma);
    }
    //--------------------TIM KIEM, FILTER---------------------
    //da ma la duy nhat nen tra ve mot object duy nhat
    public NhaCungCap findByMaNCC(String maPN) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNCC().equals(maPN)) return list[i];
        }
        return null;
    }
    public NhaCungCap findByMaNCC() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ma NCC: ");
        String ma = scanner.nextLine().trim();
        return findByMaNCC(ma);
    }
    public ListNhaCungCap locTheoMaNCC(String maNCC) {
        if(maNCC.isEmpty()) return this;
        maNCC = maNCC.trim();
        Pattern pattern = Pattern.compile(maNCC, Pattern.CASE_INSENSITIVE);
        ListNhaCungCap result = new ListNhaCungCap();
        for(int i = 0; i < n; i++) {
            if(pattern.matcher(list[i].getMaNCC()).find()) {
                result.them(list[i]);
            }
        }
        return result;
    }
    public ListNhaCungCap locTheoTen(String tenNCC) {
        if(tenNCC.isEmpty()) return this;
        tenNCC = tenNCC.trim();
        Pattern pattern = Pattern.compile(tenNCC, Pattern.CASE_INSENSITIVE);
        ListNhaCungCap result = new ListNhaCungCap();
        for(int i = 0; i < n; i++) {
            if(pattern.matcher(list[i].getTenNCC()).find()) {
                result.them(list[i]);
            }
        }
        return result;
    }
    public ListNhaCungCap locTheoDiaChi(String diaChi) {
        if(diaChi.isEmpty()) return this;
        diaChi = diaChi.trim();
        Pattern pattern = Pattern.compile(diaChi, Pattern.CASE_INSENSITIVE);
        ListNhaCungCap result = new ListNhaCungCap();
        for(int i = 0; i < n; i++) {
            if(pattern.matcher(list[i].getDiaChi()).find()) {
                result.them(list[i]);
            }
        }
        return result;
    }
    public ListNhaCungCap locTheoSDT(String sdt) {
        if(sdt.isEmpty()) return this;
        sdt = sdt.trim();
        Pattern pattern = Pattern.compile(sdt, Pattern.CASE_INSENSITIVE);
        ListNhaCungCap result = new ListNhaCungCap();
        for(int i = 0; i < n; i++) {
            if(pattern.matcher(list[i].getSoDT()).find()) {
                result.them(list[i]);
            }
        }
        return result;
    }
    //-----------THONG KE-------------
    public void thongKeTheoTen() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        System.out.print("Nhap ten chinh xac: ");
        String ten = scanner.nextLine().trim();
        for(int i = 0; i < n; i++) {
            if(list[i].getTenNCC().equals(ten)) count++;
        }
        System.out.format("Co %d NCC co ten %s\n", count, ten);
    }
    public void thongKeTheoDiaChi() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        System.out.print("Nhap dia chi chinh xac: ");
        String diaChi = scanner.nextLine().trim();
        for(int i = 0; i < n; i++) {
            if(list[i].getDiaChi().equals(diaChi)) count++;
        }
        System.out.format("Co %d NCC co dia chi %s\n", count, diaChi);
    }
    public void thongKeTheoSDT() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        System.out.print("Nhap SDT chinh xac: ");
        String soDT = scanner.nextLine().trim();
        for(int i = 0; i < n; i++) {
            if(list[i].getSoDT().equals(soDT)) count++;
        }
        System.out.format("Co %d NCC co ten %s\n", count, soDT);
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
                NhaCungCap newNCC = new NhaCungCap();
                newNCC.khoiTaoTuString(line);
                if(isValid(newNCC.getMaNCC())) {
                    them(newNCC);
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
        return ma.matches(pattern);
    }
    public boolean isUnique(String ma) {
        return findByMaNCC(ma) == null;
    }
    //--------------sort theo ma neu can----------------
    public void sortAscMaNCC() {
        for(int i = 0; i < n-1; i++) {
            Scanner intScan = new Scanner(list[i].getMaNCC());
            int vi = Integer.parseInt(intScan.findInLine("\\d{1,}"));
            intScan.close();
            for(int j = i+1; j <n; j++) {
                intScan = new Scanner(list[j].getMaNCC());
                int vj = Integer.parseInt(intScan.findInLine("\\d{1,}"));
                intScan.close();
                if(vi > vj) {
                    NhaCungCap temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
    }
    //---------------MENU--------------
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nMENU List Nha Cung Cap");
            System.out.println("\t0. THOAT");
            System.out.println("\t1. Nhap lai danh sach nay");
            System.out.println("\t2. Xuat bang danh sach");
            System.out.println("\t3. Them mot nha cung cap");
            System.out.println("\t4. Xoa nha cung cap");
            System.out.println("\t5. Sua thong tin nha cung cap");
            System.out.println("\t6. Tim kiem nha cung cap theo ma");
            System.out.println("\t7. Loc NCC thoa yeu cau");
            System.out.println("\t8. LUU vao file data hien co");
            System.out.println("\t9. Khoi tao lai tu file data hien co");
            System.out.println("\t10. Thong ke Nha cung cap");//cung ten, cung dia chi, sdt
            System.out.println("\t11. Xoa danh sach hien tai");
            System.out.println("\t12. Sap xep danh sach hien tai tang dan theo maNCC");
            System.out.println("So NCC danh sach hien tai la: " + n + "\n");
            System.out.print("Lua chon cua ban: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch(option) {
                case 1: { 
                    nhap();
                    break;
                } 
                case 2: { 
                    xuat();
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
                    NhaCungCap found = findByMaNCC();
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
                    sortAscMaNCC();
                    break;
                }
                default: break;
            }
        } while (option > 0 && option <= 12);
    }
    public void menuThongKe() {
        Scanner scanner = new Scanner(System.in);
        int select;
        System.out.println("------Thong ke-----");
        System.out.println("1. Thong ke theo ten");
        System.out.println("2. Thong ke theo dia chi");
        System.out.println("3. Thong ke theo sdt");
        select = scanner.nextInt();
        scanner.nextLine();
        switch(select) {
            case 1: {
                thongKeTheoTen();
                break;
            }
            case 2: {
                thongKeTheoDiaChi();
                break;
            }
            case 3: {
                thongKeTheoSDT();
                break;
            }
            default: break;
        }
    }
    public void menuFilter() {
        int select;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\tFILTER");
        System.out.println("1. Loc theo ten");
        System.out.println("2. Loc theo dia chi");
        System.out.println("3. Loc theo so dien thoai");
        System.out.print("Lua chon cua ban: ");
        select = scanner.nextInt();
        scanner.nextLine();
        switch(select) {
            case 1: {
                System.out.print("Nhap ten can loc: ");
                String ten = scanner.nextLine();
                System.out.println("\nDanh sach da loc: ");
                locTheoTen(ten).xuat();
                break;
            }
            case 2: {
                System.out.print("Nhap dia chi can loc: ");
                String diaChi = scanner.nextLine();
                System.out.println("Danh sach da loc: ");
                locTheoDiaChi(diaChi).xuat();
                break;
            }
            case 3: {
                System.out.print("Nhap so dien thoai can loc: ");
                String sdt = scanner.nextLine();
                System.out.println("Danh sach da loc: ");
                locTheoSDT(sdt).xuat();
                break;
            }
            default: {
                System.out.println("Lua chon khong dung, thoat");
                break;
            }
        }
    }
}
