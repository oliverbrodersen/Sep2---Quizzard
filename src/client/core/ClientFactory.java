package client.core;

import client.networking.Client;
import client.networking.RMIClient;

public class ClientFactory {

  private Client client;

  public Client getClient() {
    if(client == null) {
      client = new RMIClient();
      client.startClient();
    }
    return client;
  }



}
