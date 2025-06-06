/*
 * Copyright 2006-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.batch.samples.file.multirecordtype;

import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.samples.domain.trade.CustomerCredit;
import org.springframework.batch.samples.domain.trade.Trade;

/**
 * @author Dan Garrette
 * @since 2.0
 */
public class DelegatingTradeLineAggregator implements LineAggregator<Object> {

	private LineAggregator<Trade> tradeLineAggregator;

	private LineAggregator<CustomerCredit> customerLineAggregator;

	@Override
	public String aggregate(Object item) {
		if (item instanceof Trade trade) {
			return this.tradeLineAggregator.aggregate(trade);
		}
		else if (item instanceof CustomerCredit customerCredit) {
			return this.customerLineAggregator.aggregate(customerCredit);
		}
		else {
			throw new RuntimeException();
		}
	}

	public void setTradeLineAggregator(LineAggregator<Trade> tradeLineAggregator) {
		this.tradeLineAggregator = tradeLineAggregator;
	}

	public void setCustomerLineAggregator(LineAggregator<CustomerCredit> customerLineAggregator) {
		this.customerLineAggregator = customerLineAggregator;
	}

}
