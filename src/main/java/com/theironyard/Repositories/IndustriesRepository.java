package com.theironyard.Repositories;

import com.theironyard.Entities.Industries;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by michaelmernin on 2/7/17.
 */
public interface IndustriesRepository extends CrudRepository<Industries, Integer> {

    Industries findFirstByIndustryTotalLaborForce (Integer number);

    Industries findFirstByIndustry (String text);
}
