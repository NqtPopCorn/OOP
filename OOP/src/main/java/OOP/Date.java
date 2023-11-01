package OOP;


import java.security.InvalidParameterException;
import java.util.Scanner;

public class Date {
    private int date;
    private int month;
    private int year;

    public Date() {
        date = 1;
        year = 1;
        month = 1;
    }
    public Date(int d, int m, int y) {
        if(isValid(d, m, y)) {
            date = d;
            month = m;
            year = y;
        }
        else throw new InvalidParameterException("tham so sai");
    }

    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        int d, m, y;
        do {
            System.out.println("Nhap ngay, thang, nam: ");
            d = scanner.nextInt();
            m = scanner.nextInt();
            y = scanner.nextInt();
            if(!isValid(d, m, y)) System.out.println("Nhap sai");
        }
        while (!isValid(d, m, y));
        date = d;
        month = m;
        year = y;
    }
    public void xuat() {
        System.out.format("%02d/%02d/%04d\n", date, month, year);
    }

    public void set(int d, int m, int y) {
        if(isValid(d, m, y)) {
            date = d;
            month = m;
            year = y;
        }
        else throw new InvalidParameterException("Tham so cua ham set sai\n");
    }
    public String toString() {
        return String.format("%02d/%02d/%04d", date, month, year);
    }
    public void setDate(int d) { 
        if(isValid(d, month, year)) {
            date = d;
        }
        else throw new InvalidParameterException("Tham so cua ham setDate sai\n");
    }
    public void setMonth(int m) { 
        if(isValid(date, m, year)) {
            month = m;
        }
        else throw new InvalidParameterException("Tham so cua ham setMonth sai\n");
    }
    public void setYear(int y) { 
        if(isValid(date, year, y)) {
            year = y;
        }
        else throw new InvalidParameterException("Tham so cua ham setYear sai\n");
    }

    public boolean isValid(int d, int m, int y) {
        if(d >= 1 && d <= maxDay(m, y) && m >= 1 && m <= 12 && y >= 1) return true;
        return false;
    }
    public int maxDay(int month, int year) {
        switch(month) {
            case 1, 3, 5, 7, 8, 10, 12: return 31;
            case 4, 6, 9, 11: return 30;
            case 2: {
                if(isLeapYear(year)) return 29;
                return 28;
            }
            default: return 0;
        }
    }
    public boolean isLeapYear(int year) {
        if(year%4 == 0 && year%100 != 0 || year%400 == 0) return true;
        return false;
    }
    public boolean isLeapYear() {
        if(year%4 == 0 && year%100 != 0 || year%400 == 0) return true;
        return false;
    }
}
