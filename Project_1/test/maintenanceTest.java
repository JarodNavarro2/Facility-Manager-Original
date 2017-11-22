import facilities.Main;
import facilities.Maintenance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by carlreider on 2/15/17.
 */
public class maintenanceTest {
    Maintenance maint;
    @BeforeEach
    public void setUp(){
        maint = new Maintenance();
    }

    @Test
    public void testCreation(){
        assert (maint != null);
    }
    @Test
    public void testSetters(){
        maint.setMainenanceName("Toilet Maintenance");
        assert (maint.getMaintenance_name().equals("Toilet Maintenance"));

        maint.setProblem("Toilet Clogged");
        assert (maint.getProblem().equals("Toilet Clogged"));

        maint.setRequest_approval(false);
        assert (maint.getRequest_approval() == false);

        maint.setSchedule_time(230);
        assert (maint.getSchedule_time() == 230);

        maint.setTotal_budget(100000);
        assert (maint.getTotal_budget() == 100000);

        maint.setTotal_cost(100);
        assert (maint.getTotal_cost() == 100);
    }


}
