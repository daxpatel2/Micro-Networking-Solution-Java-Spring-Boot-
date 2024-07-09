package com.daxpatel.jobms.job.impl;

import com.daxpatel.jobms.job.Job;
import com.daxpatel.jobms.job.JobRepository;
import com.daxpatel.jobms.job.JobService;
import com.daxpatel.jobms.job.dto.JobWithCompanyDTO;
import com.daxpatel.jobms.job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     *
     * RestTemplate restTemplate = new RestTemplate();
     * url will be the url to get information from
     * lets try getting information about a company with id 1
     * when ever we make a get request to this url
     * the response will be mapped to the Company.Class object
     * this is what we do for inter class communications in microservices
     * Company company = restTemplate.getForObject("<a href="https://localhost:8081/companies/1">...</a>", Company.class);
     * System.out.println("Company name: " + company.getName());
     * System.out.println("Company id: " + company.getId());
     *
     * @return List of JobWithCompanyDTO that is an object of Company and Job -> where company gets set using .setComapny()
     */
    @Override
    public List<JobWithCompanyDTO> getJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDTOs).collect(Collectors.toList());
    }

    private JobWithCompanyDTO convertToDTOs(Job job) {
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("https://localhost:8081/companies/"+job.getCompanyId(), Company.class);
        jobWithCompanyDTO.setCompany(company);
        return jobWithCompanyDTO;

    }

    @Override
    public String createJobs(Job job) {
        jobRepository.save(job);
        return "Job created successfully";
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setTitle(updatedJob.getTitle());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
