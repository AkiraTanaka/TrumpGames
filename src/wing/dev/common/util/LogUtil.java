package wing.dev.common.util;

/**
 * ログ出力Utilクラス
 */
public class LogUtil {
	public static void printLog(String tag, String log) {
		String nameTag = "[" + tag +"]";
		System.out.println(nameTag + log);
	}
	public static void printLog(Class<?> clz, String log) {
		printLog(clz.getSimpleName(), log);
	}
	public static void printLog() {
		System.out.println("");
	}
}
