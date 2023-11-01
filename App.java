package OOP;


import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        DSDT dsdt=new DSDT();
        //=====Nhap,xuat======
        // dsdt.nhap();
        // dsdt.xuat();
        // //=====Them========
        // dsdt.themDT();
        // //=====Xoa========
        // dsdt.xoaDT();
        // //=====Tim kiem======
        // dsdt.timkiemTenSP();
        // dsdt.timkiemCrack();
        // dsdt.timkiemHeSinhThaiDocLap();
        // //=====Sua=======
        // dsdt.sua();
        dsdt=new DSDT();
        int l=0;
        Scanner scanner=new Scanner(System.in);
        while(l!=7){
            System.out.println("1-Nhap DSDT\n"+"2-Xem DSDT\n"+"3-Them DT\n"
            +"4-Xoa DT\n"+"5-Sua thong tin DT\n"+"6-Tim kiem\n"+"7-Ket thuc");
            System.out.print("Chon chuc nang :");
            l=scanner.nextInt();
            scanner.nextLine();
            switch(l){
                case 1:
                    dsdt.nhap();
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
                    dsdt.timkiemTenSP();
                    dsdt.timkiemCrack();
                    dsdt.timkiemHeSinhThaiDocLap();
                    break;
            }
              
        }

    }
}
