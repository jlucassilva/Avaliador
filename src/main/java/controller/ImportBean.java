package controller;

import httpConsumer.APIRequest;
import httpConsumer.APIResultException;
import model.Job;
import service.JobService;
import service.exception.ServiceException;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ImportBean implements Serializable {
    private static final long serialVersionUID = -1506431280002162927L;

    @Inject
    private JobService jobService;

    @Inject
    private APIRequest consumer;

    private List<Job> jobs;

    @PostConstruct
    public void init() {
        System.out.println("Iniciando");
    }

    private void atualizaJobs() {
        jobs = jobService.listarTodos();
    }

    public void importarTudo() throws IOException, APIResultException {
        List<util.Job> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jobs.addAll(consumer.executeGet("https://jobs.github.com/positions.json?search=code&page=" + i)
                    .getResultListAs(util.Job.class));
        }
        jobs.stream()
                .map(this::translateJob)
                .forEach(this::salvar);
    }


    private void salvar(Job job) {
        try {
            jobService.salvar(job);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }


    public Job translateJob(util.Job falseJob) {
        return new Job(falseJob.getCreated_at(),
                falseJob.getTitle(),
                falseJob.getLocation(),
                falseJob.getType(),
                falseJob.getDescription(),
                falseJob.getCompany(),
                falseJob.getUrl());
    }

    public List<Job> getJobs() {
        if (jobs == null) {
            atualizaJobs();
        }
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }



}
