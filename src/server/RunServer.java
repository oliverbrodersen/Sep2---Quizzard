package server;

import server.DAO.DatabaseConnection;
import server.model.QuizManagerImpl;
import server.networking.RMIServerImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class  RunServer
{
  public static void main(String[] args) throws RemoteException, AlreadyBoundException
  {
    RMIServerImpl ss = new RMIServerImpl(new QuizManagerImpl());
    ss.startServer();
  }
}
