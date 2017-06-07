package com.eastcom.common.bean.ztree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * tree模型<br>
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:28:47 <br>
 *
 */
public class Tree implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * 用于区分不同模块
	 */
	private String moduleName;
	/**
	 * 可在项目自由拓展属性类
	 */
	private Map<String, Object> attributes = Maps.newHashMap();

	/**
	 * 节点名称。<br>
	 * 1、如果不使用 name 属性保存节点名称，请修改 setting.data.key.name <br>
	 * 默认值：无
	 */
	private String name;
	/**
	 * 节点的 checkBox / radio 的 勾选状态。[setting.check.enable = true &
	 * treeNode.nocheck = false 时有效]<br>
	 * 1、如果不使用 checked 属性设置勾选状态，请修改 setting.data.key.checked<br>
	 * 2、建立 treeNode 数据时设置 treeNode.checked = true 可以让节点的输入框默认为勾选状态<br>
	 * 3、修改节点勾选状态，可以使用 treeObj.checkNode / checkAllNodes /updateNode方法，
	 * 具体使用哪种请根据自己的需求而定<br>
	 * 4、为了解决部分朋友生成 json 数据出现的兼容问题, 支持 "false","true" 字符串格式的数据<br>
	 * 默认值：false
	 */
	private Boolean checked;

	/**
	 * 记录 treeNode 节点是否为父节点。<br>
	 * 1、初始化节点数据时，根据 treeNode.children 属性判断，有子节点则设置为 true，否则为 false<br>
	 * 2、初始化节点数据时，如果设定 treeNode.isParent = true，即使无子节点数据，也会设置为父节点<br>
	 * 3、为了解决部分朋友生成 json 数据出现的兼容问题, 支持 "false","true" 字符串格式的数据<br>
	 */
	private Boolean isParent;
	
	/**
	 * 父节点ID
	 */
	private String pId;
	/**
	 * 节点的子节点数据集合。<br>
	 * 1、如果不使用 children 属性保存子节点数据，请修改 setting.data.key.children<br>
	 * 2、异步加载时，对于设置了 isParent = true 的节点，在展开时将进行异步加载<br>
	 * 默认值：无
	 */
	private List<? extends Tree> children;
	/**
	 * 1、设置节点的 checkbox / radio 是否禁用 [setting.check.enable = true 时有效]<br>
	 * 2、为了解决部分朋友生成 json 数据出现的兼容问题, 支持 "false","true" 字符串格式的数据<br>
	 * 3、请勿对已加载的节点修改此属性，禁止 或 取消禁止 请使用 setChkDisabled() 方法<br>
	 * 4、初始化时，如果需要子孙节点继承父节点的 chkDisabled 属性，请设置 setting.check.chkDisabledInherit
	 * 属性<br>
	 * 默认值：false
	 */
	private Boolean chkDisabled;
	/**
	 * 强制节点的 checkBox / radio 的 半勾选状态。[setting.check.enable = true &
	 * treeNode.nocheck = false 时有效]<br>
	 * 1、强制为半勾选状态后，不再进行自动计算半勾选状态<br>
	 * 2、设置 treeNode.halfCheck = false 或 null 才能恢复自动计算半勾选状态<br>
	 * 3、为了解决部分朋友生成 json 数据出现的兼容问题, 支持 "false","true" 字符串格式的数据<br>
	 * 默认值：false
	 */
	private Boolean halfCheck;
	/**
	 * 节点自定义图标的 URL 路径。<br>
	 * 1、父节点如果只设置 icon ，会导致展开、折叠时都使用同一个图标<br>
	 * 2、父节点展开、折叠使用不同的个性化图标需要同时设置 treeNode.iconOpen / treeNode.iconClose 两个属性<br>
	 * 3、如果想利用 className 设置个性化图标，需要设置 treeNode.iconSkin 属性<br>
	 * 默认值：无
	 */
	private String icon;
	/**
	 * 父节点自定义折叠时图标的 URL 路径。<br>
	 * 1、此属性只针对父节点有效<br>
	 * 2、此属性必须与 iconOpen 同时使用<br>
	 * 3、如果想利用 className 设置个性化图标，需要设置 treeNode.iconSkin 属性<br>
	 * 默认值：无
	 */
	private String iconClose;
	/**
	 * 父节点自定义展开时图标的 URL 路径。<br>
	 * 1、此属性只针对父节点有效<br>
	 * 2、此属性必须与 iconClose 同时使用<br>
	 * 3、如果想利用 className 设置个性化图标，需要设置 treeNode.iconSkin 属性<br>
	 * 默认值：无
	 */
	private String iconOpen;
	/**
	 * 节点自定义图标的 className<br>
	 * 1、需要修改 css，增加相应 className 的设置<br>
	 * 2、css 方式简单、方便，并且同时支持父节点展开、折叠状态切换图片<br>
	 * 3、css 建议采用图片分割渲染的方式以减少反复加载图片，并且避免图片闪动<br>
	 * 4、zTree v3.x 的 iconSkin 同样支持 IE6<br>
	 * 5、如果想直接使用 图片的Url路径 设置节点的个性化图标，需要设置 treeNode.icon / treeNode.iconOpen /
	 * treeNode.iconClose 属性<br>
	 * 
	 * 默认值：无
	 */
	private String iconSkin;
	/**
	 * 判断 treeNode 节点是否被隐藏。<br>
	 * 1、初始化 zTree 时，如果节点设置 isHidden = true，会被自动隐藏<br>
	 * 2、请勿对已加载的节点修改此属性，隐藏 / 显示 请使用 hideNode() / hideNodes() / showNode() /
	 * showNodes() 方法<br>
	 */
	private Boolean isHidden;

	/**
	 * 1、设置节点是否隐藏 checkbox / radio [setting.check.enable = true 时有效] <br>
	 * 2、为了解决部分朋友生成 json 数据出现的兼容问题, 支持 "false","true" 字符串格式的数据 <br>
	 * 默认值：false <br>
	 * true 表示此节点不显示 checkbox / radio，不影响勾选的关联关系，不影响父节点的半选状态。 <br>
	 * false 表示节点具有正常的勾选功能
	 */
	private Boolean nocheck;
	/**
	 * 记录 treeNode 节点的 展开 / 折叠 状态。 <br>
	 * 1、初始化节点数据时，如果设定 treeNode.open = true，则会直接展开此节点 <br>
	 * 2、叶子节点 treeNode.open = false <br>
	 * 3、为了解决部分朋友生成 json 数据出现的兼容问题, 支持 "false","true" 字符串格式的数据 <br>
	 * 4、非异步加载模式下，无子节点的父节点设置 open=true 后，可显示为展开状态，但异步加载模式下不会生效。（v3.5.15+） <br>
	 * 默认值：false
	 */
	private Boolean open;
	/**
	 * 设置点击节点后在何处打开 url。[treeNode.url 存在时有效] <br>
	 * 默认值：无 同超链接 target 属性: "_blank", "_self" 或 其他指定窗口名称 <br>
	 * 
	 * 省略此属性，则默认为 "_blank"<br>
	 */
	private String target;
	/**
	 * 节点链接的目标 URL<br>
	 * 1、编辑模式 (setting.edit.enable = true) 下此属性功能失效，如果必须使用类似功能，请利用 onClick
	 * 事件回调函数自行控制。<br>
	 * 2、如果需要在 onClick 事件回调函数中进行跳转控制，那么请将 URL 地址保存在其他自定义的属性内，请勿使用 url<br>
	 * 
	 * 默认值：无
	 */
	private String url;

	/**
	 * 记录节点的层级 初始化节点数据时，由 zTree 增加此属性，请勿提前赋值
	 */
	private String level;

	/**
	 * 用于批量查找节点的属性 因为getNodesByParam(key, value,
	 * parentNode)中的key值不能为attributes中的自定义属性 所以拓展该属性
	 */
	private String groupUI;

	public Tree() {
	}

	public void addAttribute(String key, String value) {
		attributes = attributes == null ? new HashMap<String, Object>() : attributes;
		attributes.put(key, value);
	}

	public void removeAttribute(String key) {
		if (attributes != null)
			attributes.remove(key);
	}

	public Tree(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Tree(String id, String name, Boolean noCheck, Boolean isParent) {
		this.id = id;
		this.name = name;
		this.nocheck = noCheck;
		this.isParent = isParent;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	
	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	//	public List<Tree> getChildren() {
//		return children;
//	}
	public List<? extends Tree> getChildren() {
		return children;
	}

	public void setChildren(List<? extends Tree> children) {
		this.children = children;
	}

	public Boolean getChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(Boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public Boolean getHalfCheck() {
		return halfCheck;
	}

	public void setHalfCheck(Boolean halfCheck) {
		this.halfCheck = halfCheck;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconClose() {
		return iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public Boolean getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Boolean isHidden) {
		this.isHidden = isHidden;
	}

	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getGroupUI() {
		return groupUI;
	}

	public void setGroupUI(String groupUI) {
		this.groupUI = groupUI;
	}

	public <T extends Tree> void  addChildren(T tree) {
		children = children == null ? new ArrayList<T>() : children;
		@SuppressWarnings("unchecked")
		List<T> l = (List<T>)children;
		l.add(tree);
	}
}
