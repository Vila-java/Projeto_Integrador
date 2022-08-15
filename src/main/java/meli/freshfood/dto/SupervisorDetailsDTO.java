package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meli.freshfood.model.Supervisor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDetailsDTO {

	private Long supersivorId;
	private String firstName;
	private String lastName;
	private String warehouseSupervisor;

	public SupervisorDetailsDTO(Supervisor supervisor) {

		this.supersivorId = supervisor.getSupersivorId();
		this.firstName = supervisor.getFirstName();
		this.lastName = supervisor.getLastName();
		this.warehouseSupervisor = supervisor.getWarehouseSupervisor();
	}
}
