package OOP;

import java.util.Arrays;
import java.util.Scanner;

public class DSDT {
    private DienThoai []dsdt;
    private int n;

    DSDT(){

    }
    DSDT(DienThoai dsdt[],int tongDT){
        this.n=tongDT;
        this.dsdt=dsdt;
    }
    DSDT(DSDT s){
        dsdt=s.dsdt;
        n=s.n;
    }
    //===========Nhap-xuat==============
    public void nhap(){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Nhap tong so loai dien thoai:");
        n=scanner.nextInt();
        scanner.nextLine();
        dsdt=new DienThoai[n];
        for(int i=0;i<n;i++){
            System.out.println("1-Androi\n"+"2-IOS");
            int l=scanner.nextInt();
            scanner.nextLine();
            switch(l){
                case 1:
                    dsdt[i]=new Androi();
                    dsdt[i].nhap();
                    break;
                case 2:
                    dsdt[i]=new IOS();
                    dsdt[i].nhap();
                    break;
            }
        }
    }
    public void xuat(){
        System.out.println();
        for(int i=0;i<n;i++){
            dsdt[i].xuat();
        }
        System.out.println();
    }
    //=========Them==============
    public void themDT(){
        Scanner scanner=new Scanner(System.in);
        dsdt=Arrays.copyOf(dsdt,dsdt.length+1);
        System.out.println("1-Androi\n"+"2-IOS");
        int h=scanner.nextInt();
        scanner.nextLine();
        switch(h){
            case 1:
                dsdt[n]=new Androi();
                dsdt[n].nhap();
                n++;
                break;
            case 2:
                dsdt[n]=new IOS();
                dsdt[n].nhap();
                n++;
                break;
        }
    }
    //======Tim kiem============
    public int timkiem(String MaSP){
        for(int i=0;i<n;i++){
            if(dsdt[i].getMaSP().equals(MaSP)){
                return i;
            }
        }
        return -1;
    }
    public void timkiemTenSP(){
        System.out.println("Nhap ten San pham muon tim kiem:");
        Scanner scanner=new Scanner(System.in);
        String TenSP=scanner.nextLine();
        for(int i=0;i<n;i++){
            if(dsdt[i].getTenSP().indexOf(TenSP)!=-1){
                dsdt[i].xuat();
            }
        }
    }
    public void timkiemCrack(){
        System.out.println("Nhap ten phan mem crack muon tim kiem:");
        Scanner scanner=new Scanner(System.in);
        String crack=scanner.nextLine();
        for(int i=0;i<n;i++){
             if (dsdt[i] instanceof Androi){
                Androi m=new Androi();
                m=(Androi)dsdt[i];
                if(m.getCrack().indexOf(crack)!=-1){
                    System.out.println("MaSP:");
                    System.out.println(m.getMaSP());
                }
            }
        }  
    }

    public void timkiemHeSinhThaiDocLap(){
        System.out.println("Nhap ten phan mem He sinh thai doc lap muon tim kiem:");
        Scanner scanner=new Scanner(System.in);
        String HeSinhThaiDocLap=scanner.nextLine();
        for(int i=0;i<n;i++){
            if(dsdt[i] instanceof IOS){
                IOS m=new IOS();
                m=(IOS)dsdt[i];
                if(m.getHeSinhThaiDL().indexOf(HeSinhThaiDocLap)!=-1){
                    System.out.println("Ten San pham la:");
                    System.out.println(m.getTenSP());
                }
            }     
        }
    }
    public void timkiemTimIphone(){
        System.out.println("Tim Iphone bang:");
        Scanner scanner=new Scanner(System.in);
        String TimIphone=scanner.nextLine();
        for(int i=0;i<n;i++){
            if(dsdt[i] instanceof IOS){
                IOS m=new IOS();
                m=(IOS)dsdt[i];
                if(m.getTimIphone().indexOf(TimIphone)!=-1){
                    System.out.println("Ten San pham la:");
                    m.getTenSP();
                }
            }     
        }
    }

    //=========Xoa=========
    //Test thu
    public void xoaDT(){
        System.out.println("Nhap MaSP muon xoa:");
        Scanner scanner=new Scanner(System.in);
        String MaSP=scanner.nextLine();
        int t=timkiem(MaSP);
        if(t==-1){
            System.out.println("Khong ton tai Dien Thoai can xoa.");
        }
        else{
            for(int i = t; i < n - 1; i++){
                dsdt[i]=new DienThoai(dsdt[i+1]);
            }
            dsdt=Arrays.copyOf(dsdt,dsdt.length-1);
            n--;
        }
    }

    //==========Sua===========
    public void sua(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Nhap MaSP :");
        String mssp=scanner.nextLine();
        int p=timkiem(mssp);
        int k;
        do{
        System.out.println("Chon sua: \n"+"1-MaSP\n"+"2-TenSP\n"+"3-SoLuong\n"
        +"4-Don gia\n"+"5-Don vi tinh\n"+"6-He sinh thai doc lap\n"+"7-Tim Iphone\n"+"8-Crack\n"+"9-Ket thuc");
        k=scanner.nextInt();
        scanner.nextLine();
        switch(k){
            case 1:
                System.out.println("Nhap thong tin can sua:");
                String c1=scanner.nextLine();
                dsdt[p].setMaSP(c1);
                break;
            case 2:
                System.out.println("Nhap thong tin can sua:");
                String c2=scanner.nextLine();
                dsdt[p].setTenSP(c2);
                break;
            case 3:
                System.out.println("Nhap thong tin can sua:");
                int c3=scanner.nextInt();
                scanner.nextLine();
                dsdt[p].setSoLuong(c3);
                break;
            case 4:
                System.out.println("Nhap thong tin can sua:");
                float c4=scanner.nextFloat();
                scanner.nextLine();
                dsdt[p].setDonGia(c4);;
                break;
            case 5:
                System.out.println("Nhap thong tin can sua:");
                String c5=scanner.nextLine();
                dsdt[p].setDonViTinh(c5);
                break;
            case 6:
                if(dsdt[p] instanceof IOS){
                    System.out.println("Nhap thong tin can sua:");
                    String c6=scanner.nextLine();
                    IOS m=new IOS();
                    m=(IOS)dsdt[p];
                    m.setHeSinhThaiDL(c6);
                }
                else{
                    System.out.println("Dien thoai thuoc dong \"Androi\" nen khong co chuc nang nay.");
                }
                break;
            case 7:
                if(dsdt[p] instanceof IOS){
                    System.out.println("Nhap thong tin can sua:");
                    String c7=scanner.nextLine();
                    IOS t=new IOS();
                    t=(IOS)dsdt[p];
                    t.setTimIphone(c7);
                }
                else{
                    System.out.println("Dien thoai thuoc dong \"Androi\" nen khong co chuc nang nay.");
                }
                break;
            case 8:
                if(dsdt[p] instanceof Androi){
                    System.out.println("Nhap thong tin can sua:");
                    String c8=scanner.nextLine();
                    Androi b=new Androi();
                    b=(Androi)dsdt[p];
                    b.setCrack(c8);
                }
                else{
                    System.out.println("Dien thoai thuoc dong \"IOS\" nen khong co chuc nang nay.");
                }
                break;
    
        }
    }while(k!=9);
}

}
