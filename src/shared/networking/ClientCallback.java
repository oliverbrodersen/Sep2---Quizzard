package shared.networking;

import shared.transferobjects.Lobby;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote
{
  void update(Lobby lobby) throws RemoteException;
}
