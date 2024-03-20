package java_assessment4;
import java.util.Scanner;
public class ManagementCrop {
    public static void main(String[] args) {
        int len;
        double updatequantity;
                String new_crop,crop1[]={};
        Scanner in=new Scanner(System.in);
        System.out.println("Input The Length of Array");
        len=in.nextInt();
        String[] NameOfCrops=new String[len+1];
        System.out.println("Enter The"+" " +len+ "Crops Names");
                for(int i=0;i<NameOfCrops.length-1;i++){
                      NameOfCrops[i]=in.next();
                     
        }
                System.out.println("Input The Length of Array of Crops");
        int leng=in.nextInt();
       int[] quantity=new int[leng+1];
          System.out.println("Enter The"+" " +len+ "Quantities");
                for(int i=0;i<quantity.length-1;i++){
                      quantity[i]=in.nextInt();
                     
        }       
//Double[] Quantity={35.0,20.0};
//for(int i=0;i<NameOfCrops.length;i++){
//            System.out.println(Quantity[i]);
//        }
//      
        
    }
         }
        

