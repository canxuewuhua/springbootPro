package address;

import com.example.demo.SpringBootWildApplication;
import com.example.demo.dto.UserAreaDTO;
import com.example.demo.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWildApplication.class)
public class AddressUtilsTest {

    @Autowired
    AddressService addressService;

    // 直辖市
    // 有些直辖市的第二级名称不是市辖区而是县，比如蓟县、密云县
    @Test
    public void testDirecCity() {
        String address = "上海 上海市 普陀区住房城乡建设厅666号";
        UserAreaDTO userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("上海市", userAreaDTO.getProvinceName());
        Assert.assertEquals("市辖区", userAreaDTO.getCityName());
        Assert.assertEquals("普陀区", userAreaDTO.getZoneName());

        address = "北京市顺义区马坡镇顺安南路666号";
        userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("北京市", userAreaDTO.getProvinceName());
        Assert.assertEquals("市辖区", userAreaDTO.getCityName());
        Assert.assertEquals("顺义区", userAreaDTO.getZoneName());

        address = "天津市蓟县洇溜镇八里庄村666号";
        userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("天津市", userAreaDTO.getProvinceName());
        Assert.assertEquals("县", userAreaDTO.getCityName());
        Assert.assertEquals("蓟县", userAreaDTO.getZoneName());
    }

    // 只有市和县区，缺少省
    @Test
    public void testNoProvince() {
        String address = "武汉市洪山区关山大道666号";
        UserAreaDTO userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("湖北省", userAreaDTO.getProvinceName());
        Assert.assertEquals("武汉市", userAreaDTO.getCityName());
        Assert.assertEquals("洪山区", userAreaDTO.getZoneName());

        address = "荆门市京山县杰伦镇华仔村666号";
        userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("湖北省", userAreaDTO.getProvinceName());
        Assert.assertEquals("荆门市", userAreaDTO.getCityName());
        Assert.assertEquals("京山县", userAreaDTO.getZoneName());
    }

    // 只有省和县区，缺少市
    @Test
    public void testNoCity() {
        String address = "湖北省通山县厦铺镇大坝村666号";
        UserAreaDTO userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("湖北省", userAreaDTO.getProvinceName());
        Assert.assertEquals("咸宁市", userAreaDTO.getCityName());
        Assert.assertEquals("通山县", userAreaDTO.getZoneName());

        address = "河北省武安市大同镇沙沟村666号";
        userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("河北省", userAreaDTO.getProvinceName());
        Assert.assertEquals("邯郸市", userAreaDTO.getCityName());
        Assert.assertEquals("武安市", userAreaDTO.getZoneName());
    }

    // 自治州、盟、少数民族等
    @Test
    public void testOther() {
        String address = "湖北省恩施土家族苗族自治州恩施市666号";
        UserAreaDTO userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("湖北省", userAreaDTO.getProvinceName());
        Assert.assertEquals("恩施土家族苗族自治州", userAreaDTO.getCityName());
        Assert.assertEquals("恩施市", userAreaDTO.getZoneName());

        address = "乌鲁木齐市天山区团结路666号";
        userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("新疆维吾尔自治区", userAreaDTO.getProvinceName());
        Assert.assertEquals("乌鲁木齐市", userAreaDTO.getCityName());
        Assert.assertEquals("天山区", userAreaDTO.getZoneName());

        address = "内蒙古兴安盟乌兰浩特市曙光路666号";
        userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("内蒙古自治区", userAreaDTO.getProvinceName());
        Assert.assertEquals("兴安盟", userAreaDTO.getCityName());
        Assert.assertEquals("乌兰浩特市", userAreaDTO.getZoneName());

        address = "内蒙古呼伦贝尔市鄂伦春自治旗大杨树镇666号";
        userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("内蒙古自治区", userAreaDTO.getProvinceName());
        Assert.assertEquals("呼伦贝尔市", userAreaDTO.getCityName());
        Assert.assertEquals("鄂伦春自治旗", userAreaDTO.getZoneName());
    }

