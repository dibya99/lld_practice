package elevatorsystem.entity;

import elevatorsystem.enums.Direction;
import elevatorsystem.enums.RequestType;

public class Request {
    private RequestType requestType;
    private Direction direction;
    private int targetFloor;

    public Request(RequestType requestType, Direction direction, int targetFloor) {
        this.requestType = requestType;
        this.direction = direction;
        this.targetFloor = targetFloor;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }
}
