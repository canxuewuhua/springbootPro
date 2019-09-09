package com.example.demo.leetcode.trade;

import java.util.*;

/**
 * @author yongqiang.zhu
 * @date 2019/9/8 21:15
 */
public class TradeTest {
	public static void main(String[] args) {
		String[] transactions = {"alice,20,800,mtv","alice,50,1200,mtv"};
		System.out.println(invalidTransactions(transactions));
	}

	public static List<String> invalidTransactions(String[] transactions) {
		// 首先处理只有1条数据的情况
		if (transactions.length == 1) {
			return new ArrayList<String>() {
				{
					add(transactions[0]);
				}
			};
		}

		// 给一个最大的可定长度
		Set<String> result = new HashSet<>(transactions.length);
		// name -> Trans
		Map<String, List<Trade>> map_name = new HashMap<>(transactions.length);
		for (int i = 0; i < transactions.length; i++) {
			// 遍历所有的交易
			String records = transactions[i];
			// , 分割
			String[] record_temp = records.split(",");
			Trade trade = new Trade(
					record_temp[0], Integer.parseInt(record_temp[1]), Integer.parseInt(record_temp[2]), record_temp[3]);

			// 交易金额是否超过1000
			if (trade.amount > 1000) {
				result.add(records);
			}

			// 是否同名 不同城市
			if (map_name.containsKey(trade.name)) {
				// 说明同名
				List<Trade> beforeTrans = map_name.get(trade.name);
				for (Trade t : beforeTrans) {
					if (!trade.city.equals(t.city)
							&& Math.abs(trade.time - t.time) <= 60) {
						// 交易无效
						result.add(trade.toString());
						result.add(t.toString());
					}
				}
			}
			if (map_name.containsKey(trade.name)) {
				map_name.get(trade.name).add(trade);
			} else {
				List<Trade> temp = new ArrayList<>();
				temp.add(trade);
				map_name.put(trade.name, temp);
			}
		}
		return new ArrayList<>(result);
	}
}
