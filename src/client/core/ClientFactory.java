package client.core;

import client.networking.Client;
import client.networking.RMIClient;
import shared.transferobjects.UserID;

public class ClientFactory {

  private static ClientFactory clientFactory;
  private Client client;

  public Client getClient() {
    if(client == null) {
      client = new RMIClient();
      client.startClient();
    }
    return client;
  }

  public static ClientFactory getInstance() {
    if (clientFactory == null) {
      clientFactory = new ClientFactory();
    }
    return clientFactory;
  }
}
