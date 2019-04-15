package com.example.demo.exercise.other;

import com.example.demo.test.reflex.Person;
import lombok.Data;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yongqiang.zhu
 */
public class OtherTest {
	public static void main(String[] args) throws Exception {

		List<Object> loanRecordList = new ArrayList<>();
		LoanRecord loanRecord = new LoanRecord();
		loanRecord.setRecordId("JK4128291988");
		loanRecord.setTransAmount(new BigDecimal("225"));
		loanRecord.setTransStatus("SUCESS");
		loanRecord.setMerchantName("Besty");
		loanRecordList.add(loanRecord);

		LoanRecord loanRecord2 = new LoanRecord();
		loanRecord2.setRecordId("JK4128291990");
		loanRecord2.setTransAmount(new BigDecimal("575"));
		loanRecord2.setTransStatus("SUCESS");
		loanRecord2.setMerchantName("apple");
		loanRecordList.add(loanRecord2);

		List<Template> templateList = new ArrayList<>();
		Template template = new Template();
		template.setRecordId("recordId");
		template.setTransAmount("transAmount");
		template.setTransStatus("transStatus");
		templateList.add(template);
		// list对象转化为list map
		List<Map<String,Object>> listMap = getListMap(loanRecordList);
		for (Map<String,Object> map: listMap){
			// 如果取到的模板不是Template类，而是模板表的字段
			// 如果是从数据库中取配好的对比项（编码、名称、比较类型），直接取值作map的key
			Class c2 = Template.class;
			Field[] fields = c2.getDeclaredFields();
			for(Field field : fields){
				if (Modifier.isPrivate(field.getModifiers())) {
					Object m = map.get(field.getName());
					// 将取的值存入对账流水表，对账流水明细
					// 对账流水单（主键、参照key、商户号、场景id、对账结果、核销状态）
					// 对账流水明细（主键、流水主表id、字段名称、字段编码、A系统的值、B系统的值，对账方式、对账结果）
					System.out.println(m);
				}
			}
		}

		//System.out.println(listMap);

	}


	public static List<Map<String,Object>> getListMap(List<Object> loanRecordList) throws Exception {
		return convertListBean2ListMap(loanRecordList);
	}

	/**
	 * 将 List<JavaBean>对象转化为List<Map>
	 * @author wyply115
	 * @return Object对象
	 * @version 2016年3月20日 11:03:01
	 */
	public static List<Map<String,Object>> convertListBean2ListMap(List<Object> beanList) throws Exception {
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		for(int i=0, n=beanList.size(); i<n; i++){
			Object bean = beanList.get(i);
			Map map = convertBean2Map(bean);
			mapList.add(map);
		}
		return mapList;
	}


	/**
	 * 将 JavaBean对象转化为 Map   此方法未测试
	 * @author wyply115
	 * @param bean 要转化的类型
	 * @return Map对象
	 * @version 2016年3月20日 11:03:01
	 */
	public static Map convertBean2Map(Object bean) throws Exception {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0, n = propertyDescriptors.length; i <n ; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}
}

@Data
class LoanRecord{

	private String recordId;

	private BigDecimal transAmount;

	private String transStatus;

	private String merchantName;

}
@Data
class  Template{
	private String recordId;

	private String transAmount;

	private String transStatus;
}
