
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import PN_CTPN_NCC.*;
import sanpham.DSDT;
import sanpham.ListCTDT;
import sanpham.MenuDT;

public class test {
    public static void main(String[] args) {
        // ListChiTietPN l = new ListChiTietPN("NCC_PN_CTPN\\chitietPN.txt");

        // l.printTable();

        ListNhaCungCap lNCC = new ListNhaCungCap("data\\nhacungcap.txt");
        ListChiTietPN lCTPN = new ListChiTietPN("data\\chitietPN.txt");
        ListPhieuNhap lPN = new ListPhieuNhap("data\\phieunhap.txt");
        DSDT lDT = new DSDT();
        lDT.docfile();


        // lPN.menu(lNCC, lCTPN);
        // lNCC.menu();
        lCTPN.menu(lPN, lDT);
    }
}
