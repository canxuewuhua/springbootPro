package com.example.demo.service;

import com.example.demo.common.ResultDTO;
import com.example.demo.domain.GeoRequestLogVO;
import com.example.demo.mapper.GeoRequestLogDAO;
import com.example.demo.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Transactional
@Slf4j
public class GeoRequestLogService {

    @Autowired
    private GeoRequestLogDAO geoRequestLogDAO;

    /**
     * 160万数据，在create_time上未添加索引的情况下，通过时间戳的范围查询所需时间为7分钟多
     * 在create_time上添加索引的情况下，通过时间戳的范围查询所需时间为0.3秒钟
     * @return
     * @throws InterruptedException
     */
    public ResultDTO insertGeoRequestLogService() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        log.debug("开始生成数据平台集奥聚合商户的数据..");
        for (int i=0;i<1600000;i++){
            Random r = new Random();
            //获取0-9之间的随机数
            GeoRequestLogVO geoRequestLogVO = new GeoRequestLogVO();
            geoRequestLogVO.setUserId("1027341854");
            geoRequestLogVO.setRequestId("201810171420025031967350"+i);

            String m1 = String.valueOf((int)((Math.random()*9+1)*1000));
            String m2 = String.valueOf((int)((Math.random()*9+1)*10000000));
            geoRequestLogVO.setInnerIfType(m1);
            geoRequestLogVO.setCode((int)((Math.random()*9+1)*1000));
            geoRequestLogVO.setMessage("{\n" +
                    "\t\"code\": \"200\",\n" +
                    "\t\"data\": {\n" +
                    "\t\t\"ISPNUM\": {\n" +
                    "\t\t\t\"province\": \"广西\",\n" +
                    "\t\t\t\"city\": \"玉林\",\n" +
                    "\t\t\t\"isp\": \"移动\"\n" +
                    "\t\t},\n" +
                    "\t\t\"RSL\": [{\n" +
                    "\t\t\t\t\"RS\": {\n" +
                    "\t\t\t\t\t\"code\": \"-9999\",\n" +
                    "\t\t\t\t\t\"desc\": \"{\\\"result_xdpt\\\":[{\\\"REGISTERTIME\\\":\\\"2018-10-21 16:17:25\\\",\\\"PLATFORMCODE\\\":\\\"GEO_0000001289\\\",\\\"P_TYPE\\\":\\\"2\\\"}],\\\"result_dksq\\\":[],\\\"result_dkfk\\\":[],\\\"result_dkbh\\\":[],\\\"TJXX\\\":{\\\"regtimes\\\":\\\"1\\\",\\\"regplatforms\\\":\\\"1\\\",\\\"reglasttime\\\":\\\"2018-10-21 16:17:25\\\",\\\"regfirsttime\\\":\\\"2018-10-21 16:17:25\\\",\\\"apptimes\\\":\\\"\\\",\\\"appplatforms\\\":\\\"\\\",\\\"appmoney\\\":\\\"\\\",\\\"applasttime\\\":\\\"\\\",\\\"appfirsttime\\\":\\\"\\\",\\\"loantimes\\\":\\\"\\\",\\\"loanplatforms\\\":\\\"\\\",\\\"loanmoney\\\":\\\"\\\",\\\"loanfirsttime\\\":\\\"\\\",\\\"loanlasttime\\\":\\\"\\\",\\\"rejtimes\\\":\\\"\\\",\\\"rejplatforms\\\":\\\"\\\",\\\"rejfirsttime\\\":\\\"\\\",\\\"rejlasttime\\\":\\\"\\\",\\\"regtimes_bank\\\":\\\"0\\\",\\\"regplatforms_bank\\\":\\\"0\\\",\\\"reglasttime_bank\\\":\\\"\\\",\\\"regfirsttime_bank\\\":\\\"\\\",\\\"apptimes_bank\\\":\\\"\\\",\\\"appplatforms_bank\\\":\\\"\\\",\\\"appmoney_bank\\\":\\\"\\\",\\\"applasttime_bank\\\":\\\"\\\",\\\"appfirsttime_bank\\\":\\\"\\\",\\\"loantimes_bank\\\":\\\"\\\",\\\"loanplatforms_bank\\\":\\\"\\\",\\\"loanmoney_bank\\\":\\\"\\\",\\\"loanfirsttime_bank\\\":\\\"\\\",\\\"loanlasttime_bank\\\":\\\"\\\",\\\"rejtimes_bank\\\":\\\"\\\",\\\"rejplatforms_bank\\\":\\\"\\\",\\\"rejfirsttime_bank\\\":\\\"\\\",\\\"rejlasttime_bank\\\":\\\"\\\",\\\"regtimes_nonbank\\\":\\\"1\\\",\\\"regplatforms_nonbank\\\":\\\"1\\\",\\\"reglasttime_nonbank\\\":\\\"2018-10-21 16:17:25\\\",\\\"regfirsttime_nonbank\\\":\\\"2018-10-21 16:17:25\\\",\\\"apptimes_nonbank\\\":\\\"\\\",\\\"appplatforms_nonbank\\\":\\\"\\\",\\\"appmoney_nonbank\\\":\\\"\\\",\\\"applasttime_nonbank\\\":\\\"\\\",\\\"appfirsttime_nonbank\\\":\\\"\\\",\\\"loantimes_nonbank\\\":\\\"\\\",\\\"loanplatforms_nonbank\\\":\\\"\\\",\\\"loanmoney_nonbank\\\":\\\"\\\",\\\"loanfirsttime_nonbank\\\":\\\"\\\",\\\"loanlasttime_nonbank\\\":\\\"\\\",\\\"rejtimes_nonbank\\\":\\\"\\\",\\\"rejplatforms_nonbank\\\":\\\"\\\",\\\"rejfirsttime_nonbank\\\":\\\"\\\",\\\"rejlasttime_nonbank\\\":\\\"\\\"},\\\"TJXX_3d\\\":{\\\"regtimes\\\":\\\"0\\\",\\\"regplatforms\\\":\\\"0\\\",\\\"reglasttime\\\":\\\"\\\",\\\"regfirsttime\\\":\\\"\\\",\\\"apptimes\\\":\\\"\\\",\\\"appplatforms\\\":\\\"\\\",\\\"appmoney\\\":\\\"\\\",\\\"applasttime\\\":\\\"\\\",\\\"appfirsttime\\\":\\\"\\\",\\\"loantimes\\\":\\\"\\\",\\\"loanplatforms\\\":\\\"\\\",\\\"loanmoney\\\":\\\"\\\",\\\"loanfirsttime\\\":\\\"\\\",\\\"loanlasttime\\\":\\\"\\\",\\\"rejtimes\\\":\\\"\\\",\\\"rejplatforms\\\":\\\"\\\",\\\"rejfirsttime\\\":\\\"\\\",\\\"rejlasttime\\\":\\\"\\\",\\\"regtimes_bank\\\":\\\"0\\\",\\\"regplatforms_bank\\\":\\\"0\\\",\\\"reglasttime_bank\\\":\\\"\\\",\\\"regfirsttime_bank\\\":\\\"\\\",\\\"apptimes_bank\\\":\\\"\\\",\\\"appplatforms_bank\\\":\\\"\\\",\\\"appmoney_bank\\\":\\\"\\\",\\\"applasttime_bank\\\":\\\"\\\",\\\"appfirsttime_bank\\\":\\\"\\\",\\\"loantimes_bank\\\":\\\"\\\",\\\"loanplatforms_bank\\\":\\\"\\\",\\\"loanmoney_bank\\\":\\\"\\\",\\\"loanfirsttime_bank\\\":\\\"\\\",\\\"loanlasttime_bank\\\":\\\"\\\",\\\"rejtimes_bank\\\":\\\"\\\",\\\"rejplatforms_bank\\\":\\\"\\\",\\\"rejfirsttime_bank\\\":\\\"\\\",\\\"rejlasttime_bank\\\":\\\"\\\",\\\"regtimes_nonbank\\\":\\\"0\\\",\\\"regplatforms_nonbank\\\":\\\"0\\\",\\\"reglasttime_nonbank\\\":\\\"\\\",\\\"regfirsttime_nonbank\\\":\\\"\\\",\\\"apptimes_nonbank\\\":\\\"\\\",\\\"appplatforms_nonbank\\\":\\\"\\\",\\\"appmoney_nonbank\\\":\\\"\\\",\\\"applasttime_nonbank\\\":\\\"\\\",\\\"appfirsttime_nonbank\\\":\\\"\\\",\\\"loantimes_nonbank\\\":\\\"\\\",\\\"loanplatforms_nonbank\\\":\\\"\\\",\\\"loanmoney_nonbank\\\":\\\"\\\",\\\"loanfirsttime_nonbank\\\":\\\"\\\",\\\"loanlasttime_nonbank\\\":\\\"\\\",\\\"rejtimes_nonbank\\\":\\\"\\\",\\\"rejplatforms_nonbank\\\":\\\"\\\",\\\"rejfirsttime_nonbank\\\":\\\"\\\",\\\"rejlasttime_nonbank\\\":\\\"\\\"},\\\"TJXX_7d\\\":{\\\"regtimes\\\":\\\"0\\\",\\\"regplatforms\\\":\\\"0\\\",\\\"reglasttime\\\":\\\"\\\",\\\"regfirsttime\\\":\\\"\\\",\\\"apptimes\\\":\\\"\\\",\\\"appplatforms\\\":\\\"\\\",\\\"appmoney\\\":\\\"\\\",\\\"applasttime\\\":\\\"\\\",\\\"appfirsttime\\\":\\\"\\\",\\\"loantimes\\\":\\\"\\\",\\\"loanplatforms\\\":\\\"\\\",\\\"loanmoney\\\":\\\"\\\",\\\"loanfirsttime\\\":\\\"\\\",\\\"loanlasttime\\\":\\\"\\\",\\\"rejtimes\\\":\\\"\\\",\\\"rejplatforms\\\":\\\"\\\",\\\"rejfirsttime\\\":\\\"\\\",\\\"rejlasttime\\\":\\\"\\\",\\\"regtimes_bank\\\":\\\"0\\\",\\\"regplatforms_bank\\\":\\\"0\\\",\\\"reglasttime_bank\\\":\\\"\\\",\\\"regfirsttime_bank\\\":\\\"\\\",\\\"apptimes_bank\\\":\\\"\\\",\\\"appplatforms_bank\\\":\\\"\\\",\\\"appmoney_bank\\\":\\\"\\\",\\\"applasttime_bank\\\":\\\"\\\",\\\"appfirsttime_bank\\\":\\\"\\\",\\\"loantimes_bank\\\":\\\"\\\",\\\"loanplatforms_bank\\\":\\\"\\\",\\\"loanmoney_bank\\\":\\\"\\\",\\\"loanfirsttime_bank\\\":\\\"\\\",\\\"loanlasttime_bank\\\":\\\"\\\",\\\"rejtimes_bank\\\":\\\"\\\",\\\"rejplatforms_bank\\\":\\\"\\\",\\\"rejfirsttime_bank\\\":\\\"\\\",\\\"rejlasttime_bank\\\":\\\"\\\",\\\"regtimes_nonbank\\\":\\\"0\\\",\\\"regplatforms_nonbank\\\":\\\"0\\\",\\\"reglasttime_nonbank\\\":\\\"\\\",\\\"regfirsttime_nonbank\\\":\\\"\\\",\\\"apptimes_nonbank\\\":\\\"\\\",\\\"appplatforms_nonbank\\\":\\\"\\\",\\\"appmoney_nonbank\\\":\\\"\\\",\\\"applasttime_nonbank\\\":\\\"\\\",\\\"appfirsttime_nonbank\\\":\\\"\\\",\\\"loantimes_nonbank\\\":\\\"\\\",\\\"loanplatforms_nonbank\\\":\\\"\\\",\\\"loanmoney_nonbank\\\":\\\"\\\",\\\"loanfirsttime_nonbank\\\":\\\"\\\",\\\"loanlasttime_nonbank\\\":\\\"\\\",\\\"rejtimes_nonbank\\\":\\\"\\\",\\\"rejplatforms_nonbank\\\":\\\"\\\",\\\"rejfirsttime_nonbank\\\":\\\"\\\",\\\"rejlasttime_nonbank\\\":\\\"\\\"},\\\"TJXX_30d\\\":{\\\"regtimes\\\":\\\"1\\\",\\\"regplatforms\\\":\\\"1\\\",\\\"reglasttime\\\":\\\"2018-10-21 16:17:25\\\",\\\"regfirsttime\\\":\\\"2018-10-21 16:17:25\\\",\\\"apptimes\\\":\\\"\\\",\\\"appplatforms\\\":\\\"\\\",\\\"appmoney\\\":\\\"\\\",\\\"applasttime\\\":\\\"\\\",\\\"appfirsttime\\\":\\\"\\\",\\\"loantimes\\\":\\\"\\\",\\\"loanplatforms\\\":\\\"\\\",\\\"loanmoney\\\":\\\"\\\",\\\"loanfirsttime\\\":\\\"\\\",\\\"loanlasttime\\\":\\\"\\\",\\\"rejtimes\\\":\\\"\\\",\\\"rejplatforms\\\":\\\"\\\",\\\"rejfirsttime\\\":\\\"\\\",\\\"rejlasttime\\\":\\\"\\\",\\\"regtimes_bank\\\":\\\"0\\\",\\\"regplatforms_bank\\\":\\\"0\\\",\\\"reglasttime_bank\\\":\\\"\\\",\\\"regfirsttime_bank\\\":\\\"\\\",\\\"apptimes_bank\\\":\\\"\\\",\\\"appplatforms_bank\\\":\\\"\\\",\\\"appmoney_bank\\\":\\\"\\\",\\\"applasttime_bank\\\":\\\"\\\",\\\"appfirsttime_bank\\\":\\\"\\\",\\\"loantimes_bank\\\":\\\"\\\",\\\"loanplatforms_bank\\\":\\\"\\\",\\\"loanmoney_bank\\\":\\\"\\\",\\\"loanfirsttime_bank\\\":\\\"\\\",\\\"loanlasttime_bank\\\":\\\"\\\",\\\"rejtimes_bank\\\":\\\"\\\",\\\"rejplatforms_bank\\\":\\\"\\\",\\\"rejfirsttime_bank\\\":\\\"\\\",\\\"rejlasttime_bank\\\":\\\"\\\",\\\"regtimes_nonbank\\\":\\\"1\\\",\\\"regplatforms_nonbank\\\":\\\"1\\\",\\\"reglasttime_nonbank\\\":\\\"2018-10-21 16:17:25\\\",\\\"regfirsttime_nonbank\\\":\\\"2018-10-21 16:17:25\\\",\\\"apptimes_nonbank\\\":\\\"\\\",\\\"appplatforms_nonbank\\\":\\\"\\\",\\\"appmoney_nonbank\\\":\\\"\\\",\\\"applasttime_nonbank\\\":\\\"\\\",\\\"appfirsttime_nonbank\\\":\\\"\\\",\\\"loantimes_nonbank\\\":\\\"\\\",\\\"loanplatforms_nonbank\\\":\\\"\\\",\\\"loanmoney_nonbank\\\":\\\"\\\",\\\"loanfirsttime_nonbank\\\":\\\"\\\",\\\"loanlasttime_nonbank\\\":\\\"\\\",\\\"rejtimes_nonbank\\\":\\\"\\\",\\\"rejplatforms_nonbank\\\":\\\"\\\",\\\"rejfirsttime_nonbank\\\":\\\"\\\",\\\"rejlasttime_nonbank\\\":\\\"\\\"},\\\"TJXX_60d\\\":{\\\"regtimes\\\":\\\"1\\\",\\\"regplatforms\\\":\\\"1\\\",\\\"reglasttime\\\":\\\"2018-10-21 16:17:25\\\",\\\"regfirsttime\\\":\\\"2018-10-21 16:17:25\\\",\\\"apptimes\\\":\\\"\\\",\\\"appplatforms\\\":\\\"\\\",\\\"appmoney\\\":\\\"\\\",\\\"applasttime\\\":\\\"\\\",\\\"appfirsttime\\\":\\\"\\\",\\\"loantimes\\\":\\\"\\\",\\\"loanplatforms\\\":\\\"\\\",\\\"loanmoney\\\":\\\"\\\",\\\"loanfirsttime\\\":\\\"\\\",\\\"loanlasttime\\\":\\\"\\\",\\\"rejtimes\\\":\\\"\\\",\\\"rejplatforms\\\":\\\"\\\",\\\"rejfirsttime\\\":\\\"\\\",\\\"rejlasttime\\\":\\\"\\\",\\\"regtimes_bank\\\":\\\"0\\\",\\\"regplatforms_bank\\\":\\\"0\\\",\\\"reglasttime_bank\\\":\\\"\\\",\\\"regfirsttime_bank\\\":\\\"\\\",\\\"apptimes_bank\\\":\\\"\\\",\\\"appplatforms_bank\\\":\\\"\\\",\\\"appmoney_bank\\\":\\\"\\\",\\\"applasttime_bank\\\":\\\"\\\",\\\"appfirsttime_bank\\\":\\\"\\\",\\\"loantimes_bank\\\":\\\"\\\",\\\"loanplatforms_bank\\\":\\\"\\\",\\\"loanmoney_bank\\\":\\\"\\\",\\\"loanfirsttime_bank\\\":\\\"\\\",\\\"loanlasttime_bank\\\":\\\"\\\",\\\"rejtimes_bank\\\":\\\"\\\",\\\"rejplatforms_bank\\\":\\\"\\\",\\\"rejfirsttime_bank\\\":\\\"\\\",\\\"rejlasttime_bank\\\":\\\"\\\",\\\"regtimes_nonbank\\\":\\\"1\\\",\\\"regplatforms_nonbank\\\":\\\"1\\\",\\\"reglasttime_nonbank\\\":\\\"2018-10-21 16:17:25\\\",\\\"regfirsttime_nonbank\\\":\\\"2018-10-21 16:17:25\\\",\\\"apptimes_nonbank\\\":\\\"\\\",\\\"appplatforms_nonbank\\\":\\\"\\\",\\\"appmoney_nonbank\\\":\\\"\\\",\\\"applasttime_nonbank\\\":\\\"\\\",\\\"appfirsttime_nonbank\\\":\\\"\\\",\\\"loantimes_nonbank\\\":\\\"\\\",\\\"loanplatforms_nonbank\\\":\\\"\\\",\\\"loanmoney_nonbank\\\":\\\"\\\",\\\"loanfirsttime_nonbank\\\":\\\"\\\",\\\"loanlasttime_nonbank\\\":\\\"\\\",\\\"rejtimes_nonbank\\\":\\\"\\\",\\\"rejplatforms_nonbank\\\":\\\"\\\",\\\"rejfirsttime_nonbank\\\":\\\"\\\",\\\"rejlasttime_nonbank\\\":\\\"\\\"},\\\"TJXX_90d\\\":{\\\"regtimes\\\":\\\"1\\\",\\\"regplatforms\\\":\\\"1\\\",\\\"reglasttime\\\":\\\"2018-10-21 16:17:25\\\",\\\"regfirsttime\\\":\\\"2018-10-21 16:17:25\\\",\\\"apptimes\\\":\\\"\\\",\\\"appplatforms\\\":\\\"\\\",\\\"appmoney\\\":\\\"\\\",\\\"applasttime\\\":\\\"\\\",\\\"appfirsttime\\\":\\\"\\\",\\\"loantimes\\\":\\\"\\\",\\\"loanplatforms\\\":\\\"\\\",\\\"loanmoney\\\":\\\"\\\",\\\"loanfirsttime\\\":\\\"\\\",\\\"loanlasttime\\\":\\\"\\\",\\\"rejtimes\\\":\\\"\\\",\\\"rejplatforms\\\":\\\"\\\",\\\"rejfirsttime\\\":\\\"\\\",\\\"rejlasttime\\\":\\\"\\\",\\\"regtimes_bank\\\":\\\"0\\\",\\\"regplatforms_bank\\\":\\\"0\\\",\\\"reglasttime_bank\\\":\\\"\\\",\\\"regfirsttime_bank\\\":\\\"\\\",\\\"apptimes_bank\\\":\\\"\\\",\\\"appplatforms_bank\\\":\\\"\\\",\\\"appmoney_bank\\\":\\\"\\\",\\\"applasttime_bank\\\":\\\"\\\",\\\"appfirsttime_bank\\\":\\\"\\\",\\\"loantimes_bank\\\":\\\"\\\",\\\"loanplatforms_bank\\\":\\\"\\\",\\\"loanmoney_bank\\\":\\\"\\\",\\\"loanfirsttime_bank\\\":\\\"\\\",\\\"loanlasttime_bank\\\":\\\"\\\",\\\"rejtimes_bank\\\":\\\"\\\",\\\"rejplatforms_bank\\\":\\\"\\\",\\\"rejfirsttime_bank\\\":\\\"\\\",\\\"rejlasttime_bank\\\":\\\"\\\",\\\"regtimes_nonbank\\\":\\\"1\\\",\\\"regplatforms_nonbank\\\":\\\"1\\\",\\\"reglasttime_nonbank\\\":\\\"2018-10-21 16:17:25\\\",\\\"regfirsttime_nonbank\\\":\\\"2018-10-21 16:17:25\\\",\\\"apptimes_nonbank\\\":\\\"\\\",\\\"appplatforms_nonbank\\\":\\\"\\\",\\\"appmoney_nonbank\\\":\\\"\\\",\\\"applasttime_nonbank\\\":\\\"\\\",\\\"appfirsttime_nonbank\\\":\\\"\\\",\\\"loantimes_nonbank\\\":\\\"\\\",\\\"loanplatforms_nonbank\\\":\\\"\\\",\\\"loanmoney_nonbank\\\":\\\"\\\",\\\"loanfirsttime_nonbank\\\":\\\"\\\",\\\"loanlasttime_nonbank\\\":\\\"\\\",\\\"rejtimes_nonbank\\\":\\\"\\\",\\\"rejplatforms_nonbank\\\":\\\"\\\",\\\"rejfirsttime_nonbank\\\":\\\"\\\",\\\"rejlasttime_nonbank\\\":\\\"\\\"},\\\"TJXX_180d\\\":{\\\"regtimes\\\":\\\"1\\\",\\\"regplatforms\\\":\\\"1\\\",\\\"reglasttime\\\":\\\"2018-10-21 16:17:25\\\",\\\"regfirsttime\\\":\\\"2018-10-21 16:17:25\\\",\\\"apptimes\\\":\\\"\\\",\\\"appplatforms\\\":\\\"\\\",\\\"appmoney\\\":\\\"\\\",\\\"applasttime\\\":\\\"\\\",\\\"appfirsttime\\\":\\\"\\\",\\\"loantimes\\\":\\\"\\\",\\\"loanplatforms\\\":\\\"\\\",\\\"loanmoney\\\":\\\"\\\",\\\"loanfirsttime\\\":\\\"\\\",\\\"loanlasttime\\\":\\\"\\\",\\\"rejtimes\\\":\\\"\\\",\\\"rejplatforms\\\":\\\"\\\",\\\"rejfirsttime\\\":\\\"\\\",\\\"rejlasttime\\\":\\\"\\\",\\\"regtimes_bank\\\":\\\"0\\\",\\\"regplatforms_bank\\\":\\\"0\\\",\\\"reglasttime_bank\\\":\\\"\\\",\\\"regfirsttime_bank\\\":\\\"\\\",\\\"apptimes_bank\\\":\\\"\\\",\\\"appplatforms_bank\\\":\\\"\\\",\\\"appmoney_bank\\\":\\\"\\\",\\\"applasttime_bank\\\":\\\"\\\",\\\"appfirsttime_bank\\\":\\\"\\\",\\\"loantimes_bank\\\":\\\"\\\",\\\"loanplatforms_bank\\\":\\\"\\\",\\\"loanmoney_bank\\\":\\\"\\\",\\\"loanfirsttime_bank\\\":\\\"\\\",\\\"loanlasttime_bank\\\":\\\"\\\",\\\"rejtimes_bank\\\":\\\"\\\",\\\"rejplatforms_bank\\\":\\\"\\\",\\\"rejfirsttime_bank\\\":\\\"\\\",\\\"rejlasttime_bank\\\":\\\"\\\",\\\"regtimes_nonbank\\\":\\\"1\\\",\\\"regplatforms_nonbank\\\":\\\"1\\\",\\\"reglasttime_nonbank\\\":\\\"2018-10-21 16:17:25\\\",\\\"regfirsttime_nonbank\\\":\\\"2018-10-21 16:17:25\\\",\\\"apptimes_nonbank\\\":\\\"\\\",\\\"appplatforms_nonbank\\\":\\\"\\\",\\\"appmoney_nonbank\\\":\\\"\\\",\\\"applasttime_nonbank\\\":\\\"\\\",\\\"appfirsttime_nonbank\\\":\\\"\\\",\\\"loantimes_nonbank\\\":\\\"\\\",\\\"loanplatforms_nonbank\\\":\\\"\\\",\\\"loanmoney_nonbank\\\":\\\"\\\",\\\"loanfirsttime_nonbank\\\":\\\"\\\",\\\"loanlasttime_nonbank\\\":\\\"\\\",\\\"rejtimes_nonbank\\\":\\\"\\\",\\\"rejplatforms_nonbank\\\":\\\"\\\",\\\"rejfirsttime_nonbank\\\":\\\"\\\",\\\"rejlasttime_nonbank\\\":\\\"\\\"}}\"\n" +
                    "\t\t\t\t},\n" +
                    "\t\t\t\t\"IFT\": \"T40301\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"ECL\": []\n" +
                    "\t},\n" +
                    "\t\"msg\": \"成功\"\n" +
                    "}");
            geoRequestLogVO.setCreateTime((long)((Math.random()*9+1)*1000000000));
            geoRequestLogVO.setAction("01");
            geoRequestLogVO.setActionCount(1);
            geoRequestLogVO.setPhoneOperator("");
            geoRequestLogDAO.insert(geoRequestLogVO);
        }

