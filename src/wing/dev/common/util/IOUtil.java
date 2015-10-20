package wing.dev.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 入出力に関するUtilクラス
 */
public class IOUtil {

	/**
	 * 入力した数字を取得するUtil
	 * @return 入力した数字
	 */
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

	/**
	 * 上限値以下の入力した数字を取得するUtil
	 * @param maxNumber 上限値
	 * @return 入力した数字
	 */
	public static int inputNumberUnderMax(int maxNumber) {
		int inputNumber = -1;
		while(true) {
			inputNumber = inputNumber();
			if (inputNumber > 0 && inputNumber <= maxNumber) {
				break;
			} else {
				System.out.println("入力範囲は" + 1 + "〜" + maxNumber + "です");
			}
		}
		return inputNumber;
	}
}
