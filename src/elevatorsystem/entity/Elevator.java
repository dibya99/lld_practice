package elevatorsystem.entity;

import com.sun.source.tree.Tree;
import elevatorsystem.enums.Direction;
import elevatorsystem.observer.ElevatorObserver;
import elevatorsystem.observer.ElevatorObserverImpl;
import elevatorsystem.state.ElevatorState;
import elevatorsystem.state.IdleState;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Elevator implements Runnable{
    private int id;
    private int currentFloor;
    private Set<Integer> upRequests;
    private Set<Integer> downRequests;
    private boolean isRunning;
    private ElevatorState elevatorState;
    private List<ElevatorObserver> observerList;

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void notifyObservers()
    {
        for(ElevatorObserver observer : observerList)
        {
            observer.display(this);
        }
    }


    public Elevator(int id)
    {
        this.id=id;
        upRequests=new TreeSet<>();
        downRequests=new TreeSet<>((a,b) -> b.compareTo(a));
        isRunning=true;
        elevatorState=new IdleState();
        observerList = new ArrayList<>();
        observerList.add(new ElevatorObserverImpl());
        currentFloor=0;
    }

    public void move()
    {
        this.elevatorState.move(this);
    }

    public void addRequest(Request request)
    {
        this.elevatorState.addRequest(this,request);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Integer> getUpRequests() {
        return upRequests;
    }

    public void setUpRequests(Set<Integer> upRequests) {
        this.upRequests = upRequests;
    }

    public Set<Integer> getDownRequests() {
        return downRequests;
    }

    public void setDownRequests(Set<Integer> downRequests) {
        this.downRequests = downRequests;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "id=" + id +
                ", currentFloor=" + currentFloor +
                ", isRunning=" + isRunning +
                ", elevatorState=" + elevatorState +
                '}';
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }

    @Override
    public void run() {
        while(isRunning)
        {

            try
            {
                move();
                Thread.sleep(5000);
            }
            catch(InterruptedException exception)
            {
                Thread.currentThread().interrupt();
                isRunning=false;
                exception.printStackTrace();
            }
            catch(Exception exception)
            {
                isRunning=false;
                exception.printStackTrace();
            }
        }
    }
}
