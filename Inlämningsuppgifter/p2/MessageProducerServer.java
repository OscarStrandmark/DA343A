package p2;

import p1.MessageProducerInput;

public class MessageProducerServer {

	public MessageProducerServer(MessageProducerInput mpInput, int port) {
	}

	public void startServer() {
	}

}
/*
MessageProducerServer måste veta på vilken port den ska lyssna hantera uppkopplingar.
Servern måste också ha en referens till MessageProducerInput. Servern utgörs troligen av ett
antal klasser (kan vara inre klasser).
Servern ska vara en iterativ server som använder en tråd. Servern ska alltså hanterar en klient i
taget. Kommunikationen mellan klient och server sker med objektströmmar, dvs
ObjectInputStream respektive ObjectOutputStream ska användas.
*/