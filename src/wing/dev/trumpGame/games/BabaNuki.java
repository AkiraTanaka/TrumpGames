package wing.dev.trumpGame.games;

import java.util.List;

/**
 * ババぬきゲームクラス
 */
public class BabaNuki extends JijiNuki {

	/**
	 * コンストラクタ
	 */
	public BabaNuki() {
		super("ババぬき");
	}

	/**
	 * プレイヤーと山札を設定
	 * @param playerNames プレイヤーたち
	 * @param CPUNames CPUたち
	 */
	@Override
	protected void settingYamahuda(List<String> playerNames, List<String> CPUNames) {
		initField(playerNames, CPUNames, 1);
	}
}
