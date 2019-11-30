package address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.SpringBootWildApplication;
import com.example.demo.dto.CityNameDTO;
import com.example.demo.dto.UserAreaDTO;
import com.example.demo.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author yongqiang.zhu
 * @date 2019/11/30 20:59
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWildApplication.class)
public class AddressListFilterTest {

	@Autowired
	private AddressService addressService;

	@Test
	public void testDirecCity() {

//		String cityNameJson = "{\"cityNameList\",\"北京市、天津市、石家庄市、唐山市、太原市、大连市、上海市、南京市、无锡市、徐州市、常州市、苏州市、南通市、淮安市、盐城市、扬州市、镇江市、泰州市、杭州市、宁波市、温州市、嘉兴市、湖州市、绍兴市、金华市、台州市、合肥市、福州市、厦门市、南昌市、济南市、青岛市、淄博市、东营市、烟台市、潍坊市、济宁市、威海市、临沂市、滨州市、郑州市、武汉市、宜昌市、长沙市、岳阳市、广州市、深圳市、珠海市、佛山市、江门市、肇庆市、 惠州市、东莞市、中山市、南宁市、柳州市、海口市、重庆市、成都市、泸州市、绵阳市、宜宾市、贵阳市、昆明市、西安市、兰州市\"}";
		String cityNameJson =  "{\"cityNameList\":\"北京市、天津市、石家庄市、唐山市、太原市、大连市、上海市、南京市、无锡市、徐州市、常州市、苏州市、南通市、淮安市、盐城市、扬州市、镇江市、泰州市、杭州市、宁波市、温州市、嘉兴市、湖州市、绍兴市、金华市、台州市、合肥市、福州市、厦门市、南昌市、济南市、青岛市、淄博市、东营市、烟台市、潍坊市、济宁市、威海市、临沂市、滨州市、郑州市、武汉市、宜昌市、长沙市、岳阳市、广州市、深圳市、珠海市、佛山市、江门市、肇庆市、惠州市、东莞市、中山市、南宁市、柳州市、海口市、重庆市、成都市、泸州市、绵阳市、宜宾市、贵阳市、昆明市、西安市、兰州市\"}";

		CityNameDTO cityNameDTO = JSON.parseObject(cityNameJson, CityNameDTO.class);
		String cityNameList = cityNameDTO.getCityNameList();

		List<String> adrList = Arrays.asList(cityNameList.split("、"));
		String address = "广东省增城市子龙街66号";
		UserAreaDTO userAreaDTO = addressService.parseAddress(address, false);
		if (adrList.contains(userAreaDTO.getProvinceName()) || adrList.contains(userAreaDTO.getCityName())){
			log.info("工作居住地符合民生易贷的66个准入城市之一");
		}else{
			log.error("工作居住地不符合民生易贷的66个准入城市之一");
		}
	}
}
