package wing.dev.common.control;

import java.util.Comparator;

import wing.dev.common.model.Player;

public class PlayerComparator implements Comparator<Player> {
    //比較メソッド（データクラスを比較して-1, 0, 1を返すように記述する）
    public int compare(Player a, Player b) {
        int aNo = a.getNo();
        int bNo = b.getNo();
        if (aNo > bNo) {
            return 1;
        } else if (bNo > aNo) {
            return -1;
        } else {
        	return 0;
        }
    }
}