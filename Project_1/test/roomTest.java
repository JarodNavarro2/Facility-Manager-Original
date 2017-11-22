import facilities.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class roomTest {

    public Room room;
    @BeforeEach
    void setUp() {
        room = new Room("Test Room",150,200,1);

    }

   @Test
    public void testRoomCreation(){
        assert (room.room_occupancy() == true);
        assert (room.getRoomMaxCapacity() == 200);
        assert (room.getRoomName().equals("Test Room"));
        assert (room.getRoomNumber() == 1);
        }
     @Test
    public void testRoomSetters(){
        Room room = new Room("Test Room",150,200,1);

        room.setRoom_max_capacity(1000);
        assert (room.getRoomMaxCapacity() == 1000);

        room.setRoom_current_capacity(400);
        assert (room.getRoom_current_capacity() == 400);

        room.setRoom_number(0101);
        assert (room.getRoomNumber() == 0101);
        room.setRoomName("Test Room 2");
        assert (room.getRoomName().equals("Test Room 2"));
        assertTrue(room.room_occupancy());

        room.setRoom_current_capacity(0);
        assert (room.room_occupancy() == false);
        }
}