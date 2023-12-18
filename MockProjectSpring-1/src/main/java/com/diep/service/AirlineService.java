package com.diep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diep.model.AirlineInformation;
import com.diep.repository.AirlineRepository;

/**
 * This class provides service methods for managing airline information.
 * Account: DiepDP1
 * Birthday: 1998-02-27
 */
@Service
@Transactional
public class AirlineService {
	@Autowired
	AirlineRepository repo;
	/**
	 * 
	 * Account: DiepDP1
	 * Birthday: 1998-02-27
	 * @param airline
	 */
	public void save(AirlineInformation airline) {
		repo.save(airline);
	}
	/**
	 * 
	 * Account: DiepDP1
	 * Birthday: 1998-02-27
	 * @param id
	 * @return
	 */
	public AirlineInformation findAirlineById(long id) {
		return repo.findById(id).orElse(null);
	}
	
	/**
	 * 
	 * Account: DiepDP1
	 * Birthday: 1998-02-27
	 * @param pageable
	 * @return
	 */
	public Page<AirlineInformation> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}
	/**
	 * 
	 * Account: DiepDP1
	 * Birthday: 1998-02-27
	 * @return
	 */
	public List<AirlineInformation> findAirlineInformation() {
		return repo.findAirlineInformation();
	}
	/**
	 * 
	 * Account: DiepDP1
	 * Birthday: 1998-02-27
	 * @param airlineCode
	 * @param pageable
	 * @return
	 */
	public Page<AirlineInformation> findByAirlineCode(String airlineCode, Pageable pageable) {
		return repo.findByAirlineCode(airlineCode, pageable);
	}
	/**
	 * 
	 * Account: DiepDP1
	 * Birthday: 1998-02-27
	 * @return
	 */
	public List<Long> findAllId() {
		return repo.findAllIds();
	}
	/**
	 * 
	 * Account: DiepDP1
	 * Birthday: 1998-02-27
	 * @param airlineCode
	 * @return
	 */
	public int countAirlineCode(String airlineCode) {
		 return repo.countAirlineCode(airlineCode);
	}
	/**
	 * 
	 * Account: DiepDP1
	 * Birthday: 1998-02-27
	 * @param id
	 * @return
	 */
	public int updatePage(long id) {
		List<AirlineInformation> airlineList = repo.findAll();
		int page = -1;
		for(int i = 0; i < airlineList.size(); i++) {
			if(airlineList.get(i).getId() == id) {
				page = i;
				break;
			}
		}
		return page/13;
	}
}
