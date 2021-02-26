import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Product {
    
    // colection of main variabels
    private String nameProduct;
    private String codeProduct;
    private String stockProduct;
    private String priceProduct;
    private String keyword;

    // main file
    private static FileReader fileReader;
    private static FileWriter fileWriter;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;

    // operation
    public static Scanner input = new Scanner(System.in);
    
    // constructor
    public Product(String keyword){
        this.keyword = keyword;
    }

    public Product(){}

    public Product(String nameProduct,String codeProduct,String stockProduct,String priceProduct){
        this.nameProduct = nameProduct;
        this.codeProduct = codeProduct;
        this.stockProduct = stockProduct;
        this.priceProduct = priceProduct;
    }

    // getter
    public String getNameProduct(){
        return nameProduct;
    }
    public String getCodeProduct(){
        return codeProduct;
    }
    public String getStockProduct(){
        return stockProduct;
    }
    public String getPriceProduct(){
        return priceProduct;
    }
    public String getkeyword(){
        return keyword;
    }

    //----------------------------------------------------------------------------

    public static void seeData()throws IOException{
        // colection of variabels
        fileReader = new FileReader("databaseProduct.txt");
        bufferedReader = new BufferedReader(fileReader);

        String nomor,nama,kode,stok,harga;
        nomor="Nomor";nama="Nama Produk";kode="Kode Produk";stok="Stok Produk";harga="Harga Produk";

        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("|\t%-7s |\t%-35s |\t%-15s |\t%-10s|\t%-10s|",nomor,nama,kode,stok,harga);
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // colection of variabels
        int no = 0;
        String data = bufferedReader.readLine();

        // display data
        while(data != null){
            
            //colection of variabels
            StringTokenizer st = new StringTokenizer(data,"|");
            no++;

            String next1 = st.nextToken();String next2 = st.nextToken();String next3 = st.nextToken();String next4 = st.nextToken();
            System.out.printf("|\t%-7d ",no);
            System.out.printf("|\t%-35s |",next1);
            System.out.printf("\t%-15s |",next2);
            System.out.printf("\t%-10s |",next3);
            System.out.printf("\t%-10s  |",next4);
            System.out.print("\n");

            System.out.println("---------------------------------------------------------------------------------------------------------");
            data = bufferedReader.readLine();
        }
    }

    public static void addData(Product product)throws IOException{

        // colection of variabels
        fileWriter = new FileWriter("databaseProduct.txt",true);
        bufferedWriter = new BufferedWriter(fileWriter);
        

        String[] keyword = {product.getNameProduct()+"|"+product.getCodeProduct()+"|"+product.getStockProduct()+"|"+product.getPriceProduct()};
        boolean exist = checkData(keyword, false);


        if(!exist){
            System.out.println("\nData yang anda masukan adalah: ");
            System.out.println("==============================");
            System.out.println("Nama Produk: "+product.getNameProduct());
            System.out.println("Kode Produk: "+product.getCodeProduct());
            System.out.println("Stok Produk: "+product.getStockProduct());
            System.out.println("Harga Produk: "+product.getPriceProduct());

            System.out.print("\nApakah anda ingin menambahkan data tersebut y/n: ");String selectionInput = input.next();

            if(selectionInput.equalsIgnoreCase("y")){
                bufferedWriter.write(product.getNameProduct()+"|"+product.getCodeProduct()+"|"+product.getStockProduct()+"|"+product.getPriceProduct());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
    }
    public static void searchData(Product produk)throws IOException{

        // colection of variabels
        fileReader = new FileReader("databaseProduct.txt");
        bufferedReader = new BufferedReader(fileReader);

        String[] keyword = produk.getkeyword().split("\\s+");
        checkData(keyword, true);

    }

    public static boolean checkData(String[] keyword, boolean display)throws IOException{
        
        // colection of variabels
        fileReader = new FileReader("databaseProduct.txt");
        bufferedReader = new BufferedReader(fileReader);
        
        String data = bufferedReader.readLine();
        boolean exist = false;

        //display
        if (display){
            System.out.println("Data yang anda masukan sudah tersedia di database...");
            System.out.println("Dengan data berikut: \n");

            String nama,kode,stok,harga;
            nama = "Nama Produk";kode = "Kode Produk";stok = "Stok Produk";harga = "Harga Produk";
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.printf("|\t%-35s |\t%-15s |\t%-10s|\t%-10s|",nama,kode,stok,harga);
            System.out.println("\n---------------------------------------------------------------------------------------------");
        }
        
        //check data is exist
        while(data!=null){
            exist = true;

            for(String keywords:keyword){
                exist = exist && data.toLowerCase().contains(keywords.toLowerCase());
            }

            if (exist){
                
                StringTokenizer st = new StringTokenizer(data,"|");
                System.out.printf("|\t%-35s |",st.nextToken());
                System.out.printf("\t%-15s |",st.nextToken());
                System.out.printf("\t%-10s |",st.nextToken());
                System.out.printf("\t%-10s  |",st.nextToken());
            }
            data = bufferedReader.readLine();
        }
        if(display){
                  System.out.println("\n---------------------------------------------------------------------------------------------");
        }
        return exist;
    }


}
