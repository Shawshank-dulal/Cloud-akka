package com.example;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Producer extends AbstractActor{
    ActorSystem system = ActorSystem.create();
    private int count=0;
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(StartMessage.class, this::onStartMessage)
                .match(String.class,this::onPrimeMessage)
                .build();
    }

    private void onPrimeMessage(String msg) {
        count++;
        System.out.println("Count is: "+count);
        System.out.println("Producer received Prime result: "+msg+" from worker: "+getSender());
        checkCount();
    }
    public void checkCount(){
        if(this.count==1000){
            system.terminate();
        }
    }

    private void onStartMessage(StartMessage msg) {
        System.out.println("Producer received message from main: ");
        ActorRef SupervisorRef = system.actorOf(Props.create(Supervisor.class));
        for (int i=0;i<1000;i++){
            int min = 10000;
            int max = 100000;
            int b = (int) (Math.random() * (max - min + 1) + min);
            SupervisorRef.tell(b,this.getSelf());
        }
        }

    }





