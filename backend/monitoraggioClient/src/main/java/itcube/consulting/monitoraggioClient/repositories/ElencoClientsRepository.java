package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.ElencoClients;
import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.database.ShallowClient;

public interface ElencoClientsRepository extends CrudRepository<ElencoClients,Integer>{
	
	@Query(value="Select * from elenco_clients where id_company= :company", nativeQuery=true)
	List<ElencoClients> getElencoClients(@Param("company") ElencoCompanies company);
	
	@Query(value="SELECT *\n"
			+ "FROM elenco_clients ec\n"
			+ "WHERE ec.id_company = :company", nativeQuery=true)
	List<ElencoClients> getShallowClients(@Param("company") ElencoCompanies company);
	
	@Query(value="SELECT *\n"
			+ "FROM elenco_clients ec\n"
			+ "where ec.id= :id_client", nativeQuery=true)
	ElencoClients getDeepClient(@Param("id_client") int id_client);	
	
	@Query(value="Select elenco_companies.id from elenco_companies join elenco_clients on elenco_companies.id_client=elenco_clients.id where id_client= :id_client", nativeQuery=true)
	int getIdCompany(@Param("id_client") int id_client);
}
