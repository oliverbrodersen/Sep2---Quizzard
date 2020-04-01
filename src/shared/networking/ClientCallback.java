package shared.networking;

import shared.transferobjects.Lobby;
import shared.transferobjects.Participant;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientCallback extends Remote
{
  void update(Lobby lobby) throws RemoteException;
  void connected() throws RemoteException;
  ArrayList<Participant> getParticipants()throws RemoteException;
  void newParticipant(Participant participant)throws RemoteException;
  void setLobby(Lobby lobby)throws RemoteException;
  Lobby getLobby()throws RemoteException;
}
