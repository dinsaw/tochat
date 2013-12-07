/**
 * 
 */
package dev.dinesh.tochat.core.listener;

import dev.dinesh.tochat.core.Message;

/**
 * @author dinesh
 *
 */
public interface ChatListener {
	public void onMessageReceived(Message message);
}
