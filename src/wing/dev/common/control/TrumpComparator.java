package wing.dev.common.control;

import java.util.Comparator;

import wing.dev.common.model.Trump;

public class TrumpComparator implements Comparator<Trump> {
    //比較メソッド（データクラスを比較して-1, 0, 1を返すように記述する）
    public int compare(Trump a, Trump b) {
        int aType = a.getType().getNo();
        int bType = b.getType().getNo();
        //こうすると社員番号の昇順でソートされる
        if (aType > bType) {
            return 1;
        } else if (bType > aType) {
            return -1;
        } else {
            int aNo = a.getNo().getNo();
            int bNo = b.getNo().getNo();
            if (aNo > bNo) {
                return 1;
            } else if (bNo > aNo) {
                return -1;
            } else {
            	return 0;
            }
        }
    }

}