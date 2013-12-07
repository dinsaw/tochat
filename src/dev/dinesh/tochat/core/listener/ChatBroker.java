/**
 * 
 */
package dev.dinesh.tochat.core.listener;

import dev.dinesh.tochat.core.Chatter;
import dev.dinesh.tochat.core.Message;

/**
 * @author dsawant
 *
 */
public interface ChatBroker {
	public void addChatListener(ChatListener messageListener);
	public void removeChatListener(ChatListener messageListener);
	public void notifyForMessageReceived(Message message);
	public void notifyOnNewChatter(Chatter c);
}
