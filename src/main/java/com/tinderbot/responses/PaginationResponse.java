package com.tinderbot.responses;

import java.util.List;

public class PaginationResponse {
	
	private Long total;
	
	private List<?> values;
	
	public PaginationResponse() {
		
	}

	public PaginationResponse(Long total, List<?> values) {
		super();
		this.total = total;
		this.values = values;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getValues() {
		return values;
	}

	public void setValues(List<?> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "PaginationResponse [total=" + total + ", values=" + values + "]";
	}
	
}
