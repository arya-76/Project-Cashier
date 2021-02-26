import java.io.IOException;
import java.util.Scanner;

public class Main {
    // colection of variabels
    private static String username;
    private static String password;
    private static String nameProduct;
    private static String numberOfProduct;
    private static int amountProduct;
    private static String[][] dataTransaction;


    // colection of class
    private static Product product;
    private static Cashier cashier;

    // operation
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args)throws IOException{
        System.out.println("======== Toko Haji Otong ========\n");

        // colection of variabels
        boolean loopLogin = true;
        boolean insideLoop = true;

        while (loopLogin){
            System.out.print("Masukan Username: ");username = input.next();
            System.out.print("Masukan Password: ");password = input.next();

            cashier = new Cashier(username,password);

            if (cashier.getUsername().equalsIgnoreCase(cashier.getUsernameDefault())&&cashier.getPassword().equalsIgnoreCase(cashier.getPasswordDefault())){
                System.out.println("\nBerhasil login!!\n");

                while (insideLoop){
                    System.out.println("Main Menu...");
                    System.out.println("1. Lihat data");
                    System.out.println("2. Tambah data");
                    System.out.println("3. Cari data");
                    System.out.println("4. Transaksi\n");

                    System.out.print("Masukan pilihan anda: ");int pilihanUser = input.nextInt();
                    System.out.println();

                    switch (pilihanUser){
                        case 1:
                            System.out.println("~ Lihat Data Produk!!\n");

                            product = new Product();
                            Product.seeData();
                            insideLoop = returnToMenu();
                            break;
                        case 2:
                            // colection of variabels
                            boolean outerAddLoop = true;

                            System.out.println("~ Tambah Data!!\n");

                            while(outerAddLoop){

                                System.out.print("Masukan nama produk: ");String nameProduct = input.next();
                                System.out.print("Masukan kode produk: ");String codeProduct = input.next();
                                System.out.print("Masukan stok produk: ");String stockProduct = input.next();
                                System.out.print("Masukan harga produk: ");String priceProduct = input.next();

                                product = new Product(nameProduct,codeProduct,stockProduct,priceProduct);
                                Product.addData(product);

                                System.out.print("Apakah anda ingin menambah data lagi y/n? ");String addAgain = input.next();
                                System.out.println();
                                if(addAgain.equalsIgnoreCase("y")){
                                    outerAddLoop = true;
                                }else{
                                    outerAddLoop = false;
                                }
                            }
                            insideLoop = returnToMenu();
                            break;
                        case 3:
                            System.out.println("~ Cari Data!!\n");

                            System.out.print("Masukan Kata Kunci: ");String keyword = input.next();

                            product = new Product(keyword);
                            Product.searchData(product);
                            insideLoop = returnToMenu();
                            break;
                        case 4:
                            System.out.println("~ Transaksi!!\n");

                            // colection of variabels
                            boolean failedInput = true;
                            int num = 0;

                            // main program

                            while (failedInput){
                                System.out.print("Berapa banyak produk yang akan dibeli? ");amountProduct = input.nextInt();
                                dataTransaction = new String[amountProduct][2];

                                for (int i = 0; i < amountProduct; i++) {
                                    num++;
                                    System.out.println("~ Program ke-"+num);

                                    System.out.print("Masukan nama produk: ");nameProduct = input.next();
                                    dataTransaction[i][0] = nameProduct;
                                    System.out.print("Masukan jumlah produk "+nameProduct+": ");numberOfProduct = input.next();
                                    dataTransaction[i][1] = numberOfProduct;

                                    String[] key = {nameProduct};
                                    failedInput = Transaction.checkData(key);

                                    if (failedInput){
                                        failedInput = false;
                                    }else{
                                        System.out.println("Data yang anda masukan salah...");
                                        System.out.println("Harap masukan data dengan benar...");
                                        failedInput = true;
                                        i = amountProduct;
                                    }
                                }
                            }

                            System.out.println("PEMBAYARAN...");
                            Transaction.displayData(dataTransaction);
                            System.out.print("Masukan uang tunai: ");int customerMoney = input.nextInt();
                            Transaction.purchase(dataTransaction,customerMoney);
                            insideLoop = returnToMenu();
                            break;

                    }
                }
                loopLogin = false;
            }else{
                System.out.println("Username atau password yang anda masukan salah");
                System.out.println("Silahkan masukan kembali username dan password...");
            }
        }
    }
    public static boolean returnToMenu(){
        boolean insideLoop;
        String back;
        System.out.print("Apakah anda ingin kembali ke main menu y/n? ");back = input.next();
        if (back.equalsIgnoreCase("y")){
            insideLoop = true;
        }else {
            insideLoop = false;
        }
        return insideLoop;
    }


}
