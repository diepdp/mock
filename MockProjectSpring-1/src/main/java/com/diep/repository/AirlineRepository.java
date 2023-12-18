package com.diep.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.diep.model.AirlineInformation;

/**
 * This interface represents a repository for managing airline information.
 * Account: DiepDP1
 * Birthday: 1998-02-27
 */
public interface AirlineRepository extends JpaRepository<AirlineInformation, Long>{
	/**
	 * 
	 * Account: DiepDP1
	 * Birthday: 1998-02-27
	 * @return
	 */
	@Query("SELECT a FROM AirlineInformation a ORDER BY a.airlineName")
	List<AirlineInformation> findAirlineInformation();
	
	/**
	 * 
	 * Account: DiepDP1
	 * Birthday: 1998-02-27
	 * @param airlineCode
	 * @param pageable
	 * @return
	 */
	@Query("SELECT a FROM AirlineInformation a WHERE (?1 = 'All' OR ?1 = '' OR a.airlineCode LIKE %?1%) ORDER BY a.airlineName")
	Page<AirlineInformation> findByAirlineCode(String airlineCode, Pageable pageable);
	
	/**
	 * 
	 * Account: DiepDP1
	 * Birthday: 1998-02-27
	 * @return
	 */
	@Query("SELECT a.id FROM AirlineInformation a")
	List<Long> findAllIds();
	
	/**
	 * 
	 * Account: DiepDP1
	 * Birthday: 1998-02-27
	 * @param airlineCode
	 * @return
	 */
	@Query("SELECT COUNT (*) FROM AirlineInformation a WHERE a.airlineCode = ?1")
	int countAirlineCode(String airlineCode);
}
