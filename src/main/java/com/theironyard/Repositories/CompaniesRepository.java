package com.theironyard.Repositories;

import com.theironyard.Entities.Companies;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by michaelmernin on 2/7/17.
 */
public interface CompaniesRepository extends CrudRepository<Companies, String> {

    Companies findFirstByCompanyName (String text);
}