        GeoRequestLogVO geoRequestLogVO1 = new GeoRequestLogVO();
        geoRequestLogVO1.setUserId("1027341855");
        geoRequestLogVO1.setRequestId("201910171420025031967350");
        geoRequestLogVO1.setInnerIfType("T40303");
        geoRequestLogVO1.setCode(200);
        geoRequestLogVO1.setMessage("{\"code\":\"200\",\"data\":{\"ISPNUM\":{\"province\":\"辽宁\",\"city\":\"大连\",\"isp\":\"联通\"},\"RSL\":[{\"RS\":{\"code\":\"0\",\"desc\":\"正常在用\"},\"IFT\":\"A4\"}],\"ECL\":[]},\"msg\":\"成功\"}");
        geoRequestLogVO1.setCreateTime(System.currentTimeMillis()/1000);
        geoRequestLogVO1.setAction("01");
        geoRequestLogVO1.setActionCount(1);
        geoRequestLogVO1.setPhoneOperator("");
        geoRequestLogDAO.insert(geoRequestLogVO1);
        long endTime = System.currentTimeMillis();
        log.debug("批量生成数据平台集奥聚合表数据成功，耗时{}",(endTime-startTime));
        return ResultUtils.success("批量生成数据平台集奥聚合表数据总共耗时为："+ (endTime-startTime));
    }
}
