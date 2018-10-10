package service;

import dao.JobDaoK;
import model.Job;

import javax.inject.Inject;
import java.io.Serializable;

public class JobService extends ServiceAbstractK<Job> implements Serializable {

	private static final long serialVersionUID = -8259604384570933622L;
	private JobDaoK dao;

	@Inject
	public JobService(JobDaoK dao) {
		super(dao);
		this.dao = dao;
	}
}
