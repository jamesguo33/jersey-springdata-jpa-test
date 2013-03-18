package sample.op.eastblue.dawnisland.jpa;

import sample.op.eastblue.dawnisland.model.Task;

public interface TaskCustomRepository {

	public Task createSpecialTask(Task task);
}
