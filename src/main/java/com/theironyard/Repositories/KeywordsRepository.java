package com.theironyard.Repositories;

import com.theironyard.Entities.Keywords;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by michaelmernin on 2/2/17.
 */
public interface KeywordsRepository extends CrudRepository<Keywords, Integer> {

    Keywords findFirstByKeyword(String text);
}
