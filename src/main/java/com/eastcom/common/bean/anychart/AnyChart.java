package com.eastcom.common.bean.anychart;

/**
 * AnyChart模型<br>
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:27:16 <br>
 *
 */
public class AnyChart implements java.io.Serializable {
	
	private static final long serialVersionUID = -6331106893078220845L;
	
	private String name;
    private String y;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getY() {
	return y;
    }

    public void setY(String y) {
	this.y = y;
    }

}
