package com.example;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Worker extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(Integer.class, this::onStartMessage)
                .build();
    }

    private void onStartMessage(int msg) {
        System.out.println("Worker " + this.getSelf() + " received random number from " + getSender() + " " + msg);
        int num = msg;
        boolean flag = false;
        for (int i = 2; i <= num / 2; ++i) {
            if (num % i == 0) {
                flag = true;
                break;
            }
        }
        if (!flag)
            getSender().tell(new String("The Number "+String.valueOf(num)+" Is a Prime Number"),getSelf());
        else
            getSender().tell(new String("The Number "+String.valueOf(num)+" Is Not a Prime Number"),getSelf());
    }
    }





