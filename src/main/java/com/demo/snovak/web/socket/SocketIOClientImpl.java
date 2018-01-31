package com.demo.snovak.web.socket;

import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.client.IO.Options;
import io.socket.emitter.Emitter;


@Component
public class SocketIOClientImpl implements SocketIOClient {

	private static final Logger log = LoggerFactory.getLogger(SocketIOClient.class);
	private static final String NODEJS_SOCKET_IO_URL = null;
	
	
	private Socket socket;
	
	public interface ChatEngineEvents {
		String PROFANITY_DETECTED = "profanity";
	}
	
	public void sendMessage(String topic, String payload) {
		socket.emit(topic, payload);
	}
	
	@PostConstruct
	void initializeSocketIOClient() throws URISyntaxException {
		Options opts = new IO.Options();
		// re-connect if connection drops
		opts.forceNew = true;
		opts.reconnection = true;

		socket = IO.socket(NODEJS_SOCKET_IO_URL, opts);

		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				log.info("WebSocket connected ...");
			}

		}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				log.info("WebSocket disconnected ...");
			}

		}).on(ChatEngineEvents.PROFANITY_DETECTED, new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				
			}

		});
	}

}
