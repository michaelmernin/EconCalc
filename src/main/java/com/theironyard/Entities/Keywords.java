package com.theironyard.Entities;

import javax.persistence.*;

/**
 * Created by michaelmernin on 2/2/17.
 */

@Entity
@Table(name = "keywords")
public class Keywords {

    @Id
    @GeneratedValue
    int id;

    @Column
    String keyword;

    @ManyToOne
    Industries industry;

    @ManyToOne
    Companies company;

    public Keywords(String keyword, Industries industry, Companies company) {
        this.keyword = keyword;
        this.industry = industry;
        this.company = company;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public Keywords(Companies company) {

        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Industries getIndustry() {
        return industry;
    }

    public void setIndustry(Industries industry) {
        this.industry = industry;
    }

    public Keywords() {

    }

}
