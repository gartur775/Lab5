import java.io.*;
import java.util.Scanner;

public class Ex5_Var3 {
    public static void main(String[] args) throws IOException {
        DataOutputStream dout = null;
        DataInputStream din = null;
        DataOutputStream dout2 = null;
        DataInputStream din2 = null;
        File res;
        try {
            File file = new File("src.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            dout = new DataOutputStream(new FileOutputStream(file));
            Scanner sc = new Scanner(System.in);
            System.out.println("count:");
            int count = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < count; i++){
                String line = sc.nextLine();
                dout.writeUTF(line);
            }
            res = new File("result.txt");
            res.createNewFile();
            dout2 = new DataOutputStream(new FileOutputStream(res));
            din = new DataInputStream(new FileInputStream(file));
            String n = "";
            for(int i =0; i<count;i++){
                n = din.readUTF();
                int cS = n.length();
                dout2.writeInt(cS);
                dout2.writeUTF(n);
            }
            din2 = new DataInputStream(new FileInputStream(res));
            for(int i = 0; i < count; i++){
                int cS = din2.readInt();
                String str = din2.readUTF();
                System.out.print("Количество сиволов в строке: " +cS+ "." + " Cтрока: " +str);
                System.out.println("");
            }
        }
        catch (EOFException e) {

        }
        catch (IOException ioe) {
            System.out.println("Ошибка создания файла. " + ioe);
        }

        finally {
            dout.flush();
            dout.close();
            din.close();
            dout2.flush();
            dout2.close();
            din2.close();
        }
    }
}
