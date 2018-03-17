package works.weave.socks.shipping.entities;

import java.util.UUID;

public class Shipment {
    private String id;

    private String name;
    
    private String amount;

    public Shipment() {
        this("");
    }

    public Shipment(String name) {
        this(UUID.randomUUID().toString(), name,"4.99");
    }

    public Shipment(String id, String name, String amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ",amount='" + amount +
                '\'' +'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Shipment shipment = (Shipment) o;

        return getId() != null ? getId().equals(shipment.getId()) : shipment.getId() == null;

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
