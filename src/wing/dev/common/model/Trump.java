package wing.dev.common.model;

import wing.dev.common.IAttribute.TrumpNo;
import wing.dev.common.IAttribute.TrumpType;

public class Trump {
	private TrumpType type = null;
	private TrumpNo no = null;

	public Trump(TrumpType type, TrumpNo no){
		this.type = type;
		this.no = no;
	}

	@Override
	public String toString() {
		return this.type.toString() + this.no.toString();
	}

	/**
	 * typeを取得します。
	 * @return type
	 */
	public TrumpType getType() {
	    return type;
	}
	/**
	 * typeを設定します。
	 * @param type type
	 */
	public void setType(TrumpType type) {
	    this.type = type;
	}
	/**
	 * noを取得します。
	 * @return no
	 */
	public TrumpNo getNo() {
	    return no;
	}
	/**
	 * noを設定します。
	 * @param no no
	 */
	public void setNo(TrumpNo no) {
	    this.no = no;
	}


}
