package wing.dev.trumpGame;

import wing.dev.common.Field;

public abstract class BaseTrumpGame extends Field {
	/**
	 * コンストラクタ
	 * @param isTestMode テストモードにするか
	 */
	public BaseTrumpGame(String gameName) {
		m_gameName = gameName;
	}
}
