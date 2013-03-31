package com.fringe81.sample.http;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * HTTP Server sample with Java.
 * 
 * @author ji3
 *
 */
public class Server {

	/**
	 * Main method.
	 * 
	 * @param args nothing
	 * @throws Exception exception
	 */
	public static void main(String[] args) throws Exception {

		final HttpHandler contextHandler = new HttpHandler() {
			public void handle(HttpExchange exchange) throws IOException {
				BufferedOutputStream output = null;

				try {
					output = new BufferedOutputStream(
							exchange.getResponseBody());
					exchange.sendResponseHeaders(200, 0);

					final String body = "success!";
					output.write(body.getBytes());

					System.out.println("done!");
				} finally {
					if (output != null) {
						try {
							output.close();
						} catch (IOException e) {
						}
					}
				}
			}
		};

		final int bindPort = 8888;
		final HttpServer server = HttpServer.create(new InetSocketAddress(
				bindPort), 0);
		server.createContext("/context/", contextHandler);

		server.start();
	}
}