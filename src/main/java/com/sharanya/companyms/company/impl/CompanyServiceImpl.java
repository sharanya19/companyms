package com.sharanya.companyms.company.impl;


import com.sharanya.companyms.company.Company;
import com.sharanya.companyms.company.CompanyRepository;
import com.sharanya.companyms.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);

        if(optionalCompany.isPresent()){
            Company company1 = optionalCompany.get();
            company1.setDescription(company.getDescription());
            company1.setName(company.getName());

            companyRepository.save(company1);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        try {
             companyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
