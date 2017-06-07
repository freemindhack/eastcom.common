package com.eastcom.common.bean.easyui;

/**
 * ComboBox模型<br>
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:27:38 <br>
 *
 */
public class ComboBox implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
    private String text;
    private boolean selected;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public boolean isSelected() {
	return selected;
    }

    public void setSelected(boolean selected) {
	this.selected = selected;
    }

}
