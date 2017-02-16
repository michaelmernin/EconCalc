package com.theironyard.Controllers;




import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Keyword;
import com.theironyard.Entities.Companies;
import com.theironyard.Entities.Industries;
import com.theironyard.Entities.Keywords;


import com.theironyard.Repositories.CompaniesRepository;
import com.theironyard.Repositories.IndustriesRepository;
import com.theironyard.Repositories.KeywordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

import javax.annotation.PostConstruct;

import static econalgorithms.core.lbforcetotal;

/**
 * Created by michaelmernin on 2/3/17.
 */

@Controller
public class ProgramController  {

    @Value("${api.key}")
    String api;


    public static ArrayList<Industries> industryUsed = new ArrayList<>();

    public static ArrayList<Companies> companiesUsed = new ArrayList<>();

    public static ArrayList<com.ibm.watson.developer_cloud.alchemy.v1.model.Keywords> pulledKeys = new ArrayList<>();

    @Autowired
    KeywordsRepository keywords;

    @Autowired
    IndustriesRepository industries;

    @Autowired
    CompaniesRepository companies;

    @PostConstruct
    public void fillData() {
        if(industries.count() == 0) {
            Industries finS = new Industries("Financial Services", 0, 185775, 0, 0, 0);
            industries.save(finS);
            Industries rtailT = new Industries("Retail Trade", 0, 309818, 0, 0, 0);
            industries.save(rtailT);
        }
        if(companies.count() ==0) {
            Companies target = new Companies(industries.findFirstByIndustry("Retail Trade"), "Target", 26694, 0, 0, 0, 0);
            companies.save(target);
            Companies bestBuy = new Companies(industries.findFirstByIndustry("Retail Trade"), "Best Buy", 8000, 0, 0, 0, 0);
            companies.save(bestBuy);
            Companies wells = new Companies(industries.findFirstByIndustry("Financial Services"), "Wells Fargo", 20000, 0, 0, 0, 0);
            companies.save(wells);

            //Companies tcf = new Companies(industries.findFirstByIndustry("Financial Services"), "TCF", 3003, 0, 0, 0, 0);
            //companies.save(tcf);

            //Companies us = new Companies(industries.findFirstByIndustry("Financial Services"), "US Bank", 12010, 0, 0, 0, 0);
            //companies.save(us);
            //Companies mayo = new Companies(industries.findFirstByIndustry("Education & Health"), "Mayo", 41892, 0, 0, 0, 0);
            //companies.save(mayo);

        }

        if(keywords.count() == 0) {
            Keywords target = new Keywords("Target", industries.findFirstByIndustry("Retail Trade"), companies.findFirstByCompanyName("target"));
            keywords.save(target);
            Keywords bestBuy = new Keywords("Best Buy", industries.findFirstByIndustry("Retail Trade"), companies.findFirstByCompanyName("Best Buy"));
            keywords.save(bestBuy);
            Keywords wells = new Keywords("wells fargo", industries.findFirstByIndustry("Financial Services"), companies.findFirstByCompanyName("wells fargo"));
            keywords.save(wells);
        }

    }

    @RequestMapping(path = "/upload-file", method = RequestMethod.POST)
    public String upload(String text, Model model) throws Exception {

        AlchemyLanguage service = new AlchemyLanguage();
        service.setApiKey(api);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(AlchemyLanguage.TEXT, text);

        com.ibm.watson.developer_cloud.alchemy.v1.model.Keywords jsonKeywords = service.getKeywords(params).execute();

        for(Keyword pulledWord : jsonKeywords.getKeywords()) {
            Companies co = companies.findFirstByCompanyName(pulledWord.getText());
            Keywords wd = keywords.findFirstByKeyword(pulledWord.getText());
            if(wd != null) {

                industryUsed.add(wd.getIndustry());
            }
            if(co != null) {
                companiesUsed.add(co);
            }
        }
        int tGsp = 0;
        int tR = 0;
        int tS = 0;
        int salesTaxRev = 0;

        String industryName = null;

        if(industryUsed.size() == 0 && companiesUsed.size() == 0) {
            throw new Exception("No Keywords were ignited");
        }

        /*for(Industries item : industryUsed) {

            tGsp += item.getIndustryTotalGsp(); //GrossStateProduct
            tLf += item.getIndustryTotalLaborForce();
            tR  += item.getIndustryTotalRevenue();
            tS += item.getIndustryTotalSales();
            salesTaxRev += item.getIndustryTotalSalesTaxRevenue();
            industryName = item.getIndustry(); //HOW???????industry class called??????
        }*/

        int lf = 0;
        int revenue = 0;
        int sales = 0;
        int salesTaxRevenue = 0;
        int gsp = 0;
        String companyName = null;
        Industries industry = null;

        //int tLf = 0;

        HashSet<Industries> indulf = new HashSet<>();

        for(Companies item : companiesUsed) {

            lf += item.getCompanyLaborForce(); //
            revenue += item.getCompanyRevenue();
            sales  += item.getCompanySales();
            salesTaxRevenue += item.getCompanySalesTaxRevenueSupplied();
            gsp += item.getCompanyGsp();
            companyName = item.getCompanyName();
            industry = item.getIndustry();

            indulf.add(industry);

        }

        int tLf = 0;

        for(Industries i : indulf) {

            tLf += i.getIndustryTotalLaborForce();

        }
        double laborForcePercentage = lbforcetotal(lf, tLf); //items are put through clojure

        model.addAttribute("industries", industryUsed);
        model.addAttribute("companies", companiesUsed);
        model.addAttribute("calcs", lbforcetotal(lf, tLf));


        return "Results";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String analysis(){
        return "index";
    }
}
