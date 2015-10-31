package cn.eainfo.common;

/**
 * 生成ajax实体对象
 */
public class AjaxBeanData {
	private String label;// 模块名称
	private Object data;// 数据对象
	private String result;// 处理结果 成功：sucess 失败：faild
	private String resume;// 错误发生时的提醒信息
	
	/*构造函数赋值addde by huxiangyu 
	 * */
	public AjaxBeanData(){
		
	}
	public AjaxBeanData(String result,String resume,String label){
		this.label= label;
		this.result = result;
		this.resume = resume;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
}
