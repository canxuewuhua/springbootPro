package com.example.demo.util;

import com.example.demo.dto.AddressDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class AddressUtils {

    /**
     * 将地址转换为省、市、县(区)、街道
     *
     * @param address
     * @return
     */
    public static AddressDTO parseAddress(String address) {
        String regex = "(?<province>[^省]+省|.+自治区|内蒙古|新疆|宁夏|广西|西藏|上海市|上海|北京市|北京|天津市|天津|重庆市|重庆)(?<city>[^市]+市|.+自治州|.+地区|.+盟|)(?<county>[^县]+县|.+?区|.+市|.+旗)";
        String regexWithoutProvince = "(?<city>[^市]+市|.+自治州|.+地区|.+盟|)(?<county>[^县]+县|.+?区|.+市|.+旗)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null;
        String city = null;
        String county = null;

        AddressDTO addressDTO = null;
        if (!m.find()) {
            m.usePattern(Pattern.compile(regexWithoutProvince));
        }
        if (m.find(0)) {
            try {
                province = m.group("province");
            } catch (Exception e) {
                province = null;
            }
            city = m.group("city");
            county = m.group("county");
            addressDTO = new AddressDTO(province, city, county);
        }

        return addressDTO;

    }
}
