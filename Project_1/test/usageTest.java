import facilities.Facility;
import facilities.Usage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class usageTest {

    public Facility testFac;
    public ArrayList<Facility> facList;
    public Usage use;

    @BeforeEach
    void setUp() {
        testFac = new Facility();
        use = new Usage();
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
    public void testListUsage(){
        testFac.createARoom("Shower",2,500,1);
        testFac.createARoom("Bathroom",100,102,2);
        use.setUse("Vacant");
        assert (use.getUse().equals("Vacant"));
    }
}

