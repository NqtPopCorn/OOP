package sanpham;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MenuDT {
    public void main(String[] args) {
        DSDT dsdt=new DSDT();
        int l=0;
        Scanner scanner=new Scanner(System.in);
        while(l!=7){
            System.out.println("1-Doc DSDT\n"+"2-Xem DSDT\n"+"3-Them DT\n"
            +"4-Xoa DT\n"+"5-Sua thong tin DT\n"+"6-Tim kiem\n"+"7-Ghi file\n"+"8-Ket thuc");
            System.out.print("Chon chuc nang :");
            l=scanner.nextInt();
            scanner.nextLine();
            switch(l){
                case 1:
                    // dsdt.nhap();
                    dsdt.docfile();
                    break;
                case 2:
                    dsdt.xuat();
                    break;
                case 3:
                    dsdt.themDT();
                    break;
                case 4:
                    dsdt.xoaDT();
                    break;
                case 5:
                    dsdt.sua();
                    break;
                case 6:
                    System.out.println("\t1-Tim kiem theo TenSP.\n");
                    System.out.println("\t2-Tim kiem theo Crack.\n");
                    System.out.println("\t3-Tim kiem theo He Sinh Thai Doc Lap.");
                    int lp=scanner.nextInt();
                    scanner.nextLine();
                    if(lp==1){
                        dsdt.timkiemTenSP();
                    }
                    else if(lp==2){
                        dsdt.timkiemCrack();
                    }
                    else if(lp==3){
                        dsdt.timkiemHeSinhThaiDocLap();
                    }
                    else{
                        System.out.println("Nhap sai,moi nhap lai.");
                    }
                    break;
                case 7:
                    dsdt.ghifile();
                    break;
                default:
                    return;
            }
              
        }
    }
}
