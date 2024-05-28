package com.grocery_service.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EmbeddedOrderKey implements Serializable {
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Column(name = "order_id", nullable = false)
	    private int orderId;

	    @Column(name = "item_id", nullable = false)
	    private int itemId;

		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}


}
