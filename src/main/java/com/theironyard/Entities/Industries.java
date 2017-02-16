package com.theironyard.Entities;

import javax.persistence.*;


/**
 * Created by michaelmernin on 2/6/17.
 */

@Entity
@Table(name = "industries")
public class Industries {

    @Id
    @GeneratedValue
    int id;

    @Column
    String industry;

    @Column
    int industryTotalGsp;

    @Column
    int industryTotalLaborForce;

    @Column
    int industryTotalRevenue;

    @Column
    int industryTotalSales;

    @Column
    int industryTotalSalesTaxRevenue;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public int getIndustryTotalGsp() {
        return industryTotalGsp;
    }

    public void setIndustryTotalGsp(int industryTotalGsp) {
        this.industryTotalGsp = industryTotalGsp;
    }

    public int getIndustryTotalLaborForce() {
        return industryTotalLaborForce;
    }

    public void setIndustryTotalLaborForce(int industryTotalLaborForce) {
        this.industryTotalLaborForce = industryTotalLaborForce;
    }

    public int getIndustryTotalRevenue() {
        return industryTotalRevenue;
    }

    public void setIndustryTotalRevenue(int industryTotalRevenue) {
        this.industryTotalRevenue = industryTotalRevenue;
    }

    public int getIndustryTotalSales() {
        return industryTotalSales;
    }

    public void setIndustryTotalSales(int industryTotalSales) {
        this.industryTotalSales = industryTotalSales;
    }

    public int getIndustryTotalSalesTaxRevenue() {
        return industryTotalSalesTaxRevenue;
    }

    public void setIndustryTotalSalesTaxRevenue(int industryTotalSalesTaxRevenue) {
        this.industryTotalSalesTaxRevenue = industryTotalSalesTaxRevenue;
    }

    public Industries() {

    }

    public Industries(String industry, int industryTotalGsp, int industryTotalLaborForce, int industryTotalRevenue, int industryTotalSales, int industryTotalSalesTaxRevenue) {

        this.industry = industry;
        this.industryTotalGsp = industryTotalGsp;
        this.industryTotalLaborForce = industryTotalLaborForce;
        this.industryTotalRevenue = industryTotalRevenue;
        this.industryTotalSales = industryTotalSales;
        this.industryTotalSalesTaxRevenue = industryTotalSalesTaxRevenue;
    }
}
