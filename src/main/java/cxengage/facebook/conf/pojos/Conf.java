package cxengage.facebook.conf.pojos;

import java.util.List;
public class Conf {
	private List<Tenant> tenants;

	public List<Tenant> getTenants() {
		return tenants;
	}

	public void setTenants(List<Tenant> tenants) {
		this.tenants = tenants;
	}
}
