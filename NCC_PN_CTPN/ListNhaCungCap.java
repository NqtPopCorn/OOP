import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

//xoa mot nha cung cap xong thi ma da xoa con dung lai duoc khong?, giai phap la gan bien static cho NhaCungCap
//cach doc data phan cach boi dau phay bang scanner? tl: dung scanner.useDelimiter(String pattern)
public class ListNhaCungCap {
    private NhaCungCap[] list;
    private int n;
    private File fileData;
    final private String maNCC_pattern = "NCC\\d{3,}";

    ListNhaCungCap() {
        list = new NhaCungCap[0];
        n = 0;
        fileData = null;
    }
    ListNhaCungCap(int n) {
        list = new NhaCungCap[n];
        this.n = n;
        fileData = null;
    }
    ListNhaCungCap(String fileData_path) {
        list = new NhaCungCap[0];
        n = 0;
        fileData = new File(fileData_path);
        khoiTaoTuFileData();
    }

    //---------------------NHAP XUAT----------------------
    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so phan tu: ");
        int newSize = scanner.nextInt();
        reSize(newSize);
        for(int i = 0; i < n; i++) {
            System.out.println("Nhap NCC thu " + i+1 +": ");
            String ma = "";
            do {
                System.out.println("Nhap ma: ");
                ma = scanner.nextLine();
                if(!isValidMaNCC(ma)) {
                    System.out.println("Ma sai dinh dang hoac da ton tai!!");
                    System.out.println("Hay thu nhap lai voi: " + NhaCungCap.createValidId());
                    continue;
                }
            } while(!isValidMaNCC(ma));
            list[i] = new NhaCungCap(ma);
            list[i].nhap(ma);
        }
    }
    public void xuat() {
        for(int i = 0; i < n; i++) {
            System.out.println("Nha cung cap thu " + i+1 +": ");
            list[i].xuat();
        }
    }
    public void printTable() {
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
        if(newSize == 0) list = null;
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

    //----------------------THEM----------------------
    public void them() {
        if(list == null) list = new NhaCungCap[1];
        Scanner scanner = new Scanner(System.in);
        NhaCungCap newNCC = null;
        String ma = "";
        int opt;
        System.out.println("Nhap 1 de nhap maNCC hoac 2 de khoi tao maNCC mac dinh");
        opt = scanner.nextInt();
        scanner.nextLine();
        if( opt == 1) {
            do {
                System.out.println("Nhap ma: ");
                ma = scanner.nextLine();
                if(!isValidMaNCC(ma)) {
                    System.out.println("Ma sai dinh dang hoac da ton tai!!");
                    System.out.println("Hay thu nhap lai voi: " + NhaCungCap.createValidId());
                    continue;
                }
            } while(!isValidMaNCC(ma));
            newNCC = new NhaCungCap();
            newNCC.nhap(ma);
        }
        else {
            ma = NhaCungCap.createValidId();
            newNCC = new NhaCungCap();
            newNCC.nhap(ma);
        }
        list = Arrays.copyOf(list, n + 1);
        list[n++] = newNCC;
        //them vao file data neu can
        // if(fileData != null) ghiThemVaoFile(newNCC);
    }
    public void them(NhaCungCap a) {
        if(list == null) list = new NhaCungCap[1];
        if(isValidMaNCC(a.getMaNCC())) {
            list = Arrays.copyOf(list, n + 1);
            list[n++] = a;
            //them vao file data neu can
            // if(fileData != null) ghiThemVaoFile(a);
        }
        else System.out.println("Ma sai format hoac da duoc su dung!");
        return;
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
                break;
            }
        }
        //cap nhat lai fileData neu can
        // if(fileData != null) capNhatFileData();
    }
    public void xoa() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ma NCC can xoa: ");
        String ma = scanner.nextLine();
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNCC().equals(ma)) {
                for(int j = i; j < n - 1; j++) {
                    list[j] = list[j+1];
                }
                list = Arrays.copyOf(list, n - 1);
                n--;
                break;
            }
        }
        // cap nhat lai fileData neu can
        // if(fileData != null) capNhatFileData();
    }
    //-------------------SUA-------------------
    //chua sua maNCC
    public void sua(String maPN) {
        int foundIndex = -1;
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNCC().equals(maPN)) {
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
                //kiem tra ma
                String ma = "";
                do {
                    System.out.println("Nhap ma moi: ");
                    ma = scanner.nextLine();
                    if(!isValidMaNCC(ma)) {
                        System.out.println("Ma sai dinh dang hoac da ton tai!!");
                        System.out.println("Hay thu nhap lai voi: " + NhaCungCap.createValidId());
                        continue;
                    }
                } while(!isValidMaNCC(ma));
                list[foundIndex].setMaNCC(ma);
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
        //cap nhat lai fileData neu can
        // if(fileData != null) capNhatFileData();
    }
    public void sua() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ma NCC can xoa: ");
        String ma = scanner.nextLine();
        sua(ma);
    }
    //--------------TIM KIEM----------------
    public NhaCungCap find(String maPN) {
        for(int i = 0; i < n; i++) {
            if(list[i].getMaNCC().equals(maPN)) {
                return list[i];
            }
        }
        return null;
    }
    
    //------------------GHI FILE-------------------
    public void capNhatFileData() {
        if(fileData == null) {
            System.out.println("this list has null path name");
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
        try {
            Scanner scanner = new Scanner(fileData);
            String line = "";
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                if(line.isEmpty()) continue;
                NhaCungCap newNCC = new NhaCungCap();
                newNCC.khoiTaoTuString(line);
                //kiem tra ptu co trong mang chua roi moi them vao mang?...
                if(isValidMaNCC(newNCC.getMaNCC())) {
                    if(list == null) list = new NhaCungCap[1];
                    else list = Arrays.copyOf(list, n + 1);
                    list[n++] = newNCC;
                    System.out.println(NhaCungCap.createValidId());
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Khong tim thay fileData");
        }
    }
    //-----------------KIEM TRA---------------
    public Boolean isValidMaNCC(String ma) {
        return isMatchedFormatId(ma) && isUniqueId(ma);
    }
    public Boolean isMatchedFormatId(String ma) {
        return ma.matches(maNCC_pattern);
    }
    //kiem tra maNCC co trong mang khong
    public boolean isUniqueId(String ma) {
        for (NhaCungCap ncc : list) {
            if(ncc.getMaNCC().equals(ma)) return false;
        }
        return true;
    }
    //--------------sort neu can----------------



    // //------------in menu--------
    // public void menu() {
    //     Scanner scanner = new Scanner(System.in);
    //     int option;
    //     System.out.println("\t\tMENU List Nha Cung Cap");
    //     System.out.println("0. thoat");
    //     System.out.println("1.Nhap lai danh sach nay");
    //     System.out.println("2.Xuat bang danh sach");
    //     System.out.println("3.Them mot nha cung cap");
    //     System.out.println("4.Xoa nha cung cap");
    //     System.out.println("5.Sua thong tin nha cung cap");
    //     System.out.println("6.Tim kiem nha cung cap");
    //     System.out.println("7.Cap nhat danh sach hien tai vao file data");
    //     System.out.println("8.Khoi tao lai tu file data");
    //     System.out.println("9.Thong ke Nha cung cap");//cung ten, cung dia chi, sdt
    //     System.out.println("10.Xoa danh sach hien tai");
    //     System.out.println("So NCC danh sach hien tai la: " + n);
    //     do {
    //         System.out.print("Lua chon cua ban: ");
    //         option = scanner.nextInt();
    //         scanner.nextLine();
    //         switch(option) {
    //             case 1: {
    //                 nhap();
    //                 break;
    //             } case 2: {
    //                 printTable();
    //                 break;
    //             } case 3: {
    //                 them();
    //                 break;
    //             } case 4: {
    //                 xoa();
    //                 break;
    //             } case 5: {
    //                 sua();
    //                 break;
    //             } case 6: {
    //                 findMenu();//tim dua tren cac field, tra ve danh sach
    //                 break;
    //             } case 7: {
    //                 capNhatFileData();
    //                 break;
    //             } case 8: {
    //                 khoiTaoTuFileData();
    //                 break;
    //             }
    //             case 9: {
    //                 thongkeTen();//dem cac nha cung cap gia tri tren fields
    //                 break;
    //             }
    //             case 10: {
    //                 reSize(0);
    //                 break;
    //             }
    //             default: break;
    //         }
    //     } while (option > 0 && option <= 10);
    // }
}
