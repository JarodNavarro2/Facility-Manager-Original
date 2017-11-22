package facilities;

public class Room {

    public String name;
    public int room_max_capacity;
    public int room_current_capacity;
    public int room_number;
    public boolean room_occupancy;

    public Room(String name, int capacity, int max_capacity, int rnumber) { //constructor
        this.name = name;
        this.room_max_capacity = max_capacity;
        this.room_number = rnumber;
        if (capacity == 0){ //user did NOT input a capacity
            this.room_occupancy = false;
            this.room_current_capacity = 0;
        }
        else {
            this.room_occupancy = true;
            this.room_current_capacity = capacity;
        }
    }

    public void setRoomName(String name) { this.name = name; }

    public boolean setRoom_current_capacity(int current_capacity) {
        //checking to make sure capacity has not reached maximum capacity
        boolean success_token;
        if (current_capacity >= this.room_max_capacity || current_capacity < 0) {
            success_token = false;
        } else {
            this.room_current_capacity = current_capacity;
            success_token = true;
        }
        return success_token;
    }

    public boolean setRoom_max_capacity(int max_capacity) {
        //checking to make sure new max capacity is not less than current capacity
        boolean success_token;
        if (max_capacity < this.room_current_capacity) {
            success_token = false;
        } else {
            this.room_max_capacity = max_capacity;
            success_token = true;
        }
        return success_token;
    }

    public void setRoom_number(int room_number) { this.room_number = room_number; }

    public String getRoomName() { return this.name; }

    public int getRoomMaxCapacity(){
        return this.room_max_capacity;
    }

    public int getRoomNumber() { return this.room_number; }

    public int getRoom_current_capacity(){ return this.room_current_capacity;}

    public boolean room_occupancy () {
        if (this.room_current_capacity > 0){
            this.room_occupancy = true;
        }
        else{
            this.room_occupancy = false;
        }
        return this.room_occupancy;
    }



}
