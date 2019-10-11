package com.example.demo.test;

import com.example.demo.domain.InoutPlanLedgerVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yongqiang.zhu
 * @date 2019/10/11 20:28
 */
public class InoutPlanMinOverdueDay {

	public static void main(String[] args) {
		List<InoutPlanLedgerVO> inoutPlanLedgerVOList = getInoutPlanLedgerVOS();
		Collections.sort(inoutPlanLedgerVOList, new Comparator<InoutPlanLedgerVO>() {
			@Override
			public int compare(InoutPlanLedgerVO o1, InoutPlanLedgerVO o2) {
				return o1.getLeaseTime() - o2.getLeaseTime();
			}
		});
		Integer overdueDays = inoutPlanLedgerVOList.get(0).getOverdueDay();
		System.out.println("逾期期次最小的一期的逾期天数为：" + overdueDays);


		// 同样，lambda表达式也能对期数进行排序，一样能达到排序的效果
		List<InoutPlanLedgerVO> userAgeList = inoutPlanLedgerVOList.stream().sorted(Comparator.comparing(InoutPlanLedgerVO::getLeaseTime)).collect(Collectors.toList());
		System.out.println(userAgeList.get(0).getOverdueDay());
	}

	private static List<InoutPlanLedgerVO> getInoutPlanLedgerVOS(){
		List<InoutPlanLedgerVO> inoutPlanLedgerVOS = new ArrayList<>();
		InoutPlanLedgerVO inoutPlanLedgerVO = new InoutPlanLedgerVO();
		inoutPlanLedgerVO.setCustomerId("1027341854");
		inoutPlanLedgerVO.setLeaseTime(1);
		inoutPlanLedgerVO.setOverdueDay(62);

		InoutPlanLedgerVO inoutPlanLedgerVO1 = new InoutPlanLedgerVO();
		inoutPlanLedgerVO1.setCustomerId("1027341854");
		inoutPlanLedgerVO1.setLeaseTime(2);
		inoutPlanLedgerVO1.setOverdueDay(32);

		InoutPlanLedgerVO inoutPlanLedgerVO2 = new InoutPlanLedgerVO();
		inoutPlanLedgerVO2.setCustomerId("1027341854");
		inoutPlanLedgerVO2.setLeaseTime(3);
		inoutPlanLedgerVO2.setOverdueDay(2);

		inoutPlanLedgerVOS.add(inoutPlanLedgerVO);
		inoutPlanLedgerVOS.add(inoutPlanLedgerVO1);
		inoutPlanLedgerVOS.add(inoutPlanLedgerVO2);
		return inoutPlanLedgerVOS;
	}
}
