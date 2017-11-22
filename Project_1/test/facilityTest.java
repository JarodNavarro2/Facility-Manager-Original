import facilities.Facility;
        import facilities.Room;
        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
  * Created by carlreider on 2/11/17.
  */
    public class facilityTest {
        public Facility testFac;
        public ArrayList<Facility> facList;

        @BeforeEach
        void setUp() {
            testFac = new Facility();
            testFac.setFacilityName("Testing");
            facList = new ArrayList<Facility>();
            facList.add(testFac);
        }

        @AfterEach
        void tearDown() {
            facList.clear();
            assert (facList.size() == 0);
            testFac = null;
        }


        @Test
        public void testCreateFacility(){
            // Must manually set all fields in facility
            // Means that the facility will initially be empty
            assert (testFac.getCurrentCapacity() == 0);
            assert (testFac.getFacilityName() == "Testing");
            assert (testFac.getOccupancy() == false);
            assert (testFac.getMaxCapacity() == 0);
            assert (testFac.getStatus() == null);
            }

        @Test
        public void testListFacilities(){
            assert (facList.size() == 1);
            for(Facility fac: facList){
                assert (fac.getFacilityInformation().equals("Name: Testing\n" +
                        "Maximum Capacity: 0\n" +
                        "Current Capacity: 0\n" +
                        "Status: null\n" +
                        "Occupancy: false"));
            }
        }


        @Test
        public void testAddNewFacility(){
            Facility testFac3 = new Facility();
            testFac3.setFacilityName("testFac3");

            assert (testFac3.getFacilityName() == ("testFac3"));
            assert (testFac3.getClass() == Facility.class);
            facList.add(testFac3);
            assert (facList.size() == 2);
        }

        @Test
        public void testFacilitySetters() {
            testFac.setFacilityName("Warehouse 1");
            assert (testFac.getFacilityName().equals("Warehouse 1"));

            testFac.setStatus("Active");
            assert (testFac.getStatus().equals("Active"));

        }

        @Test
        public void testFacRoom(){
            testFac.createARoom("Broom Closet",12,100,2);
            assert (testFac.getMaxCapacity() == 100);
            assert (testFac.roomNumberCheck(2) == true);
            assert (testFac.roomNumberCheck(1) == false);
            assert (testFac.getCurrentCapacity() == 12);

            for (Room room: testFac.rooms){
                Room testRoom = room;
                assert (testFac.getRoom("", 2).equals(testRoom));
            }

            assert (testFac.createARoom("Restroom",3,4,3));
            assert (testFac.getMaxCapacity() == 104);
            assert (testFac.roomNumberCheck(3));
            assert (testFac.getCurrentCapacity() == 15);

            assert (testFac.getOccupancy() == true);

            assert (testFac.bulldozeARoom(2) == true);
            assert (testFac.getCurrentCapacity() == 3);
            assert (testFac.getMaxCapacity() == 4);
            assert (testFac.bulldozeARoom(3) == true);
            }


        @Test
        public void testFacInfo(){
            assert (testFac.getFacilityInformation().equals("Name: Testing\nMaximum Capacity: 0\nCurrent Capacity: 0\nStatus: null\nOccupancy: false"));
        }

        @Test
        public void testAvailableCap(){
            assert (testFac.getMaxCapacity() == 0);
            assert (testFac.getCurrentCapacity() == 0);
            testFac.createARoom("Shower",2,500,1);
            testFac.createARoom("Bathroom",100,102,2);
            assert (testFac.getMaxCapacity() == 602);
            assert (testFac.getMaxCapacity() - testFac.getCurrentCapacity() == 500);

        }

        @Test
        public void complexFacilityTest(){
            testFac.createARoom("Bathroom",2,4,1);
            testFac.createARoom("Cafeteria", 10, 200,2);
            assert (testFac.getCurrentCapacity() == 12);
            assert (testFac.getMaxCapacity() == 204);
            testFac.setStatus("Occupied");
            System.out.println(testFac.getFacilityInformation());
            assert (testFac.getFacilityInformation().equals("Name: Testing\n" + "Maximum Capacity: 204\n" + "Current Capacity: 12\n" +
                    "Status: Occupied\n" + "Occupancy: true"));
            assert (testFac.getRoom("",1).getRoomName() == "Bathroom");

        }
    }