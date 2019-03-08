import java.io.*;
import java.util.Scanner;

class FileReverse {
    public static void main(String[] args) throws IOException {
        Scanner in = null;
        PrintWriter out = null;
        String line = null;
        String[] token = null;
        int i, n, lineNumber = 0;

        // check number of command line arguments is at least 2
        if (args.length < 2) {
            System.out.println("Usage: FileCopy <input file> <output file>");
            System.exit(1);
        }

        // open files
        in = new Scanner(new File(args[0]));
        out = new PrintWriter(new FileWriter(args[1]));
        // read lines from in, extract and print tokens from each line
        while ( in .hasNextLine()) {
            lineNumber++;

            // trim leading and trailing spaces, then add one trailing space so 
            // split works on blank lines
            line = in .nextLine().trim() + " ";

            // split line around white space 
            token = line.split("\\s+");

            //METHOD TO REVERSE GOES HERE
            for (n = 0; n < token.length; n++) {
                token[n] = stringReverse(token[n], 0);
            }

            for (i = 0; i < token.length; i++) {
                System.out.println(token[i]);
                out.println(token[i]);
            }
        }
        // close files
        in .close();
        out.close();
    }

    public static String stringReverse(String s, int n) {
        String output;
        if (n == (s.length() - 1)) {
            output = s.charAt(n) + "";
        } else {
            output = stringReverse(s, n + 1) + s.charAt(n);
        }
        return output;
    }

}