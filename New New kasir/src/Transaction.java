import java.io.*;
import java.util.StringTokenizer;

public class Transaction {

    // colection of main variabels
    public static String afterCast;
    public static int castingPrices;
    public static int castingNumberOfProduct;
    public static int calculate;
    public static int added = 0;

    // main file
    private static FileReader fileReader;
    private static BufferedReader bufferedReader;

    public static void displayData(String[][] dataTransaction)throws IOException{

        // colection of variabels
        String name = "Nama Produk";String lots = "Jumlah Produk";String price = "Harga Produk";String totalPrice = "Total Harga";
        boolean exist = true;

        // main file
        fileReader = new FileReader("databaseProduct.txt");
        bufferedReader = new BufferedReader(fileReader);

        // cast file
        String data = bufferedReader.readLine();



        // display data
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.printf("|\t%-35s |\t%-20s |\t%20s |\t%-20s",name,lots,price,totalPrice);



                // cast
                while (data!=null){
                    for (int i = 0; i < dataTransaction.length; i++) {
                        for (int j = 0; j < dataTransaction[0].length; j++) {
                            String[] key  = {dataTransaction[i][0]};

                            exist = true;
                            StringTokenizer st = new StringTokenizer(data,"|");

                            for (String keywords:key){
                                exist = exist && data.toLowerCase().contains(keywords.toLowerCase());
                            }

                            if (exist){
                                st.nextToken();st.nextToken();st.nextToken();
                                String stringPrice = st.nextToken();
                                afterCast = stringPrice;
                                castingPrices = Integer.parseInt(afterCast);
                                castingNumberOfProduct = Integer.parseInt(dataTransaction[i][1]);
                                calculate = castingNumberOfProduct*castingPrices;
                                added += calculate;
                                System.out.printf("\n|\t%-35s |\t%-20s |\t%-20s |\t%-20d |",dataTransaction[i][j],dataTransaction[i][j+=1],afterCast,calculate);
                            }
                            data = bufferedReader.readLine();
                        }
            }
        }
        System.out.println("\n------------------------------------------------------------------------------------------------------------------");
        System.out.println("TOTAL = "+added+"\n");
    }

    public static void purchase(String[][] dataTransaction,int customerMoney)throws IOException{
        int changeMoney = customerMoney - added;
        receipt(dataTransaction,customerMoney,changeMoney);
    }

    public static void receipt(String[][] dataTransaction,int customerMoney,int changeMoney)throws IOException{

        // main file
        fileReader = new FileReader("databaseProduct.txt");
        bufferedReader = new BufferedReader(fileReader);

        // colection of variabels
        String data = bufferedReader.readLine();
        boolean exist = true;
        String name = "Nama Produk";String lots = "Jumlah Produk";String price = "Harga Produk";String totalPrice = "Total Harga";

        System.out.println("                                  STRUK  PEMBAYARAN                                                     ");
        System.out.println("                                   TOKO HAJI OTONG                                                      \n");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf("|\t%-35s |\t%-20s |\t%20s |\t%-20s",name,lots,price,totalPrice);

        while (data!=null){
            for (int i = 0; i < dataTransaction.length; i++) {
                for (int j = 0; j < dataTransaction[0].length; j++) {
                    String[] key  = {dataTransaction[i][0]};

                    exist = true;
                    StringTokenizer st = new StringTokenizer(data,"|");

                    for (String keywords:key){
                        exist = exist && data.toLowerCase().contains(keywords.toLowerCase());
                    }

                    if (exist){
                        st.nextToken();st.nextToken();st.nextToken();
                        String stringPrice = st.nextToken();
                        afterCast = stringPrice;
                        castingPrices = Integer.parseInt(afterCast);
                        castingNumberOfProduct = Integer.parseInt(dataTransaction[i][1]);
                        calculate = castingNumberOfProduct*castingPrices;
                        System.out.printf("\n|\t%-35s |\t%-20s |\t%20s |\t%-20d |",dataTransaction[i][j],dataTransaction[i][j+=1],afterCast,calculate);
                    }
                    data = bufferedReader.readLine();
                }
            }
        }
        System.out.println("\n------------------------------------------------------------------------------------------------------------------");
        System.out.println("TOTAL = "+added);
        System.out.println("TUNAI = "+customerMoney);
        System.out.println("KEMBALIAN = "+changeMoney);


    }


    public static boolean checkData(String[] keyword) throws IOException {

        // colection of variabels
        fileReader = new FileReader("databaseProduct.txt");
        bufferedReader = new BufferedReader(fileReader);

        String data = bufferedReader.readLine();
        boolean exist = true;
        boolean loop = true;

        //check data is exist
        while (data != null) {
            exist = true;

            for (String keywords : keyword) {
                exist = exist && data.toLowerCase().contains(keywords.toLowerCase());
            }

            if (exist) {
                loop = true;
                data = null;
            } else {
                data = bufferedReader.readLine();
                loop = false;
            }
        }
        return loop;
    }

}
