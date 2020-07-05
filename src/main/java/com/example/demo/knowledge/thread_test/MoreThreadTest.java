package com.example.demo.knowledge.thread_test;

import lombok.Data;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

/**
 * 运行结果如下：
 * 获取商户列表多线程开始
 * 处理一些逻辑
 * 处理一些逻辑
 * 处理一些逻辑
 * 处理一些逻辑
 * name:zhangsan1sortNum:1
 * name:zhangsan2sortNum:2
 * name:zhangsan3sortNum:3
 * name:zhangsan4sortNum:4
 * 获取商户列表多线程结束
 */

public class MoreThreadTest {
    public static void main(String[] args) {
        PartnerDTO partnerDTO1 = new PartnerDTO("zhangsan1","1");
        PartnerDTO partnerDTO2 = new PartnerDTO("zhangsan2","2");
        PartnerDTO partnerDTO3 = new PartnerDTO("zhangsan3","3");
        PartnerDTO partnerDTO4 = new PartnerDTO("zhangsan4","4");

        // １. 对于随机访问，ArrayList要优于LinkedList
        // ２. 对于插入和删除操作，LinkedList优于ArrayList
        // 3 . LinkedList比ArrayList更占内存
        // 4 . ArrayList的实现是基于数组，LinkedList的实现是基于双向链表

        // LinkedList是线程不安全的
        /**
         * LinkedList的线程安全处理
         * 方法一:List<String> list = Collections.synchronizedList(new LinkedList<String>());
         * 方法二:将LinkedList全部换成ConcurrentLinkedQueue
         */
        LinkedList<PartnerDTO> partnerDTOList = new LinkedList<>();
        partnerDTOList.add(partnerDTO1);
        partnerDTOList.add(partnerDTO2);
        partnerDTOList.add(partnerDTO3);
        partnerDTOList.add(partnerDTO4);

        System.out.println("获取商户列表多线程开始");
        // CountDownLatch能够使一个或多个线程等待其他线程完成各自的工作后再执行；CountDownLatch是JDK 5+里面闭锁的一个实现
        final CountDownLatch latch = new CountDownLatch(partnerDTOList.size());
        try {
            for (PartnerDTO partnerDTO : partnerDTOList) {
                // 处理一些业务逻辑
                System.out.println("处理一些逻辑");

                // 通过子线程处理一些其他业务逻辑
                new Thread() {
                    @Override
                    public void run() {
                        //org.slf4j.MDC.put(ConstCommon.REQUEST_ID, UUID.randomUUID().toString());
                        try {
                            String name = partnerDTO.getName();
                            String sortNum = partnerDTO.getSortNum();
                            System.out.println("name:" + name + "sortNum:" + sortNum);
                        } catch (Exception e) {
                            System.out.println("商户列表获取失败");
                        } finally {
                            latch.countDown();
                        }
                        //org.slf4j.MDC.remove(ConstCommon.REQUEST_ID);
                    }
                }.start();
            }
            latch.await();
        }catch (Exception e){
            System.out.println("商户列表获取异常");
        }
        System.out.println("获取商户列表多线程结束");
    }
}
@Data
class PartnerDTO{
    private String name;
    private String sortNum;
    public PartnerDTO(){}
    public PartnerDTO(String name, String sortNum){
        this.name = name;
        this.sortNum = sortNum;
    }
}
