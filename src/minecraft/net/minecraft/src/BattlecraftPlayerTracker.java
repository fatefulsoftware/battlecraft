package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class BattlecraftPlayerTracker {
	List<OnPlayerJoinedListener> joinListeners;
	List<OnPlayerLeftListener> leftListeners;
	
	protected static final BattlecraftPlayerTracker sharedInstance = new BattlecraftPlayerTracker();
	
	public BattlecraftPlayerTracker() {
		joinListeners = new ArrayList<OnPlayerJoinedListener>();
		leftListeners = new ArrayList<OnPlayerLeftListener>();
	}
	
	public static BattlecraftPlayerTracker getSharedInstance () {
		return sharedInstance;
	}
	
	public void notifyPlayerJoined (EntityPlayer player) {
		for (OnPlayerJoinedListener listener : joinListeners)
			listener.onPlayerJoined(player);
	}
	
	public void notifyPlayerLeft (EntityPlayer player) {
		for (OnPlayerLeftListener listener : leftListeners)
			listener.onPlayerLeft(player);
	}

	public void addJoinListener (OnPlayerJoinedListener listener) {
		joinListeners.add(listener);
	}

	public void addLeaveListener (OnPlayerLeftListener listener) {
		leftListeners.add(listener);
	}
}
