import java.io.*;
import java.util.*;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class Main {
    public static void main(String[] args) {
        List<Book> booksList = new ArrayList<>();

        Map<Integer, String> author = new HashMap<>();
        File authorFile = new File("csv/author.csv");
        File booksFile = new File("csv/book.csv");
        FileReader fileReader = null;

        //Не хочет автоматом создавать папку result, пришлось создавать отдельно.
        File dirResult = new File("result");
        dirResult.mkdir();

        Scanner scanner = new Scanner(System.in);
        try{scanner = new Scanner(authorFile);
        }
        catch(FileNotFoundException ex){
            System.out.println("File not found.");
        }

        while(scanner.hasNextLine()){
            String string = scanner.nextLine();
            String[] arrayString = string.split(",");
            author.put(valueOf(arrayString[0]), arrayString[1]);
        }
        System.out.println("Создан обьект Map , название author  со всеми авторами наших книг.");

        String s = "";
        try(BufferedReader bReader = new BufferedReader(new FileReader(booksFile))){

            while((s=bReader.readLine())!=null) {
                String[] sArray = s.split(",");
                booksList.add(new Book(parseInt(sArray[0]),
                        sArray[1],
                        parseFloat(sArray[2]),
                        parseInt(sArray[3]),
                        sArray[4],
                        parseInt(sArray[5])));
            }
        }
        catch(IOException ex){System.out.println(ex.getMessage());}

        System.out.println("Файл с книгами сформирован!");

        String s2 = "";
        try(BufferedReader bReader = new BufferedReader(new FileReader(booksFile))){
            while((s2=bReader.readLine())!=null) {
                String[] sArray = s2.split(",");
                String title = sArray[1];
                String authorName = author.get(parseInt(sArray[5]));
                String imageName = "images"+ File.separator +sArray[4];
                InputStream inputStream = null;
                OutputStream outputStream = null;
                byte[] buffer = null;
                try{
                    inputStream = new FileInputStream(imageName);
                    buffer = new byte[inputStream.available()];
                    inputStream.read(buffer);


                    outputStream = new FileOutputStream("result"+File.separator+authorName+" - "+title+".jpg");
                    outputStream.write(buffer);

                }
                catch(FileNotFoundException ex){
                    System.out.println(ex.getMessage());
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        inputStream.close();
                    }
                    catch(IOException ex){System.out.println(ex.getMessage());}
                }
            }
        }
        catch(IOException ex){System.out.println(ex.getMessage());}


        float bigPrice = 0;
        float smallPrice = 0;
        int index = 0;
        int indexSmallPrice = 0;
        for(int i=0; i<booksList.size(); i++){
            if(booksList.get(i).getPriceBook() > bigPrice){
                bigPrice = booksList.get(i).getPriceBook();
                index= i;
            }
            if(smallPrice == 0){
                smallPrice = booksList.get(i).getPriceBook();
                indexSmallPrice = i;
            }
            if(booksList.get(i).getPriceBook() < smallPrice){
                smallPrice = booksList.get(i).getPriceBook();
                indexSmallPrice = i;
            }
        }


        try(FileWriter fWriter = new FileWriter(new File("result/result.txt"))){
            fWriter.write("\nСамая дорогая книга: "+booksList.get(index).getTitleBook() + " , цена "+booksList.get(index).getPriceBook());
            fWriter.write("\nСамая дешевая книга: "+booksList.get(indexSmallPrice).getTitleBook() + " , цена "+booksList.get(indexSmallPrice).getPriceBook());
        }
        catch(IOException ex){System.out.println(ex.getMessage());}


        System.out.println();
        System.out.println("Самая дорогая книга: "+booksList.get(index).getTitleBook() + " , цена "+booksList.get(index).getPriceBook());
        System.out.println("Самая дешевая книга: "+booksList.get(indexSmallPrice).getTitleBook() + " , цена "+booksList.get(indexSmallPrice).getPriceBook());
    }
}