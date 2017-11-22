import facilities.Inspection;
        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

        import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

        import static org.junit.jupiter.api.Assertions.assertTrue;

        public class inspectionTest {
        public Inspection testInspection;
        LocalDate lDate;
        LocalTime lTime;

        @BeforeEach
        void setUp() {
            lTime = LocalTime.now();
            lDate = LocalDate.now();
            testInspection = new Inspection("John",lDate,lTime,"Toilet overflowing",false);
        }
        @AfterEach
        void tearDown() {
                testInspection = null;
            }

        @Test
        public void testInspectCreation(){
            assert (testInspection != null);
            assert (testInspection.getClass() == Inspection.class);
        }

        @Test
        public void testInspectionGetters(){
            assert (testInspection.getInspection_inspector_name().equals("John"));
            assert (testInspection.getInspection_issue().equals("Toilet overflowing"));
            assert (testInspection.getInspection_passed() == false);
            assert (testInspection.getInspection_time() == lTime);
            assert (testInspection.getInspection_date() == lDate);


            assert (testInspection.getInspection_request_date().toString().equals("2017-02-20"));
            testInspection.isInUseDuringInterval();

        }
    }