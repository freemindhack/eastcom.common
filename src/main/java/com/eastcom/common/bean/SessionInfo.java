package com.eastcom.common.bean;

import java.util.List;
import java.util.Map;

/**
 * session信息模型<br>
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:29:38 <br>
 *
 */
public class SessionInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;// 用户ID
	private String name;// 用户登录名
	private String ip;// 用户IP

	private List<String> resourceList;// 用户可以访问的资源地址列表
	private List<String> permissionList;//拥有的资源权限列表
	private List<String> regionsList;// 用户可以访问的地市权限列表
	private List<String> bussinessList;// 用户可以访问的业务权限列表

	/**
	 * 字符串前面会拼装角色名称以{role_name}:{regionName}拼接例如： admins:广州,gzadmin:深圳；
	 */
	private List<String> regionsList2;
	/**
	 * map中存放id、name、cityOrder、shortForm1（已失效）、shortForm2（已失效）、OWNER_ROLE；
	 */
	private List<Map<String, Object>> regionListMap;

	/**
	 * 字符串前面会拼装角色名称以{role_name}:{bussinessName}拼接例如：admins:ADB,gzadmin:ORC
	 */
	private List<String> bussinessList2;
	/**
	 * map中存放id、name、OWNER_ROLE、path（已失效，为空）、adminName（已失效，为空）、adminDept（已失效，为空）
	 * 、adminPhone（已失效，为空）、parentId（已失效，为空）、parentName（已失效，为空）；
	 */
	private List<Map<String, Object>> bussinessListMap;

	public List<String> getRegionsList() {
		return regionsList;
	}

	public void setRegionsList(List<String> regionsList) {
		this.regionsList = regionsList;
	}

	public List<String> getBussinessList() {
		return bussinessList;
	}

	public void setBussinessList(List<String> bussinessList) {
		this.bussinessList = bussinessList;
	}

	public List<String> getRegionsList2() {
		return regionsList2;
	}

	public void setRegionsList2(List<String> regionsList2) {
		this.regionsList2 = regionsList2;
	}

	public List<Map<String, Object>> getRegionListMap() {
		return regionListMap;
	}

	public void setRegionListMap(List<Map<String, Object>> regionListMap) {
		this.regionListMap = regionListMap;
	}

	public List<String> getBussinessList2() {
		return bussinessList2;
	}

	public void setBussinessList2(List<String> bussinessList2) {
		this.bussinessList2 = bussinessList2;
	}

	public List<Map<String, Object>> getBussinessListMap() {
		return bussinessListMap;
	}

	public void setBussinessListMap(List<Map<String, Object>> bussinessListMap) {
		this.bussinessListMap = bussinessListMap;
	}

	public List<String> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}
	
	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
