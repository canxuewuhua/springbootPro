package bestPay;

import com.example.demo.SpringBootWildApplication;
import com.example.demo.config.FTPConfig;
import com.example.demo.util.FTPUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWildApplication.class)
@WebAppConfiguration
@Transactional(rollbackFor = Throwable.class)
@Slf4j
public class ConnectFTPTest {

    @Autowired
    private FTPConfig ftpConfig;
    @Before
    public void setUp() {

    }

    @Rollback
    @Test
    public void connectBestpayFTP(){
        String hostname = ftpConfig.hostName;
        int port = ftpConfig.port;
        String username = ftpConfig.userName;
        String password = ftpConfig.passWord;

        String ftpPath  = "/";
        FTPUtils ftpUtils = new FTPUtils(hostname, port, username, password, ftpPath);

        /**
         * 从ftp上下载文件
         */
        String pathname = "/";
        String filename = "12306Bypass_1.13.4.zip";
        String localpath = "D:/";
        ftpUtils.downloadFile(pathname, filename, localpath);
    }

    @After
    public void tearDown() {
    }
}
