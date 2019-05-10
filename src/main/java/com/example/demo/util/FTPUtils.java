package com.example.demo.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
/**
 * @author yongqiang.zhu
 * @date 2019/5/11 0:31
 */
public class FTPUtils {
	/** 日志对象 **/
	private static final Logger LOGGER = LoggerFactory.getLogger(FTPUtils.class);

	/** FTP地址 **/
	private  String FTP_ADDRESS = "";

	/** FTP端口 **/
	private  int FTP_PORT = 0;

	/** FTP用户名 **/
	private  String FTP_USERNAME = "";

	/** FTP密码 **/
	private  String FTP_PASSWORD = "";

	/** FTP基础目录 **/
	private  String BASE_PATH = "";
	/** 初始化登录ftp 默认false 登录成功返回true **/
	private  Boolean b=false;

	public Boolean getB() {
		return b;
	}

	/**
	 *  2018-6-13 12:39:55
	 *   新添，初始化登录ftp，连接失败 返回b 为：false ,成功 为 ：true
	 * @param FTP_USERNAME
	 * @param FTP_PASSWORD
	 * @param BASE_PATH
	 */
	public FTPUtils(String FTP_ADDRESS, int FTP_PORT, String FTP_USERNAME, String FTP_PASSWORD, String BASE_PATH) {
		this.FTP_ADDRESS = FTP_ADDRESS;
		this.FTP_PORT = FTP_PORT;
		this.FTP_USERNAME = FTP_USERNAME;
		this.FTP_PASSWORD = FTP_PASSWORD;
		this.BASE_PATH = BASE_PATH;
		b = login(FTP_ADDRESS, FTP_PORT, this.FTP_USERNAME, this.FTP_PASSWORD);
	}

	/** 本地字符编码  **/
	private static String localCharset = "GBK";

	/** FTP协议里面，规定文件名编码为iso-8859-1 **/
	private static String serverCharset = "ISO-8859-1";

	/** UTF-8字符编码 **/
	private static final String CHARSET_UTF8 = "UTF-8";

	/** OPTS UTF8字符串常量 **/
	private static final String OPTS_UTF8 = "OPTS UTF8";

	/** 设置缓冲区大小4M **/
	private static final int BUFFER_SIZE = 1024 * 1024 * 4;

	/** FTPClient对象 **/
	private static FTPClient ftpClient = null;

