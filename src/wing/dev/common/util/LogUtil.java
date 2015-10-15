package wing.dev.common.util;

public class LogUtil {
	public static void printLog(String tag, String log) {
		String nameTag = "[" + tag +"]";
		System.out.println(nameTag + log);
	}
	public static void printLog() {
		System.out.println("");
	}
}
