package com.daxpatel.jobms.job;

import com.daxpatel.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

/**
 * Defines only the methods not the implementations
 */
public interface JobService {
    List<JobWithCompanyDTO> getJobs();
    String createJobs(Job job);
    Job getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJob(Long id, Job job);
}