	/**
	 * 本地文件上传到FTP服务器
	 *
	 * @param ftpPath FTP服务器文件相对路径，例如：test/123
	 * @param savePath 本地文件路径，例如：D:/test/123/test.txt
	 * @param fileName 上传到FTP服务的文件名，例如：666.txt
	 * @return boolean 成功返回true，否则返回false
	 */
	public boolean uploadLocalFile(String ftpPath, String savePath, String fileName) {
		// 登录
		login(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
		boolean flag = false;
		if (ftpClient != null) {
			File file = new File(savePath);
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				ftpClient.setBufferSize(BUFFER_SIZE);
				// 设置编码：开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）
				if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
					localCharset = CHARSET_UTF8;
				}
				ftpClient.setControlEncoding(localCharset);
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 目录不存在，则递归创建
				if (!ftpClient.changeWorkingDirectory(path)) {
					this.createDirectorys(path);
				}
				// 设置被动模式，开通一个端口来传输数据
				ftpClient.enterLocalPassiveMode();
				// 上传文件
				flag = ftpClient.storeFile(new String(fileName.getBytes(localCharset), serverCharset), fis);
			} catch (Exception e) {
				LOGGER.error("本地文件上传FTP失败", e);
			} finally {
				IOUtils.closeQuietly(fis);
				closeConnect();
			}
		}
		return flag;
	}

	/**
	 * 远程文件上传到FTP服务器
	 *
	 * @param ftpPath FTP服务器文件相对路径，例如：test/123
	 * @param remotePath 远程文件路径，例如：http://www.baidu.com/xxx/xxx.jpg
	 * @param fileName 上传到FTP服务的文件名，例如：test.jpg
	 * @return boolean 成功返回true，否则返回false
	 */
	public boolean uploadRemoteFile(String ftpPath, String remotePath, String fileName) {
		// 登录
		login(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
		boolean flag = false;
		if (ftpClient != null) {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = null;
			try {
				// 远程获取文件输入流
				HttpGet httpget = new HttpGet(remotePath);
				response = httpClient.execute(httpget);
				HttpEntity entity = response.getEntity();
				InputStream input = entity.getContent();
				ftpClient.setBufferSize(BUFFER_SIZE);
				// 设置编码：开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）
				if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
					localCharset = CHARSET_UTF8;
				}
				ftpClient.setControlEncoding(localCharset);
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 目录不存在，则递归创建
				if (!ftpClient.changeWorkingDirectory(path)) {
					this.createDirectorys(path);
				}
				// 设置被动模式，开通一个端口来传输数据
				ftpClient.enterLocalPassiveMode();
				// 上传文件
				flag = ftpClient.storeFile(new String(fileName.getBytes(localCharset), serverCharset), input);
			} catch (Exception e) {
				LOGGER.error("远程文件上传FTP失败", e);
			} finally {
				closeConnect();
				try {
					httpClient.close();
				} catch (IOException e) {
					LOGGER.error("关闭流失败", e);
				}
				if (response != null) {
					try {
						response.close();
					} catch (IOException e) {
						LOGGER.error("关闭流失败", e);
					}
				}
			}
		}
		return flag;
	}
	/**
	 * 下载指定文件到本地
	 *
	 * @param ftpPath FTP服务器文件相对路径，例如：test/123
	 * @param fileName 要下载的文件名，例如：test.txt
	 * @param savePath 保存文件到本地的路径，例如：D:/test
	 * @return 成功返回true，否则返回false
	 */
	public boolean downloadFile(String ftpPath, String fileName, String savePath) {
		// 登录
		boolean flag = false;
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 判断是否存在该目录
				if (!ftpClient.changeWorkingDirectory(path)) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录不存在");
					return flag;
				}
				ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
				String[] fs = ftpClient.listNames();
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录下没有文件");
					return flag;
				}
				for (String ff : fs) {
					String ftpName = new String(ff.getBytes(serverCharset), localCharset);
					if (ftpName.equals(fileName)) {
						File file = new File(savePath + '/' + ftpName);
						try {
							OutputStream os = new FileOutputStream(file);
							flag = ftpClient.retrieveFile(ff, os);
						} catch (Exception e) {
							System.out.println(e.getMessage());
							LOGGER.error(e.getMessage(), e);
						}
						break;
					}
				}
			} catch (IOException e) {
				LOGGER.error("下载文件失败", e);
			}finally {
				Boolean close = closeConnect();
				LOGGER.info("连接是否关闭："+close);
			}
		}
		return flag;
	}

	/**
	 * 下载该目录下所有文件到本地
	 *
	 * @param ftpPath FTP服务器上的相对路径，例如：test/123
	 * @param savePath 保存文件到本地的路径，例如：D:/test
	 * @return 成功返回true，否则返回false
	 */
	public boolean downloadFiles(String ftpPath, String savePath) {
		// 登录
		boolean flag = false;
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 判断是否存在该目录
				if (!ftpClient.changeWorkingDirectory(path)) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录不存在");
					return flag;
				}
				ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
				String[] fs = ftpClient.listNames();
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录下没有文件");
					return flag;
				}
				for (String ff : fs) {
					String ftpName = new String(ff.getBytes(serverCharset), localCharset);
					File file = new File(savePath + '/' + ftpName);
					try  {
						OutputStream os = new FileOutputStream(file);
						ftpClient.retrieveFile(ff, os);
					} catch (Exception e) {
						System.out.println(e.getMessage()+e);
						LOGGER.error(e.getMessage(), e);
					}
				}
				flag = true;
			} catch (IOException e) {
				LOGGER.error("下载文件失败", e);
			}finally {
				Boolean close = closeConnect();
				LOGGER.info("连接是否关闭："+close);
			}
		}
		return flag;
	}

	/**
	 * 获取该目录下所有文件,以字节数组返回
	 *
	 * @param ftpPath FTP服务器上文件所在相对路径，例如：test/123
	 * @return Map<String, Object> 其中key为文件名，value为字节数组对象
	 */
	public Map<String, byte[]> getFileBytes(String ftpPath) {
		// 登录
		Map<String, byte[]> map = new HashMap<String, byte[]>();
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 判断是否存在该目录
				if (!ftpClient.changeWorkingDirectory(path)) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录不存在");
					return map;
				}
				ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
				String[] fs = ftpClient.listNames();
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录下没有文件");
					return map;
				}
				for (String ff : fs) {
					try  {
						InputStream is = ftpClient.retrieveFileStream(ff);
						String ftpName = new String(ff.getBytes(serverCharset), localCharset);
						ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
						byte[] buffer = new byte[BUFFER_SIZE];
						int readLength = 0;
						while ((readLength = is.read(buffer, 0, BUFFER_SIZE)) > 0) {
							byteStream.write(buffer, 0, readLength);
						}
						map.put(ftpName, byteStream.toByteArray());
						ftpClient.completePendingCommand(); // 处理多个文件
					} catch (Exception e) {
						System.out.println(e.getMessage());
						LOGGER.error(e.getMessage(), e);
					}
				}
			} catch (IOException e) {
				LOGGER.error("获取文件失败", e);
			}finally {
				Boolean close = closeConnect();
				LOGGER.info("连接是否关闭："+close);
			}
		}
		return map;
	}

	/**
	 * 根据名称获取文件，以字节数组返回
	 *
	 * @param ftpPath FTP服务器文件相对路径，例如：test/123
	 * @param fileName 文件名，例如：test.xls
	 * @return byte[] 字节数组对象
	 */
	public byte[] getFileBytesByName(String ftpPath, String fileName) {
		// 登录
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 判断是否存在该目录
				if (!ftpClient.changeWorkingDirectory(path)) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录不存在");
					return byteStream.toByteArray();
				}
				ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
				String[] fs = ftpClient.listNames();
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录下没有文件");
					return byteStream.toByteArray();
				}
				for (String ff : fs) {
					String ftpName = new String(ff.getBytes(serverCharset), localCharset);
					if (ftpName.equals(fileName)) {
						try {
							InputStream is = ftpClient.retrieveFileStream(ff);
							byte[] buffer = new byte[BUFFER_SIZE];
							int len = -1;
							while ((len = is.read(buffer, 0, BUFFER_SIZE)) != -1) {
								byteStream.write(buffer, 0, len);
							}
						} catch (Exception e) {
							LOGGER.error(e.getMessage(), e);
						}
						break;
					}
				}
			} catch (IOException e) {
				LOGGER.error("获取文件失败", e);
			}finally {
				Boolean close = closeConnect();
				LOGGER.info("连接是否关闭："+close);
			}
		}
		return byteStream.toByteArray();
	}

	/**
	 * 获取该目录下所有文件,以输入流返回
	 *
	 * @param ftpPath FTP服务器上文件相对路径，例如：test/123
	 * @return Map<String, InputStream> 其中key为文件名，value为输入流对象
	 */
	public Map<String, InputStream> getFileInputStream(String ftpPath) {
		// 登录
		Map<String, InputStream> map = new HashMap<String, InputStream>();
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 判断是否存在该目录
				if (!ftpClient.changeWorkingDirectory(path)) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录不存在");
					return map;
				}
				ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
				String[] fs = ftpClient.listNames();
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录下没有文件");
					return map;
				}
				for (String ff : fs) {
					String ftpName = new String(ff.getBytes(serverCharset), localCharset);
					InputStream is = ftpClient.retrieveFileStream(ff);
					map.put(ftpName, is);
					ftpClient.completePendingCommand(); // 处理多个文件
				}
			} catch (IOException e) {
				LOGGER.error("获取文件失败", e);
			}finally {
				Boolean close = closeConnect();
				LOGGER.info("连接是否关闭："+close);
			}
		}
		return map;
	}

	/**
	 * 根据名称获取文件，以输入流返回
	 *
	 * @param ftpPath FTP服务器上文件相对路径，例如：test/123
	 * @param fileName 文件名，例如：test.txt
	 * @return InputStream 输入流对象
	 */
	public InputStream getInputStreamByName(String ftpPath, String fileName) {
		// 登录
		InputStream input = null;
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 判断是否存在该目录
				if (!ftpClient.changeWorkingDirectory(path)) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录不存在");
					return input;
				}
				ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
				String[] fs = ftpClient.listNames();
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + ftpPath + "该目录下没有文件");
					return input;
				}
				for (String ff : fs) {
					String ftpName = new String(ff.getBytes(serverCharset), localCharset);
					if (ftpName.equals(fileName)) {
						input = ftpClient.retrieveFileStream(ff);
						break;
					}
				}
			} catch (IOException e) {
				LOGGER.error("获取文件失败", e);
			}finally {
				Boolean connect = closeConnect();
				LOGGER.info("连接关闭状态：" + connect);
			}
		}
		return input;
	}

	/**
	 * 根据文件夹，文件 名称，判断是否存在
	 *
	 * @param ftpPath FTP服务器上文件相对路径，例如：test/123
	 * @param fileName 文件名，例如：test.txt
	 * @return map
	 */
	public Map checkoutFtpPathAndFileName(String ftpPath, String fileName) {
		// 登录
		Map<String,Boolean> map = new HashMap<String, Boolean>();
		map.put("filePath",false);
		map.put("fileName",false);
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + ftpPath);
				// 判断是否存在该目录
				if (!ftpClient.changeWorkingDirectory(path)) {
					map.put("filePath",false);
				}else {
					map.put("filePath",true);
				}
				ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
				String[] fs = ftpClient.listNames();
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					map.put("fileName",false);
				}
				for (String ff : fs) {
					String ftpName = new String(ff.getBytes(serverCharset), localCharset);
					if (ftpName.equals(fileName)) {
						map.put("fileName",true);
					}
				}
			} catch (IOException e) {
				LOGGER.error("获取文件失败", e);
			}
		}
		return map;
	}
	/**
	 * 删除指定文件
	 *
	 * @param filePath 文件相对路径，例如：test/123/test.txt
	 * @return 成功返回true，否则返回false
	 */
	public boolean deleteFile(String filePath) {
		// 登录
		boolean flag = false;
		if (ftpClient != null) {
			try {
				String path = changeEncoding(BASE_PATH + filePath);
				flag = ftpClient.deleteFile(path);
			} catch (IOException e) {
				LOGGER.error("删除文件失败", e);
			} finally {
				Boolean close = closeConnect();
				LOGGER.info("连接是否关闭："+close);
			}
		}
		return flag;
	}

	/**
	 * 删除目录下所有文件
	 *
	 * @param dirPath 文件相对路径，例如：test/123
	 * @return 成功返回true，否则返回false
	 */
	public boolean deleteFiles(String dirPath) {
		// 登录
		boolean flag = false;
		if (ftpClient != null) {
			try {
				ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
				String path = changeEncoding(BASE_PATH + dirPath);
				String[] fs = ftpClient.listNames(path);
				// 判断该目录下是否有文件
				if (fs == null || fs.length == 0) {
					LOGGER.error(BASE_PATH + dirPath + "该目录下没有文件");
					return flag;
				}
				for (String ftpFile : fs) {
					ftpClient.deleteFile(ftpFile);
				}
				flag = true;
			} catch (IOException e) {
				LOGGER.error("删除文件失败", e);
			}finally {
				Boolean close = closeConnect();
				LOGGER.info("连接是否关闭："+close);
			}
		}
		return flag;
	}

	/**
	 * 连接FTP服务器
	 *
	 * @param address  地址，如：127.0.0.1
	 * @param port     端口，如：21
	 * @param username 用户名，如：root
	 * @param password 密码，如：root
	 */
	private Boolean login(String address, int port, String username, String password) {
		ftpClient = new FTPClient();
		try {
			ftpClient.connect(address, port);
			ftpClient.login(username, password);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				closeConnect();
				LOGGER.error("FTP服务器连接失败："+"地址："+address+"  端口："+port+"  用户名："+username+"  密码："+password);
			}else {
				b=true;
			}
		} catch (Exception e) {
			LOGGER.error("FTP登录失败", e);
		}
		return b;
	}

	/**
	 * 关闭FTP连接
	 *
	 */
	public Boolean closeConnect() {
		Boolean b=false;
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				b=true;
			} catch (IOException e) {
				LOGGER.error("关闭FTP连接失败", e);
			}
		}
		return b;
	}

	/**
	 * FTP服务器路径编码转换
	 *
	 * @param ftpPath FTP服务器路径
	 * @return String
	 */
	private static String changeEncoding(String ftpPath) {
		String directory = null;
		try {
			if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
				localCharset = CHARSET_UTF8;
			}
			directory = new String(ftpPath.getBytes(localCharset), serverCharset);
		} catch (Exception e) {
			LOGGER.error("路径编码转换失败", e);
		}
		return directory;
	}

	/**
	 * 在服务器上递归创建目录
	 *
	 * @param dirPath 上传目录路径
	 * @return
	 */
	private void createDirectorys(String dirPath) {
		try {
			if (!dirPath.endsWith("/")) {
				dirPath += "/";
			}
			String directory = dirPath.substring(0, dirPath.lastIndexOf("/") + 1);
			ftpClient.makeDirectory("/");
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			}else{
				start = 0;
			}
			end = directory.indexOf("/", start);
			while(true) {
				String subDirectory = new String(dirPath.substring(start, end));
				if (!ftpClient.changeWorkingDirectory(subDirectory)) {
					if (ftpClient.makeDirectory(subDirectory)) {
						ftpClient.changeWorkingDirectory(subDirectory);
					} else {
						LOGGER.info("创建目录失败");
						return;
					}
				}
				start = end + 1;
				end = directory.indexOf("/", start);
				//检查所有目录是否创建完毕
				if (end <= start) {
					break;
				}
			}
		} catch (Exception e) {
			LOGGER.error("上传目录创建失败", e);
		}
	}

	public static void main(String[] args) {
		String hostname = "192.168.199.239";
		int port = 21;
		String username = "vsftpd";
		String password = "zyq981512";
		String ftpPath  = "/";
		String savePath = "D:/account-balance-system.rar";
		FTPUtils ftpUtils = new FTPUtils(hostname, port, username, password, ftpPath);
		String fileName = "account-balance-system.rar";
		ftpUtils.uploadLocalFile(ftpPath, savePath, fileName);
//		String filename = "rocketmq-3.5.8.tar.gz";
//		String pathname = "/";
//		String filename = "rocketmq-3.5.8.tar.gz";
//		String localpath = "D:/";
//		ftpUtils.downloadFile(pathname, filename, localpath);
	}
}
