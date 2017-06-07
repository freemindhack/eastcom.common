package com.eastcom.common.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *  JSON模型<br>
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:29:29 <br>
 *
 */
@XmlRootElement
public class Json implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success = false;

    private String msg = "";

    private Object obj = null;

    public boolean isSuccess() {
	return success;
    }

    public void setSuccess(boolean success) {
	this.success = success;
    }

    public String getMsg() {
	return msg;
    }

    public void setMsg(String msg) {
	this.msg = msg;
    }

    public Object getObj() {
	return obj;
    }

    public void setObj(Object obj) {
	this.obj = obj;
    }

}
