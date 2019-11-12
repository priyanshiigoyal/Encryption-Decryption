package encryptdecrypt;
import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        String st = "";
        String mode = "enc";
        int key = 0;
        String result = "";
        String input;
        String alg = "shift" ;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("-key")) {
                key = Integer.parseInt(args[i + 1]);
            } else if (args[i].equalsIgnoreCase("-mode")) {
                mode = args[i + 1];
            } else if (args[i].equalsIgnoreCase("-out")) {
                result = args[i + 1];
            }
            else if(args[i].equalsIgnoreCase("-alg")){
                alg = args[i+1];
            }
            else{
                if (args[i].equalsIgnoreCase("-data")) {
                    st = st + args[i + 1];
                }
                else if (args[i].equalsIgnoreCase("-in")) {
                    try{
                        input = args[i+1];
                        File file = new File(input);
                        Scanner sc = new Scanner(file);
                        while(sc.hasNext()){
                            st = st + sc.nextLine();
                        }
                        sc.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println(st);
        Define d = new Define(mode, st, key,alg);
        String out = d.EncDec();
        if (result.length() == 0) {
            System.out.println(out);
        } else {
            try {
                File myFile = new File(result);
                FileWriter myOut = new FileWriter(myFile);
                myOut.write(out);
                myOut.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
