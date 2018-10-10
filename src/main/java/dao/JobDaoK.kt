package dao

import model.Job
import java.io.Serializable

open class JobDaoK: AbstractDaoK<Job>(Job::class.java), Serializable {
}