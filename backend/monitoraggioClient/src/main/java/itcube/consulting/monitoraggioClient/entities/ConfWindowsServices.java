package itcube.consulting.monitoraggioClient.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ConfWindowsServices {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "nome_servizio")
	private Monitoraggio monitoraggio;
	//private String nome_servizio;
	
	@OneToOne
	@JoinColumn(name = "id_client")
	private ElencoClients elencoClients;
	//private int id_client;
	private int stato;
	private Date date_and_time;
	
	public ConfWindowsServices() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate_and_time() {
		return date_and_time;
	}

	public void setDate_and_time(Date date_and_time) {
		this.date_and_time = date_and_time;
	}

	public ElencoClients getElencoClients() {
		return elencoClients;
	}

	public void setElencoClients(ElencoClients elencoClients) {
		this.elencoClients = elencoClients;
	}

	public int getStato() {
		return stato;
	}

	public void setStato(int stato) {
		this.stato = stato;
	}

	public Monitoraggio getMonitoraggio() {
		return monitoraggio;
	}

	public void setMonitoraggio(Monitoraggio monitoraggio) {
		this.monitoraggio = monitoraggio;
	}
}