    // 像有些地方的县区级，既有同名的县又有并列同名的区，如 宣化区/宣化县，后来才合并成统一的宣化区，地址库里面同时保留了宣化区和宣化县
    // 有些地方的县区级，最后面的一个 县、区、市是经过改变的，但是都是同一个地方，如 广东省增城市===广东省增城区，但是地址库只保留了一个增城区
    @Test
    public void test5() {
        String address = "河北省宣化区子龙街66号";
        UserAreaDTO userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("河北省", userAreaDTO.getProvinceName());
        Assert.assertEquals("张家口市", userAreaDTO.getCityName());
        Assert.assertEquals("宣化区", userAreaDTO.getZoneName());

        address = "河北省宣化县子龙街66号";
        userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("河北省", userAreaDTO.getProvinceName());
        Assert.assertEquals("张家口市", userAreaDTO.getCityName());
        Assert.assertEquals("宣化县", userAreaDTO.getZoneName());

        address = "广东省增城市子龙街66号";
        userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("广东省", userAreaDTO.getProvinceName());
        Assert.assertEquals("广州市", userAreaDTO.getCityName());
        Assert.assertEquals("增城区", userAreaDTO.getZoneName());
    }

    @Test
    public void testProvince() {
        String address = "上海 上海市 普陀区住房城乡建设厅666号";
        UserAreaDTO userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("上海市", userAreaDTO.getProvinceName());


        address = "湖北省通山县厦铺镇大坝村666号";
        userAreaDTO = addressService.parseAddress(address, false);
        log.info("address：{}，result：{}", address, userAreaDTO);
        Assert.assertEquals("湖北省", userAreaDTO.getProvinceName());
        Assert.assertEquals("咸宁市", userAreaDTO.getCityName());
    }

    // 文件批量解析匹配，文件格式：customerCode,address,identityAddress
    // 这个是一个批量的测试用例，平时无需跑，需要批量测试的时候单独跑即可
//    @Test
    public void testFile() {
        long start = System.currentTimeMillis();
        try {
            FileReader fr = new FileReader("/home/liaowei1/Desktop/address.txt");
            FileWriter fw = new FileWriter("/home/liaowei1/Desktop/address.csv");
            BufferedReader bf = new BufferedReader(fr);
            String str;

            // 按行读取字符串
            List<String> list = new ArrayList<>();
            while ((str = bf.readLine()) != null) {
                list.add(str);
            }
            bf.close();
            fr.close();
            StringBuffer sb = new StringBuffer();
            int n = 0;
            int m = 0;
            for (String s : list) {
                try {
                    n++;
                    sb.append(s);
                    String[] tmp = s.split(",");
                    String address = tmp[1];
                    UserAreaDTO userAreaDTO = addressService.parseAddress(address, false);
                    if (userAreaDTO == null) {
                        if (tmp.length > 2) {
                            address = tmp[2];
                            userAreaDTO = addressService.parseAddress(address, true);
                            if (userAreaDTO != null) {
                                sb.append(",2").append(",").append(userAreaDTO.getProvinceName()).append(",").append(userAreaDTO.getCityName()).append(",").append(userAreaDTO.getZoneName());
                                m++;
                            } else {
                                sb.append(",0");
                            }
                        } else {
                            sb.append(",0");
                        }
                    } else {
                        m++;
                        sb.append(",1").append(",").append(userAreaDTO.getProvinceName()).append(",").append(userAreaDTO.getCityName()).append(",").append(userAreaDTO.getZoneName());
                    }
                } catch (Exception e) {
                    sb.append(",异常地址");
                    System.out.println("异常地址：" + s);
                    e.printStackTrace();
                } finally {
                    sb.append("\r\n");
                }
            }
            fw.write(sb.toString());
            log.info("成功：{}，总数：{}，成功率：{}，耗时：{}", m, n, (double) m / n, System.currentTimeMillis() - start);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
