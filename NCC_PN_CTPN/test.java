import java.util.Scanner;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        // ListChiTietPN l = new ListChiTietPN("NCC_PN_CTPN\\chitietPN.txt");

        // l.printTable();

        ListNhaCungCap l = new ListNhaCungCap("NCC_PN_CTPN\\nhacungcap.txt");
        l.menu();

        // NhaCungCap[] l = new NhaCungCap[3];
        // l[1] = new NhaCungCap();
        // l[1].khoiTaoTuString("NCC027, intel, 98 ae uoinf sdfj, 432969323");
        
        // System.out.println(NhaCungCap.createValidId());
    }
}
