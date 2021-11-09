package ggc.core.notifications;

import java.io.Serializable;

public interface Observer extends Serializable {
	public void notify(String notification);
}
