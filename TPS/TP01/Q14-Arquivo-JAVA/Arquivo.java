import java.io.RandomAccessFile;
import java.util.Scanner;

public class Arquivo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            RandomAccessFile arq = new RandomAccessFile("num.dat", "rw");
            int n = sc.nextInt();

            for (int i = 0; i < n; i++) {
                double num = sc.nextDouble();
                arq.writeDouble(num);
            }
            arq.close();

            arq = new RandomAccessFile("num.dat", "r");
            long lerInverso = arq.length() - 8;

            for (int i = n - 1; i >= 0; i--) {
                arq.seek(lerInverso);
                double num = arq.readDouble(); 
                if (num % 1 == 0) {
			System.out.println((int) num);
		}
		else {
			System.out.println(num);
		}
                lerInverso -= 8;
            }

            arq.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

