package com.theironyard.Entities;

import javax.persistence.*;

/**
 * Created by michaelmernin on 2/7/17.
 */

@Entity
@Table(name = "companies")
public class Companies {

    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    Industries industry;

    @Column
    String companyName;

    @Column
    int CompanyLaborForce;

    @Column
    int CompanySalesTaxRevenueSupplied;

    @Column
    int CompanySales;

    @Column
    int CompanyRevenue;

    @Column
    int CompanyGsp;

    public Companies(Industries industry, String companyName, int companyLaborForce, int companySalesTaxRevenueSupplied, int companySales, int companyRevenue, int companyGsp) {
        this.industry = industry;
        this.companyName = companyName;
        CompanyLaborForce = companyLaborForce;
        CompanySalesTaxRevenueSupplied = companySalesTaxRevenueSupplied;
        CompanySales = companySales;
        CompanyRevenue = companyRevenue;
        CompanyGsp = companyGsp;
    }

    public int getCompanyGsp() {
        return CompanyGsp;
    }

    public void setCompanyGsp(int companyGsp) {
        CompanyGsp = companyGsp;
    }

    public Companies(int companyGsp) {

        CompanyGsp = companyGsp;
    }



    public Companies() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Industries getIndustry() {
        return industry;
    }

    public void setIndustry(Industries industry) {
        this.industry = industry;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyLaborForce() {
        return CompanyLaborForce;
    }

    public void setCompanyLaborForce(int companyLaborForce) {
        CompanyLaborForce = companyLaborForce;
    }

    public int getCompanySalesTaxRevenueSupplied() {
        return CompanySalesTaxRevenueSupplied;
    }

    public void setCompanySalesTaxRevenueSupplied(int companySalesTaxRevenueSupplied) {
        CompanySalesTaxRevenueSupplied = companySalesTaxRevenueSupplied;
    }

    public int getCompanySales() {
        return CompanySales;
    }

    public void setCompanySales(int companySales) {
        CompanySales = companySales;
    }

    public int getCompanyRevenue() {
        return CompanyRevenue;
    }

    public void setCompanyRevenue(int companyRevenue) {
        CompanyRevenue = companyRevenue;
    }
}
