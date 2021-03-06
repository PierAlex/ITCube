package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.VisualizzazioneEventi;

public interface VisualizzazioneEventiRepository extends CrudRepository<VisualizzazioneEventi, Integer>{
	
	@Query(value="select count(*) from visualizzazione_eventi where id_client= :id_client and level='Error' and date_and_time_evento=CURDATE()",nativeQuery=true)
	public int getProblemiOggi(@Param("id_client") int id_client);
	
	@Query(value="select count(*) from visualizzazione_eventi where id_client= :id_client and level='Warning' and date_and_time_evento=CURDATE()",nativeQuery=true)
	public int getWarningOggi(@Param("id_client") int id_client);
	
	@Query(value="select count(*) from visualizzazione_eventi where id_client= :id_client and sottocategoria= :sottocategoria",nativeQuery=true)
	public int contaSottocategoria(@Param("id_client") int id_client, @Param("sottocategoria") int sottocategoria);
	
	@Query(value="select distinct sottocategoria from visualizzazione_eventi where id_client= :id_client",nativeQuery=true)
	public List<Integer> getSottocategorie(@Param("id_client") int id_client);
}
