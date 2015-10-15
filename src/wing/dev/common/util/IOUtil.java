package wing.dev.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOUtil {
	public static int inputNumber() {
		int inputNumber = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    while (true) {
			String inputText = null;
			try {
				System.out.print("数字を入力してください: ");
		    	inputText = br.readLine();
				inputNumber = Integer.parseInt(inputText);
				break;
			} catch (IOException | NumberFormatException e) {
				System.out.println("数字以外の文字が入力されました: " + inputText);
			}
        }
		return inputNumber;
	}
}
