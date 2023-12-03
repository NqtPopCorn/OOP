package sanpham;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

import javax.sound.midi.Patch;

public class DSDT {
    public DienThoai []dsdt;
    public int n=100;

    public DSDT(){
        
    }
    public DSDT(DienThoai dsdt[],int tongDT){
        this.n=tongDT;
        this.dsdt=dsdt;
    }
    public DSDT(DSDT s){
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
            System.out.println();
        }
        
    }
    //============Kiem tra MaDT=============
    public boolean checkMaDT(String MaDT){
        for(int i=0;i<n;i++){
            if(dsdt[i].getMaSP().indexOf(MaDT)!=-1){
                return true;
            }
        }
        return false;
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
                ghinoiAnd((Androi)dsdt[n]);
                n++;
                
                break;
            case 2:
                dsdt[n]=new IOS();
                dsdt[n].nhap();
                ghinoiIOS((IOS)dsdt[n]);
                n++;
                break;
        }
    }
    //Tim hieu them
    // public void themDT(DienThoai dt){
    //     Scanner scanner=new Scanner(System.in);
    //     dsdt=Arrays.copyOf(dsdt,dsdt.length+1);
    //     System.out.println("1-Androi\n"+"2-IOS");
    //     int h=scanner.nextInt();
    //     scanner.nextLine();
    //     switch(h){
    //         case 1:
    //             dsdt[n]=new Androi();
    //             dsdt[n].nhap(dt);
    //             n++;
    //             ghinoiAnd(dsdt[n]);
    //             break;
    //         case 2:
    //             dsdt[n]=new IOS();
    //             dsdt[n].nhap(dt);
    //             n++;
    //             ghinoiIOS((IOS)dsdt[n]);
    //             break;
    //     }
    // }
    //=========Doc file text=========
    /**
     */
    public void docfile(){
        BufferedReader reader;
        dsdt=new DienThoai[n];
		try {
			reader = new BufferedReader(new FileReader("data\\dsdt.txt"));
			String line = reader.readLine();
            int i=0;
			while (line != null&& !line.trim().isEmpty()) {
				String[] result=line.split("#");
                System.out.println(result.length);
                if(result[0].indexOf("Androi")!=-1){
                    dsdt[i]=new Androi();
                    dsdt[i].setMaSP(result[1]);
                    dsdt[i].setTenSP(result[2]);
                    dsdt[i].setSoLuong(Integer.parseInt(result[3]));
                    dsdt[i].setDonGia(Float.parseFloat(result[4]));
                    dsdt[i].setDonViTinh(result[5]);
                    ((Androi) dsdt[i]).setCrack(result[6]);
                }
                else{
                    dsdt[i]=new IOS();
                    dsdt[i].setMaSP(result[1]);
                    dsdt[i].setTenSP(result[2]);
                    dsdt[i].setSoLuong(Integer.parseInt(result[3]));
                    dsdt[i].setDonGia(Float.parseFloat(result[4]));
                    dsdt[i].setDonViTinh(result[5]);
                    ((IOS) dsdt[i]).setTimIphone(result[6]);
                    ((IOS) dsdt[i]).setHeSinhThaiDL(result[7]);
                }
				line = reader.readLine();
                i++;
			}
            System.out.println("Da doc xong.");
            System.out.println();
            n=i;
            // System.out.println(n);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    //======Ghi File============
    public void ghifile(){
        try {
            PrintWriter pw=new PrintWriter("data\\dsdt.txt");
            for(int i=0;i<n;i++){
                if(dsdt[i] instanceof Androi){
                    pw.write("Androi#");
                    pw.write(dsdt[i].getMaSP()+"#");
                    pw.write(dsdt[i].getTenSP()+"#");
                    pw.write(dsdt[i].getSoLuong()+"#");
                    pw.write(dsdt[i].getDonGia()+"#");
                    pw.write(dsdt[i].getDonViTinh()+"#");
                    pw.write(((Androi) dsdt[i]).getCrack()+"\n");
                }
                else if(dsdt[i] instanceof IOS){
                    pw.write("IOS#");
                    pw.write(dsdt[i].getMaSP()+"#");
                    pw.write(dsdt[i].getTenSP()+"#");
                    pw.write(dsdt[i].getSoLuong()+"#");
                    pw.write(dsdt[i].getDonGia()+"#");
                    pw.write(dsdt[i].getDonViTinh()+"#");
                    pw.write(((IOS) dsdt[i]).getTimIphone()+"#");
                    pw.write(((IOS) dsdt[i]).getHeSinhThaiDL()+"\n");
                }
            }
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
            System.out.println("Da ghi xong.");
            System.out.println();
    }
//==========Ghi noi file============
    public static void ghinoiAnd(Androi ad){
        if(ad!=null){
            try {
                FileWriter  fw=new FileWriter ("data\\dsdt.txt",true);
                fw.append("Androi#"+ad.getMaSP()+"#"+ad.getTenSP()+"#"+ad.getSoLuong()+"#"+ad.getDonGia()+"#"+ad.getDonViTinh()+"#"+ad.getCrack()+"\n");
                fw.close();
                System.out.println("Da cap nhat.\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Invalid Androi object");
        }
    }

    public static void ghinoiIOS(IOS ios){
        if(ios!=null){
            try {
                FileWriter  pw=new FileWriter ("data\\dsdt.txt",true);
                pw.append("IOS#"+ios.getMaSP()+"#"+ios.getTenSP()+"#"+ios.getSoLuong()+"#"+ios.getDonGia()+"#"+ios.getDonViTinh()+"#"+ios.getTimIphone()+"#"+ios.getHeSinhThaiDL()+"\n");
                pw.close();
                System.out.println("Da cap nhat.\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        else{
            System.out.println("Invalid IOS object");
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
     public DienThoai timkiemDT(String MaSP){
        for(int i=0;i<n;i++){
            if(dsdt[i].getMaSP().indexOf(MaSP)!=-1){
                return dsdt[i];
            }
        }
        return null;
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
                if(dsdt[i+1] instanceof Androi){
                    dsdt[i]=new Androi((Androi) dsdt[i+1]);
                }
                else{
                    dsdt[i]=new IOS((IOS) dsdt[i+1]);
                }
            }
            dsdt=Arrays.copyOf(dsdt,dsdt.length-1);
            n--;
            ghifile();
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
                String c1;
                do{
                    c1=scanner.nextLine();
                    dsdt[p].setMaSP(c1);
                }while (checkMaDT(c1)==true); 
                ghifile();
                break;
            case 2:
                System.out.println("Nhap thong tin can sua:");
                String c2=scanner.nextLine();
                dsdt[p].setTenSP(c2);
                ghifile();
                break;
            case 3:
                System.out.println("Nhap thong tin can sua:");
                int c3=scanner.nextInt();
                scanner.nextLine();
                dsdt[p].setSoLuong(c3);
                ghifile();
                break;
            case 4:
                System.out.println("Nhap thong tin can sua:");
                float c4=scanner.nextFloat();
                scanner.nextLine();
                dsdt[p].setDonGia(c4);
                ghifile();
                break;
            case 5:
                System.out.println("Nhap thong tin can sua:");
                String c5=scanner.nextLine();
                dsdt[p].setDonViTinh(c5);
                ghifile();
                break;
            case 6:
                if(dsdt[p] instanceof IOS){
                    System.out.println("Nhap thong tin can sua:");
                    String c6=scanner.nextLine();
                    IOS m=new IOS();
                    m=(IOS)dsdt[p];
                    m.setHeSinhThaiDL(c6);
                    ghifile();
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
                    ghifile();
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
                    ghifile();
                }
                else{
                    System.out.println("Dien thoai thuoc dong \"IOS\" nen khong co chuc nang nay.");
                }
                break;
    
        }
    }while(k!=9);
}

}
