package sample.op.eastblue.dawnisland.model;


import javax.persistence.Entity;

@Entity
public class Task extends BaseEntity {

	private Integer priority;
	private String title;
	
	public Task() {
		this.priority=0;
	}
	
	public Task(String title) {
		this.title = title;
		this.priority = 0;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}
