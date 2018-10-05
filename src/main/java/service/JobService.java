package service;

import dao.JobDao;
import model.Job;

import javax.inject.Inject;
import java.io.Serializable;

public class JobService extends ServiceAbstract<Job> implements Serializable {

    private static final long serialVersionUID = -8259604384570933622L;
    private JobDao dao;

    @Inject
    public JobService(JobDao dao) {
        super(dao);
        this.dao = dao;
    }
}
